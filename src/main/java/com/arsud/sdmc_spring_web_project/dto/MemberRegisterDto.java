package com.arsud.sdmc_spring_web_project.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberRegisterDto extends BaseDto{

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String password_confirm;

    @NonNull
    private String nickname;

    @NonNull
    private String mail;
}
