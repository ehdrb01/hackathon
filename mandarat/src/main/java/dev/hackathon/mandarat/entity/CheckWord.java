package dev.hackathon.mandarat.entity;

import dev.hackathon.mandarat.dto.CheckDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Detail detail;

    private String name;

    private Boolean isSuccess;

    public static CheckWord toAdd(CheckDto checkDto, Detail detail) {
        return CheckWord.builder()
                .isSuccess(checkDto.getIsSuccess())
                .name(checkDto.getName())
                .detail(detail)
                .build();
    }
}
