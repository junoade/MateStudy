package com.MateStudy.MateStudy.controller.homework;

import com.MateStudy.MateStudy.config.MD5Generator;
import com.MateStudy.MateStudy.domain.common.DateBefore;
import com.MateStudy.MateStudy.domain.common.DateComparator;
import com.MateStudy.MateStudy.domain.lecture.Lecture;
import com.MateStudy.MateStudy.dto.FileDto;
import com.MateStudy.MateStudy.dto.Lecture.Assign_HomeworkDto;
import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.dto.Lecture.Submit_HomeworkDto;
import com.MateStudy.MateStudy.dto.security.CustomedMemberDTO;
import com.MateStudy.MateStudy.service.FileService;
import com.MateStudy.MateStudy.service.homework.AhwFileService;
import com.MateStudy.MateStudy.service.homework.ShwFileService;
import com.MateStudy.MateStudy.service.lecture.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.util.Pair;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeworkController {
    @Autowired
    private Assign_HomeworkService assgin_homeworkService;

    @Autowired
    private Submit_HomeworkService submit_homeworkService;

    @Autowired
    private TeachLectureService teachLectureService;

    @Autowired
    private TakeLectureService takeLectureService;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private AhwFileService ahwFileService;

    @Autowired
    private ShwFileService shwFileService;

    @Autowired
    private FileService fileService;

    private DateComparator dateComparator = new DateComparator();

    @GetMapping("/download/{Fid}")
    public ResponseEntity<Resource> download(@PathVariable("Fid") Long Fid) throws IOException {
        FileDto fileDto = fileService.getFile(Fid);
        Path path = Paths.get(fileDto.getFilePath());

        String contentType = Files.probeContentType(path);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.builder("attachment").
                filename(fileDto.getOriginFilename(), StandardCharsets.UTF_8).build());
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

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
//        List<Assign_HomeworkDto> ahdList = teachLectureService.getAllHomework(id);
        List<LectureDto> lectureDtoList = teachLectureService.getLectureDtoList(id);
        List<Pair<Assign_HomeworkDto, Optional<Lecture>>> ahdList = new ArrayList<>();
        for(LectureDto lDto : lectureDtoList){
            List<Pair<Assign_HomeworkDto, Optional<Lecture>>> addList = new ArrayList<>();
            addList = lectureService.getHomeworkByCodeWithLecture(lDto.getLecCode(),lDto.getSubCode());
            ahdList.addAll(addList);
        }

        Collections.sort(ahdList, dateComparator);

        List<String> remain = DateBefore.getIntervalPair(ahdList);

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
//        List<Assign_HomeworkDto> ahdList = teachLectureService.getHomework(id,lecCode,subCode);
        List<Pair<Assign_HomeworkDto, Optional<Lecture>>> ahdList = lectureService.getHomeworkByCodeWithLecture(lecCode,subCode);
        List<LectureDto> lectureDtoList = teachLectureService.getLectureDtoList(id);

        Collections.sort(ahdList, dateComparator);

        List<String> remain = DateBefore.getIntervalPair(ahdList);

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
        List<LectureDto> lectureDtoList = takeLectureService.getLectureDtoList(id);
        List<Pair<Assign_HomeworkDto, Optional<Lecture>>> ahdList = new ArrayList<>();
        for(LectureDto lDto : lectureDtoList){
            List<Pair<Assign_HomeworkDto, Optional<Lecture>>> addList = new ArrayList<>();
            addList = lectureService.getHomeworkByCodeWithLecture(lDto.getLecCode(),lDto.getSubCode());
            ahdList.addAll(addList);
        }

        Collections.sort(ahdList, dateComparator);

        List<String> remain = DateBefore.getIntervalPair(ahdList);

        model.addAttribute("name",name);
        model.addAttribute("role",cmDTO.getAuthorities().toString());
        model.addAttribute("allHomeworkList",ahdList);
        model.addAttribute("lectures",lectureDtoList);
        model.addAttribute("remainDate",remain);
        return "/homework/homework-student";
    }

    @GetMapping("/homework-student/{lecCode}/{subCode}")
    public String homeworkStudentDetail(@AuthenticationPrincipal CustomedMemberDTO cmDTO,
                                        @PathVariable String lecCode, @PathVariable Long subCode,
                                        Model model){
        String id = cmDTO.getId();
        String name = cmDTO.getName();

        List<LectureDto> lectureDtoList = takeLectureService.getLectureDtoList(id);
//        List<Assign_HomeworkDto> ahdList = lectureService.getHomeworkByCode(lecCode,subCode);
        List<Pair<Assign_HomeworkDto, Optional<Lecture>>> ahdList = lectureService.getHomeworkByCodeWithLecture(lecCode,subCode);


        Collections.sort(ahdList, dateComparator);

        List<String> remain = DateBefore.getIntervalPair(ahdList);

        model.addAttribute("name",name);
        model.addAttribute("role",cmDTO.getAuthorities().toString());
        model.addAttribute("allHomeworkList",ahdList);
        model.addAttribute("lectures",lectureDtoList);
        model.addAttribute("lecCode",lecCode);
        model.addAttribute("subCode",subCode);
        model.addAttribute("remainDate",remain);
        return "/homework/homework-student-detail";
    }

    @GetMapping("/homework/submit/{hwId}")
    public String homeworkSubmitPage(@AuthenticationPrincipal CustomedMemberDTO cmDTO,
                                     @PathVariable Long hwId, Model model){
        Assign_HomeworkDto homework = assgin_homeworkService.getHomework(hwId);
        model.addAttribute("stId",cmDTO.getId());
        model.addAttribute("homework",homework);
        return "/homework/homework-submit";
    }

    @PostMapping("/homework/submit")
    public String homeworkSubmit(@RequestParam("file") MultipartFile files, Submit_HomeworkDto submitHomeworkDto){
        Long submitId = submit_homeworkService.saveSubmitHwAuto(submitHomeworkDto);

        log.info("saveFile...");
        String origFilename = files.getOriginalFilename();
        if(!origFilename.equals("")){
            try{
                String filename = new MD5Generator(origFilename).toString();
                String savePath = System.getProperty("user.dir") + "/submitHWFiles";
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
                shwFileService.saveSubmittedFile(submitId,fileDto);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return "redirect:/homework-student/"+submitHomeworkDto.getLecCode()+'/'+submitHomeworkDto.getSubCode();
    }

    @GetMapping("/post/student/{hwId}")
    public String postStudentPage(@AuthenticationPrincipal CustomedMemberDTO cmDTO,
                                  @PathVariable Long hwId, Model model){
        String stId = cmDTO.getId();

        if(submit_homeworkService.isSubmitted(stId, hwId)){
            Submit_HomeworkDto subHomework = submit_homeworkService.getHomework(stId, hwId);
            log.info("submitId : "+subHomework.getSubmitId());
            List<FileDto> fileList = shwFileService.getFileDtoBySubmitId(subHomework.getSubmitId());
            model.addAttribute("submitted",1);
            model.addAttribute("homework",subHomework);
            model.addAttribute("fileList",fileList);
            return "/homework/post-student";
        }
        Assign_HomeworkDto homework = assgin_homeworkService.getHomework(hwId);
        List<FileDto> fileList = ahwFileService.getFileDtoByHwId(hwId);
        model.addAttribute("instId",stId);
        model.addAttribute("submitted",0);
        model.addAttribute("homework",homework);
        model.addAttribute("fileList",fileList);
        return "/homework/post-student";
    }
}
