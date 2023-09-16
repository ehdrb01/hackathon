package dev.hackathon.mandarat.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckRequest {
    private String checkName;

    private Boolean isSuccess;
}
