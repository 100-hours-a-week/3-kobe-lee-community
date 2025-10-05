package com.example.community.image.repository;

import com.example.community.image.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    boolean existsById(Long id);
}
