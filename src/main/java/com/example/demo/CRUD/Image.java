package com.example.demo.CRUD;

import javax.persistence.*;

@Entity
@Table(name = "image")
public class Image {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fileName", nullable = false)
    private String fileName;


    @Column(name = "postURL", nullable = false)
    private String postURL;

    public Image() {
    }

    public Image(Long id, String fileName, String postURL) {
        this.id = id;
        this.fileName = fileName;
        this.postURL = postURL;
    }

    public Image(String fileName, String postURL) {
        this.fileName = fileName;
        this.postURL = postURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPostURL() {
        return postURL;
    }

    public void setPostURL(String postURL) {
        this.postURL = postURL;
    }
}
