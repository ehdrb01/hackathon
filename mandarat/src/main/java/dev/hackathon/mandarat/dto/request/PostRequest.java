package dev.hackathon.mandarat.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostRequest {
    private String goalName;

    private List<DetailRequest> detailList;
}
