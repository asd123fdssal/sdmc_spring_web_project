package com.arsud.sdmc_spring_web_project.repository;

import com.arsud.sdmc_spring_web_project.entity.Member;
import com.arsud.sdmc_spring_web_project.entity.MemberData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDataRepository extends JpaRepository<MemberData, Long> {

    MemberData findByMember_IdAndCharacters_Id(
            Long member_id,
            Long chracters_id
    );

}