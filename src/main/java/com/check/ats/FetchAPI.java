package com.check.ats;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FetchAPI {
    @PostMapping("/score")
        public ResponseEntity<Map<String, String>> handleUpload(
            @RequestParam("description") String description,
            @RequestParam("qualifications") String qualifications,
            @RequestParam("resume") MultipartFile resume
    ){
        try{
        String filename = resume.getOriginalFilename();
        File dest = new File(System.getProperty("..\\..\\..\\..\\resources\\static\\pdfs"), filename);
        resume.transferTo(dest);
        boolean exists = dest.exists();
        Map<String, String> result = new HashMap<>();
        result.put("result", "✅ File saved: " + filename + " (" + (exists ? "exists" : "missing") + ")");
        return ResponseEntity.ok(result);
        }
        catch (IOException e){

        }
        finally{
            
        }
        return null;
    }
}
