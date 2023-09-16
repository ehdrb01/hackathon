package dev.hackathon.mandarat.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostUpdateRequest {
    private Long id;
    private String goalName;

    private List<DetailUpdateRequest> detailList;
}
