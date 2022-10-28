package com.example.postgresql.controller;

import com.example.postgresql.Utils.EmailService;
import com.example.postgresql.dto.request.CityRequest;
import com.example.postgresql.dto.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/email")
public class EmailSenderController {
    @Autowired
    EmailService emailService;

    @GetMapping
    public ResponseEntity sendMessage(){
        return ResponseEntity.ok(emailService.sendEmail("jovidon-a.90@mail.ru","Email","My message text"));
    }

    @PostMapping
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        emailService.uploadFile(file);
        return ResponseEntity.ok("File uploaded successfully!");
    }

}
