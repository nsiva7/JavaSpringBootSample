package siva.nimmala.springbootsample.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/files")
@Tag(name = "Multipart", description = "APIs for uploading file and save.")
public class SampleMultipartController {

    SampleMultipartController() {
        System.out.println("Execution Dir: " + System.getProperty("user.dir"));
    }

    private static final String uploadDir = System.getProperty("user.dir") + "/Uploads";

    @PostMapping(value = "/profile", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadProfile(
//            @RequestParam("file") MultipartFile file
            @RequestPart("file") MultipartFile file
    ) {
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
