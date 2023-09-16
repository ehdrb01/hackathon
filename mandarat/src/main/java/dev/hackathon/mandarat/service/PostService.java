package dev.hackathon.mandarat.service;

import dev.hackathon.mandarat.dto.DetailDto;
import dev.hackathon.mandarat.dto.PostDto;
import dev.hackathon.mandarat.dto.request.PostRequest;
import dev.hackathon.mandarat.entity.CheckWord;
import dev.hackathon.mandarat.entity.Detail;
import dev.hackathon.mandarat.entity.Post;
import dev.hackathon.mandarat.repository.CheckRepository;
import dev.hackathon.mandarat.repository.DetailRepository;
import dev.hackathon.mandarat.repository.MemberRepository;
import dev.hackathon.mandarat.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final DetailRepository detailRepository;
    private final CheckRepository checkRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long addPost(PostDto postDto, Long memberId) {
        Post post = postRepository.save(Post.toAdd(postDto, memberRepository.findById(memberId).orElse(null)));

        return post.getId();
    }

    @Transactional
    public Post getOnePost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);

        return post;
    }

    @Transactional
    public List<Post> getMemberPost(Long memberId) {
        List<Post> postList = postRepository.findPostByMemberId(memberId);

        return postList;
    }

    @Transactional
    public List<PostDto> getAllPostName() {
        List<Post> postList = postRepository.findAll();
        List<PostDto> postDtoList = postList.stream().map(PostDto::toGetPost).collect(Collectors.toList());

        return postDtoList;
    }

    @Transactional
    public Long deletePost(Long postId) {
        List<Detail> detailList = detailRepository.findDetailByPostId(postId);

        for(Detail temp : detailList) {
            for(CheckWord checkWord : temp.getCheckWordList()) {
                checkRepository.delete(checkWord);
            }

            detailRepository.delete(temp);
        }
        postRepository.delete(postRepository.findById(postId).orElse(null));

        return postId;
    }

    @Transactional
    public Long updatePost(Long postId, PostDto postDto, Long memberId) {
        Post updatePost = postRepository.save(Post.toAdd(postDto, memberRepository.findById(memberId).orElse(null)));

        deletePost(postId);

        return updatePost.getId();
    }
}
