package com.arsud.sdmc_spring_web_project.service;

import com.arsud.sdmc_spring_web_project.entity.Characters;
import com.arsud.sdmc_spring_web_project.entity.Title;
import com.arsud.sdmc_spring_web_project.repository.CharactersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharactersService {

    private final CharactersRepository charactersRepository;

    public Characters save(
            Title title,
            byte[] picture,
            String korName,
            String orgName,
            String strategy
    ){
        return charactersRepository.save(
                Characters.builder()
                        .title(title)
                        .picture(picture)
                        .korName(korName)
                        .orgName(orgName)
                        .strategy(strategy)
                        .valid(true)
                        .build()
        );
    }

}
