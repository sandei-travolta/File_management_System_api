package com.sandeidevlab.File_management_System.Controller;

import com.sandeidevlab.File_management_System.Enity.File;
import com.sandeidevlab.File_management_System.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    @PutMapping("/Files")
    public String update(@RequestBody File file) throws ExecutionException, InterruptedException {
        return fileService.updateFile(file);
    }
    @DeleteMapping("/Files/{name}")
    public String deleteProduct(@PathVariable String name) throws ExecutionException, InterruptedException {
        return FileService.deleteFileByTittle(name);
    }

}
