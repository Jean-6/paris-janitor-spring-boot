package com.example.paris_janitor_api.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stage {
    private RequestStatus status;
    @CreatedDate
    private LocalDateTime createdAt;
}
