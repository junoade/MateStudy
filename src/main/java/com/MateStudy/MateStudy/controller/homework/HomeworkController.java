package com.MateStudy.MateStudy.controller.homework;

import com.MateStudy.MateStudy.config.MD5Generator;
import com.MateStudy.MateStudy.domain.common.DateBefore;
import com.MateStudy.MateStudy.domain.common.DateComparator;
import com.MateStudy.MateStudy.dto.FileDto;
import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;
import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.dto.security.CustomedMemberDTO;
import com.MateStudy.MateStudy.repository.lecture.LectureRepository;
import com.MateStudy.MateStudy.service.homework.AhwFileService;
import com.MateStudy.MateStudy.service.lecture.Assign_HomeworkService;
import com.MateStudy.MateStudy.service.lecture.LectureService;
import com.MateStudy.MateStudy.service.lecture.TeachLectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeworkController {
    @Autowired
    private Assign_HomeworkService assgin_homeworkService;

    @Autowired
    private TeachLectureService teachLectureService;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private AhwFileService ahwFileService;

    @GetMapping("/homework")
    public String homework(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){
        if(cmDTO.getAuthorities().toString().equals("[INSTRUCTOR]")){
            return homeworkAdmin(cmDTO, model);
        }else{
            return homeworkStudent(cmDTO, model);
        }
    }

    @GetMapping("/homework-admin")
    public String homeworkAdmin(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){
        String id = cmDTO.getId();
        String name = cmDTO.getName();
        List<Assign_HomeworkDto> ahdList = teachLectureService.getAllHomework(id);
        List<LectureDto> lectureDtoList = teachLectureService.getTeachLectureList(id);

        DateComparator dateComparator = new DateComparator();
        Collections.sort(ahdList, dateComparator);

        List<String> remain = DateBefore.getInterval(ahdList);

        model.addAttribute("name",name);
        model.addAttribute("role",cmDTO.getAuthorities().toString());
        model.addAttribute("instId",id);
        model.addAttribute("allHomeworkList",ahdList);
        model.addAttribute("lectures",lectureDtoList);
        model.addAttribute("remainDate",remain);
        return "/homework/homework-admin";
    }

    @GetMapping("homework-admin/{lecCode}/{subCode}")
    public String homeworkAdminDetail(@AuthenticationPrincipal CustomedMemberDTO cmDTO,
                                      @PathVariable String lecCode, @PathVariable Long subCode,
                                      Model model){
        String id = cmDTO.getId();
        String name = cmDTO.getName();
        List<Assign_HomeworkDto> ahdList = teachLectureService.getHomework(id,lecCode,subCode);
        List<LectureDto> lectureDtoList = teachLectureService.getTeachLectureList(id);

        DateComparator dateComparator = new DateComparator();
        Collections.sort(ahdList, dateComparator);

        List<String> remain = DateBefore.getInterval(ahdList);

        model.addAttribute("name",name);
        model.addAttribute("role",cmDTO.getAuthorities().toString());
        model.addAttribute("instId",id);
        model.addAttribute("lecCode",lecCode);
        model.addAttribute("subCode",subCode);
        model.addAttribute("homeworkList",ahdList);
        model.addAttribute("lectures",lectureDtoList);
        model.addAttribute("remainDate",remain);
        return "/homework/homework-admin-detail";
    }

    @GetMapping("/homework/register/{lecCode}/{subCode}")
    public String homeworkRegisterPage2(@AuthenticationPrincipal CustomedMemberDTO cmDTO,
                                   @PathVariable String lecCode, @PathVariable Long subCode,
                                   Model model){
        LectureDto lectureDto = lectureService.getLecture(lecCode, subCode);
        model.addAttribute("instId",cmDTO.getId());
        model.addAttribute("lecture",lectureDto);
        return "/homework/homework-register";
    }

    @PostMapping("/homework/register")
    public String homeworkRegister(@RequestParam("file") MultipartFile files, Assign_HomeworkDto assignHomeworkDto){
        Long hwId = assgin_homeworkService.saveHomeworkAuto(assignHomeworkDto);

        log.info("saveFile...");
        String origFilename = files.getOriginalFilename();
        if(!origFilename.equals("")){
            try{
                String filename = new MD5Generator(origFilename).toString();
                String savePath = System.getProperty("user.dir") + "/assignHWFiles";
                if(!new File(savePath).exists()){
                    try{
                        new File(savePath).mkdir();
                    }catch (Exception e){
                        e.getStackTrace();
                    }
                }
                String filePath = savePath + "/" + filename;
                files.transferTo(new File(filePath));

                FileDto fileDto = new FileDto(origFilename,filename,filePath);
                log.info(fileDto.getFilePath());
                log.info(fileDto.getFilename());
                log.info(fileDto.getOriginFilename());
                ahwFileService.saveAssignedFile(hwId,fileDto);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/homework-admin/"+assignHomeworkDto.getLecCode()+'/'+assignHomeworkDto.getSubCode();
    }

    @GetMapping("/homework-student")
    public String homeworkStudent(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){
        String id = cmDTO.getId();
        String name = cmDTO.getName();
        List<Assign_HomeworkDto> ahdList = teachLectureService.getAllHomework(id);
        List<LectureDto> lectureDtoList = teachLectureService.getTeachLectureList(id);

        model.addAttribute("name",name);
        model.addAttribute("role",cmDTO.getAuthorities().toString());
        model.addAttribute("allHomeworkList",ahdList);
        model.addAttribute("lectures",lectureDtoList);
        return "/homework/homework-student";
    }
}
