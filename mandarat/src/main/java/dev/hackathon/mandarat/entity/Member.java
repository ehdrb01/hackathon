package dev.hackathon.mandarat.entity;

import dev.hackathon.mandarat.dto.request.MemberRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String passwd;

    @OneToMany(mappedBy = "member")
    private List<Post> postList;

    public static Member toAdd (MemberRequest memberRequest) {
        return Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .passwd(memberRequest.getPasswd())
                .build();
    }
}
