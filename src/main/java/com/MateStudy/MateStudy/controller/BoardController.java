package com.MateStudy.MateStudy.controller;

import com.MateStudy.MateStudy.config.MD5Generator;
import com.MateStudy.MateStudy.dto.BoardDto;
import com.MateStudy.MateStudy.dto.FileDto;
import com.MateStudy.MateStudy.service.BoardService;
import com.MateStudy.MateStudy.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private FileService fileService;

    @GetMapping("/board")
    public String list(Model model){
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return "board/list";
    }

    @GetMapping("/board/post")
    public String postPage(){
        return "board/post";
    }

    @PostMapping("/board/post")
    public String post(@RequestParam("file")MultipartFile files, BoardDto boardDto){
        String origFilename = files.getOriginalFilename();
        if(!origFilename.equals("")){
            try {
                String filename = new MD5Generator(origFilename).toString();
                /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
                String savePath = System.getProperty("user.dir") + "/files";
                /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
                if (!new File(savePath).exists()) {
                    try{
                        new File(savePath).mkdir();
                    }
                    catch(Exception e){
                        e.getStackTrace();
                    }
                }
                String filePath = savePath + "/" + filename;
                files.transferTo(new File(filePath));

                FileDto fileDto = new FileDto();
                fileDto.setOriginFilename(origFilename);
                fileDto.setFilename(filename);
                fileDto.setFilePath(filePath);

                Long fileId = fileService.saveFile(fileDto);
                boardDto.setFileId(fileId);
                boardService.savePost(boardDto);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }else{
            FileDto fileDto = new FileDto();
            fileService.saveFile(fileDto);
            boardService.savePost(boardDto);
        }
        return "redirect:/board";
    }

    @GetMapping("/board/{id}")
    public String listDetail(@PathVariable("id") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        FileDto fileDto = fileService.getFile(id);
        model.addAttribute("post",boardDto);
        model.addAttribute("filename",fileDto.getOriginFilename());
        return "board/detail";
    }

    @GetMapping("/board/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post",boardDto);
        return "board/edit";
    }

    @PutMapping("/board/edit/{id}")
    public String edit(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/board";
    }

    @DeleteMapping("/board/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.deletePost(id);
        return "redirect:/board";
    }

//    @GetMapping("/download/{fileId}")
//    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
//        FileDto fileDto = fileService.getFile(fileId);
//        Path path = Paths.get(fileDto.getFilePath());
////        Resource resource = new InputStreamResource(Files.newInputStream(path));
////        return ResponseEntity.ok()
////                .contentType(MediaType.parseMediaType("application/octet-stream"))
////                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOriginFilename() + "\"")
////                .body(resource);
//        String contentType = Files.probeContentType(path);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentDisposition(ContentDisposition.builder("attachment").
//                filename(fileDto.getOriginFilename(), StandardCharsets.UTF_8).build());
//        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
//        Resource resource = new InputStreamResource(Files.newInputStream(path));
//        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
//    }
}
