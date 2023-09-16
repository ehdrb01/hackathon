package dev.hackathon.mandarat.controller;

import dev.hackathon.mandarat.dto.DetailDto;
import dev.hackathon.mandarat.dto.PostDto;
import dev.hackathon.mandarat.dto.request.PostRequest;
import dev.hackathon.mandarat.dto.response.PostNameResponse;
import dev.hackathon.mandarat.dto.response.PostResponse;
import dev.hackathon.mandarat.service.CheckService;
import dev.hackathon.mandarat.service.DetailService;
import dev.hackathon.mandarat.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class UploadController {
    private final PostService postService;
    private final DetailService detailService;
    private final CheckService checkService;

    @PostMapping("/mandarat/post/add/{memberId}")
    public ResponseEntity<Long> addPost(@RequestBody PostRequest postRequest, @PathVariable Long memberId) {
        PostDto postDto = PostDto.toAdd(postRequest);

        Long postId = postService.addPost(postDto, memberId);
        detailService.addDetail(postDto.getDetailDtoList(), postService.getOnePost(postId));


        return ResponseEntity.ok(postId);
    }

    @GetMapping("/mandarat/post/get/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
        PostDto postDto = PostDto.toGetPost(postService.getOnePost(postId));
        List<DetailDto> detailDtoList = detailService.getAllDetail(postId);
        PostResponse postResponse = PostResponse.toResponse(postDto, detailDtoList);

        return ResponseEntity.ok(postResponse);
    }

    @GetMapping("/mandarat/post/user/get/{memberId}")
    public ResponseEntity<List<PostDto>> getPostById(@PathVariable Long memberId) {
        List<PostDto> postDtoList = postService.getMemberPost(memberId).stream().map(PostDto::toGetPost).collect(Collectors.toList());

        return ResponseEntity.ok(postDtoList);
    }

    @GetMapping("/mandarat/post/get")
    public ResponseEntity<List<PostNameResponse>> getAllPostName() {
        List<PostDto> postDtoList = postService.getAllPostName();
        List<PostNameResponse> postResponseList = postDtoList.stream().map(PostNameResponse::toResponse).collect(Collectors.toList());

        return ResponseEntity.ok(postResponseList);
    }

    @DeleteMapping("/mandarat/post/delete/{postId}")
    public ResponseEntity<Long> deletePost(@PathVariable Long postId) {
        Long returnId = postService.deletePost(postId);

        return ResponseEntity.ok(returnId);
    }

    @PostMapping("/mandarat/post/update/{postId}/{memberId}")
    public ResponseEntity<Long> updatePost(@PathVariable Long postId, @PathVariable Long memberId, @RequestBody PostRequest postRequest) {
        PostDto postDto = PostDto.toAdd(postRequest);
        Long returnId = postService.updatePost(postId, postDto, memberId);
        detailService.addDetail(postDto.getDetailDtoList(), postService.getOnePost(returnId));

        return ResponseEntity.ok(returnId);
    }
}
