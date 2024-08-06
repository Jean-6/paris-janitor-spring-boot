package com.example.paris_janitor_api.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponse {
    //private String filePath;
    private String url;
    private LocalDateTime dateTime;
}
