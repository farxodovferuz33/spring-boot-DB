package com.epam.spring_boot.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class BookDTO {
    private String title;
    private String description;
    private Double price;
    private String author;
}
