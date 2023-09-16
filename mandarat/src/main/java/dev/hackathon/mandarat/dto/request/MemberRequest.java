package dev.hackathon.mandarat.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequest {
    private String email;

    private String passwd;

    private String name;
}
