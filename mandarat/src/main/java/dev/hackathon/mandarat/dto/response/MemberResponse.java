package dev.hackathon.mandarat.dto.response;

import dev.hackathon.mandarat.entity.Member;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private String name;
    private String email;
    private String passwd;

    public static MemberResponse toResponse(Member member) {
        return MemberResponse.builder()
                .name(member.getName())
                .email(member.getEmail())
                .passwd(member.getPasswd())
                .build();
    }
}
