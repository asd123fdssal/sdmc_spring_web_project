package com.arsud.sdmc_spring_web_project.repository;

import com.arsud.sdmc_spring_web_project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>
{
    Member findByUsernameAndValid(String username, Boolean valid);
    Member findByNickname(String nickname);
}
