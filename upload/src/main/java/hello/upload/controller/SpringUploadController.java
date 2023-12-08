package hello.upload.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

//    @PostMapping("/upload")
    public String saveFile(@RequestParam String itemName,
                           @RequestParam MultipartFile file,
                           HttpServletRequest request) throws IOException {

        log.info("request={}", request);
        log.info("itemName={}", itemName);
        log.info("multipartFile={}", file);

        if (!file.isEmpty()) {
            String fullPath = fileDir + file.getOriginalFilename();
            log.info("파일 저장 fullPath={}", fullPath);
            file.transferTo(new File(fullPath));
        }

        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV2(@ModelAttribute TestDto testDto,
                           HttpServletRequest request) throws IOException {

        log.info("request={}", request);
        log.info("itemName={}", testDto.getItemName());
        log.info("multipartFile={}", testDto.getFile());

        if (!testDto.getFile().isEmpty()) {
            String fullPath = fileDir + testDto.getFile().getOriginalFilename();
            log.info("파일 저장 fullPath={}", fullPath);
            testDto.getFile().transferTo(new File(fullPath));
        }

        return "upload-form";
    }

    @Data
    @AllArgsConstructor
    static class TestDto {
        private String itemName;
        private MultipartFile file;
    }
    
}
