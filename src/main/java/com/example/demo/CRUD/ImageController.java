package com.example.demo.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("image")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<Image> getImages(){
        return imageService.getImages();
    }

    @PostMapping("upload")
    public void addImage(@RequestParam("file") List<MultipartFile> fileList){
        imageService.addImage(fileList);
    }

    @DeleteMapping(path = "{imgId}")
    public void deleteImage(@PathVariable Long imgId) throws IOException {
        imageService.deleteImage(imgId);
    }
}
