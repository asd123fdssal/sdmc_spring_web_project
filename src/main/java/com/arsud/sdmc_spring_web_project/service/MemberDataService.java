package com.arsud.sdmc_spring_web_project.service;

import com.arsud.sdmc_spring_web_project.entity.MemberData;
import com.arsud.sdmc_spring_web_project.repository.MemberDataRepository;
import com.arsud.sdmc_spring_web_project.repository.template.MemberDataTemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberDataService {

    private final MemberDataTemplateRepository memberDataTemplateRepository;
    private final MemberDataRepository memberDataRepository;

    public void updateMemberData(
            Long memberDataId,
            String progress
    ){
        memberDataTemplateRepository.updateMemberData(
                memberDataId,
                progress
        );
    }

    public void insertMemberData(
            Long memberId,
            Long characterId,
            Long characterTitleId,
            String progress
    ){
        memberDataTemplateRepository.insertMemberData(
                memberId,
                characterId,
                characterTitleId,
                progress
        );
    }

    public MemberData findByMemberIdCharacterId(
            Long characterId,
            Long memberId
    ){
        return memberDataRepository.findByMember_IdAndCharacters_Id(characterId, memberId);
    }

    public List<MemberData> findByTitleIdAndMemderId (
            Long title_id,
            String username
    ){
        return memberDataTemplateRepository.findByTitleIdAndMemberId(title_id, username);
    }

    public long findByMemberId(
            Long memberId,
            Long characterId
    ){
        return memberDataTemplateRepository.memberDataId(memberId, characterId);
    }

}
