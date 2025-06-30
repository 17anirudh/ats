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
    Map<String, String> result = new HashMap<>();
    @PostMapping("/calculate")
        public ResponseEntity<Map<String, String>> handleUpload(
            @RequestParam("description") String description,
            @RequestParam("qualifications") String qualifications,
            @RequestParam("resume") MultipartFile resume
    ){
        try{
            String filename = resume.getOriginalFilename();
            File dest = new File(System.getProperty("..\\..\\..\\..\\resources\\static\\pdfs"), filename);
            ParseDocument parser = new ParseDocument();
            resume.transferTo(dest);
            String text = parser.parse("..\\..\\..\\..\\resources\\static\\pdfs" + filename);
            result.put("Resume", text);
            result.put("Qualification", qualifications);
            result.put("Description", description);
            
            return ResponseEntity.ok(result);
        }
        catch (IOException e){

        }
        finally{
            
        }
        return null;
    }
}
