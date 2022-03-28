package com.arsud.sdmc_spring_web_project.service;

import com.arsud.sdmc_spring_web_project.entity.Member;
import com.arsud.sdmc_spring_web_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encryptor;

    public Member signup(
            String username,
            String password,
            String mail,
            String nickname
    ) {
        Member member = Member.builder()
                .username(username)
                .password(encryptor.encode(password))
                .nickname(nickname)
                .authority("ROLE_USER")
                .mail(mail)
                .valid(true)
                .build();

        return memberRepository.save(member);
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsernameAndValid(username, true);
    }

    public Member findByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }

}
