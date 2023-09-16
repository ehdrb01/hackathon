package dev.hackathon.mandarat.entity;

import dev.hackathon.mandarat.dto.DetailDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String detailName;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    @OneToMany(mappedBy = "detail")
    private List<CheckWord> checkWordList;

    public static Detail toAdd(DetailDto detailDto, Post post) {
        return Detail.builder()
                .detailName(detailDto.getDetailName())
                .post(post)
                .build();
    }
}
