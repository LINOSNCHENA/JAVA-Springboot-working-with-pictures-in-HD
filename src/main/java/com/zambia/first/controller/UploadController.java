package com.zambia.first.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://zTDB//";
    
    // ----------------------------------------------------------------------- INTRODUCTION
    @GetMapping("/")
    public String index() { return "uploadfile";  }

    // ----------------------------------------------------------------------- UPLOAD ROUTING
    @PostMapping("/uploadfile") 
    public String sFileUpload(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes) 
    {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a pembafile to upload");
            return "redirect:uploaded";
           }
        try { // Pickup the file for relocation in your individual folder
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("message",
            "You successfully pemba uploaded '" + file.getOriginalFilename() + "'");
            } 
        catch (IOException e) {   e.printStackTrace();       }

        return "redirect:/uploaded";
    }
  // ------------------------------------------------------------------------- RESULTS PRINTING
    @GetMapping("/uploaded")
    public String uploadStatus() { return "uploaded";  }

}
