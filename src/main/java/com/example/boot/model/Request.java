package com.example.boot.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Request {
    private String name;
    private String information;
    private String year;
}
