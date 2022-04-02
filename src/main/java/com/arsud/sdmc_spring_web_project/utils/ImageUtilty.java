package com.arsud.sdmc_spring_web_project.utils;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImageUtilty {

    public static String makeBase64Image(byte[] image){
        return Base64.getEncoder().encodeToString(image);
    }
}
