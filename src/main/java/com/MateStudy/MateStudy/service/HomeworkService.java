package com.MateStudy.MateStudy.service;

import com.MateStudy.MateStudy.domain.homework.Homework;
import com.MateStudy.MateStudy.dto.HomeworkDto;
import com.MateStudy.MateStudy.repository.HomeworkRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomeworkService {
    private HomeworkRepository homeworkRepository;

    public HomeworkService(HomeworkRepository homeworkRepository){
        this.homeworkRepository = homeworkRepository;
    }

    @Transactional
    public void saveHomework(HomeworkDto homeworkDto){
        homeworkRepository.save(homeworkDto.toEntity());
    }

    public List<HomeworkDto> getHomeworkList(){
        List<Homework> homeworkList = homeworkRepository.findAll();
        List<HomeworkDto> homeworkDtoList = new ArrayList<>();

        for(Homework homework : homeworkList){
            HomeworkDto homeworkDto = HomeworkDto.builder()
                    .hno(homework.getHno())
                    .content(homework.getContent())
                    .title(homework.getTitle())
                    .dueDate(homework.getDueDate())
                    .regDate(homework.getRegDate())
                    .modDate(homework.getModDate())
                    .build();
            homeworkDtoList.add(homeworkDto);
        }
        return homeworkDtoList;
    }
}
