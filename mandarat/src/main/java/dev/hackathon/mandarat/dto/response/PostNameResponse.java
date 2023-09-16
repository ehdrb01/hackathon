package dev.hackathon.mandarat.dto.response;

import dev.hackathon.mandarat.dto.PostDto;
import dev.hackathon.mandarat.entity.Post;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostNameResponse {
    private Long postId;

    private String goalName;

    public static PostNameResponse toResponse(PostDto postDto) {
        return PostNameResponse.builder()
                .postId(postDto.getPostId())
                .goalName(postDto.getGoalName())
                .build();
    }
}
