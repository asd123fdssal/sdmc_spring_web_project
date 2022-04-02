package com.arsud.sdmc_spring_web_project.controller;

import com.arsud.sdmc_spring_web_project.service.MemberDataService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Controller
@AllArgsConstructor
public class MemberDataController {

    private final MemberDataService memberDataService;

    @PostMapping("/game/title/detail/characters")
    public void characterProgress(
            Model model,
            @RequestBody HashMap<String, Object> progress
    ){
        long character_id = Long.parseLong(String.valueOf(progress.get("character_id")));
        long member_id = Long.parseLong(String.valueOf(progress.get("member_id")));
        long title_id = Long.parseLong(String.valueOf(progress.get("title_id")));
        long member_data_id = memberDataService.findByMemberId(member_id, character_id);
        String progress_str = String.valueOf(progress.get("progress"));

        if(member_data_id != -1){
            memberDataService.updateMemberData(
                    member_data_id,
                    progress_str
            );
        }else{
            memberDataService.insertMemberData(
                    member_id,
                    character_id,
                    title_id,
                    progress_str
            );
        }
    }

}
