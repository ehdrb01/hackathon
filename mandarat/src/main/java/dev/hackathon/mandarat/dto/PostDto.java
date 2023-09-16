package dev.hackathon.mandarat.dto;

import dev.hackathon.mandarat.dto.request.PostRequest;
import dev.hackathon.mandarat.dto.request.PostUpdateRequest;
import dev.hackathon.mandarat.entity.Post;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long postId;

    private String goalName;

    private List<DetailDto> detailDtoList;

    public static PostDto toAdd(PostRequest postRequest) {
        return PostDto.builder()
                .goalName(postRequest.getGoalName())
                .detailDtoList(postRequest.getDetailList().stream()
                        .map(DetailDto::toAdd).collect(Collectors.toList()))
                .build();
    }

    public static PostDto toUpdate(PostUpdateRequest postUpdateRequest) {
        return PostDto.builder()
                .goalName(postUpdateRequest.getGoalName())
                .detailDtoList(postUpdateRequest.getDetailList().stream()
                        .map(DetailDto::toUpdate).collect(Collectors.toList()))
                .build();
    }

    public static PostDto toGetPost(Post post) {
        return PostDto.builder()
                .postId(post.getId())
                .goalName(post.getGoalName())
                .build();
    }
}
