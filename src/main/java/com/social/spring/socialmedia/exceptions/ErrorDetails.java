package com.social.spring.socialmedia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
    private String errorMessage;
    private String errorPath;
    private LocalDateTime timestamp;
}
