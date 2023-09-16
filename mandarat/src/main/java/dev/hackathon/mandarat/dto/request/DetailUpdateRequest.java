package dev.hackathon.mandarat.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DetailUpdateRequest {
    private Long id;
    private String detailName;

    private List<CheckUpdateRequest> checkRequestList;
}
