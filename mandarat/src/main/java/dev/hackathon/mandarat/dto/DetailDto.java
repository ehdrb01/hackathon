package dev.hackathon.mandarat.dto;

import dev.hackathon.mandarat.dto.request.DetailRequest;
import dev.hackathon.mandarat.dto.request.DetailUpdateRequest;
import dev.hackathon.mandarat.entity.Detail;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailDto {
    private Long id;

    private String detailName;

    private List<CheckDto> checkDtoList;

    public static DetailDto toAdd(DetailRequest detailRequest) {
        return DetailDto.builder()
                .detailName(detailRequest.getDetailName())
                .checkDtoList(detailRequest.getCheckRequestList().stream()
                        .map(CheckDto::toAdd).collect(Collectors.toList()))
                .build();
    }

    public static DetailDto toUpdate(DetailUpdateRequest detailUpdateList) {
        return DetailDto.builder()
                .detailName(detailUpdateList.getDetailName())
                .checkDtoList(detailUpdateList.getCheckRequestList().stream()
                        .map(CheckDto::toUpdate).collect(Collectors.toList()))
                .build();
    }

    public static DetailDto toResponse(Detail detail) {
        return DetailDto.builder()
                .id(detail.getId())
                .detailName(detail.getDetailName())
                .checkDtoList(detail.getCheckWordList().stream().map(CheckDto::toResponse).collect(Collectors.toList()))
                .build();
    }
}
