package com.kimmy.s3bucketspringbootdemo.repository;

import com.kimmy.s3bucketspringbootdemo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Integer> {
}
