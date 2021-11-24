package com.MateStudy.MateStudy.controller.Lecture;

import com.MateStudy.MateStudy.domain.lecture.Lecture;
import com.MateStudy.MateStudy.dto.Lecture.LectureDto;
import com.MateStudy.MateStudy.dto.Lecture.TeachLectureDto;
import com.MateStudy.MateStudy.dto.security.CustomedMemberDTO;
import com.MateStudy.MateStudy.service.lecture.TeachLectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LectureController {
    @Autowired
    private TeachLectureService teachLectureService;

    @GetMapping("/lecture-admin")
    public String lecture(@AuthenticationPrincipal CustomedMemberDTO cmDTO, Model model){
        List<LectureDto> lectureDtoList = teachLectureService.getTeachLectureList(cmDTO.getName());
        model.addAttribute("postList", lectureDtoList);
        return "lecture/lecture-admin";
    }
}
