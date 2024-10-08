package com.suraj.blog_api.surajblogapi.Services.UserServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.web.multipart.MultipartFile;

import com.suraj.blog_api.surajblogapi.Services.FileService;

public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // File fileName
        String name = file.getOriginalFilename();

        // ABC.png

        // Random Name Generate file
        String randomId = UUID.randomUUID().toString();

        String fileName = randomId.concat(name.substring(name.lastIndexOf('.')));

        // FullPath

        String filePath = path + File.separator + fileName;

        // Create a folder if not Created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }

        // File Copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }

}
