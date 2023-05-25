package com.sandeidevlab.File_management_System.Controller;

import com.sandeidevlab.File_management_System.Enity.File;
import com.sandeidevlab.File_management_System.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class FileController {
    @Autowired
    private FileService fileService;
    @GetMapping("/Files")
    public List<File> getAllFiles() throws ExecutionException, InterruptedException {
        return fileService.getFileDetails();
    }
}
