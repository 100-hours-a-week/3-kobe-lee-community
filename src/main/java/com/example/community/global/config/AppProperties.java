package com.example.community.global.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class AppProperties {

    @Value("${app.default-profile-image-id}")
    private Long defaultProfileImageId;

}