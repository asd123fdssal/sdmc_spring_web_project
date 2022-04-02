package com.arsud.sdmc_spring_web_project.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CrawlingDto {

    private String korName;

    private String releaseDate;

    private String url;
}
