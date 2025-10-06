package com.example.community.auth.api.dto;

import java.time.LocalDateTime;

public record LogoutResponse(
        LocalDateTime logoutAt
) {

}
