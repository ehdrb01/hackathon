package dev.hackathon.mandarat.dto.response;

import dev.hackathon.mandarat.dto.DetailDto;
import dev.hackathon.mandarat.dto.PostDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private PostDto post;

    private List<DetailDto> detailList;

    public static PostResponse toResponse(PostDto post, List<DetailDto> detailList) {
        return PostResponse.builder()
                .post(post)
                .detailList(detailList)
                .build();
    }
}
