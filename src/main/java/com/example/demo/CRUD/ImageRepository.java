package com.example.demo.CRUD;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT i.fileName FROM Image i WHERE i.id = :imgId")
    String imgName(@Param("imgId") Long imgId);

}
