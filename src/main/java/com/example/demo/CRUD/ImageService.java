package com.example.demo.CRUD;

import com.example.demo.Exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.upload-dir}")
    private String targetLocation;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> getImages() {
        return imageRepository.findAll();
    }

    public void addImage(List<MultipartFile> fileList) {
        for (MultipartFile file: fileList) {
        Random random = new Random();
        String fileName = StringUtils.cleanPath(random.nextInt(10000) + "-" + file.getOriginalFilename());

        try {
            Files.write(Paths.get(targetLocation).resolve(fileName), file.getBytes());
        }catch(IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!",ex);
        }

        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileName)
                .toUriString();

        Image image = new Image(fileName, fileUri);
        imageRepository.save(image);
        }
    }

    public void deleteImage(Long imgId) throws IOException {
        Optional<Image> imageOptional = imageRepository.findById(imgId);
        if (imageOptional.isEmpty()){
            throw new IllegalStateException("Image with id number " + imgId + " does not exists");
        } else
            Files.delete(Path.of(targetLocation + "/" + imageRepository.imgName(imgId)));
            imageRepository.deleteById(imgId);
    }
}
