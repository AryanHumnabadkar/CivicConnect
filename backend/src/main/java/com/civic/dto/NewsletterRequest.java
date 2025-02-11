package com.civic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsletterRequest {
    private String email;
    private String category;

}
