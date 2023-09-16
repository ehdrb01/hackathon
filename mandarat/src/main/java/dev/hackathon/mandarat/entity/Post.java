package dev.hackathon.mandarat.entity;

import dev.hackathon.mandarat.dto.PostDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goalName;

    @OneToMany(mappedBy = "post")
    private List<Detail> detailList;

    @ManyToOne
    private Member member;

    public static Post toAdd(PostDto postDto, Member member) {
        return Post.builder()
                .member(member)
                .goalName(postDto.getGoalName())
                .build();
    }
}
