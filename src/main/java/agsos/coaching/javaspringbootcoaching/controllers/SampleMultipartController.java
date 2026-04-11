package agsos.coaching.javaspringbootcoaching.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/files")
public class SampleMultipartController {

    private static final String uploadDir = "/Users/nsiva7/Workspace/Coaching/JavaSpringBootCoaching/Uploads";

    @PostMapping("/profile")
    public ResponseEntity<String> uploadProfile(@RequestParam("file") MultipartFile file) {
        try {
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File saveFile = new File(uploadDir, file.getOriginalFilename());

            file.transferTo(saveFile);

            return ResponseEntity.ok("File Uploaded Successfully.");

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
