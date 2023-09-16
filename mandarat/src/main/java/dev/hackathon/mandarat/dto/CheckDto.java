package dev.hackathon.mandarat.dto;

import dev.hackathon.mandarat.dto.request.CheckRequest;
import dev.hackathon.mandarat.dto.request.CheckUpdateRequest;
import dev.hackathon.mandarat.entity.CheckWord;
import lombok.*;
import org.hibernate.annotations.Check;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckDto {
    private Long id;
    private String name;

    private Boolean isSuccess;

    public static CheckDto toAdd(CheckRequest checkRequest) {
        return CheckDto.builder()
                .name(checkRequest.getCheckName())
                .isSuccess(checkRequest.getIsSuccess())
                .build();
    }

    public static CheckDto toUpdate(CheckUpdateRequest checkUpdateRequest) {
        return CheckDto.builder()
                .id(checkUpdateRequest.getId())
                .name(checkUpdateRequest.getCheckName())
                .isSuccess(checkUpdateRequest.getIsSuccess())
                .build();
    }

    public static CheckDto toResponse(CheckWord checkWord) {
        return CheckDto.builder()
                .id(checkWord.getId())
                .name(checkWord.getName())
                .isSuccess(checkWord.getIsSuccess())
                .build();
    }
}
