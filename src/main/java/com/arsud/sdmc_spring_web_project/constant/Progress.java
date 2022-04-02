package com.arsud.sdmc_spring_web_project.constant;

import java.util.List;

public class Progress {

    public static List<String> listOfProgress(){
        return List.of(
                "미진행",
                "진행중",
                "완료"
        );
    }
}
