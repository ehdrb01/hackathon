package dev.hackathon.mandarat.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DetailRequest {
    private String detailName;

    private List<CheckRequest> checkRequestList;
}
