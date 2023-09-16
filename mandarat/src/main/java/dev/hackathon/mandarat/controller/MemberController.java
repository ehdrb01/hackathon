package dev.hackathon.mandarat.controller;

import dev.hackathon.mandarat.dto.request.LoginRequest;
import dev.hackathon.mandarat.dto.request.MemberRequest;
import dev.hackathon.mandarat.dto.response.MemberResponse;
import dev.hackathon.mandarat.entity.Member;
import dev.hackathon.mandarat.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("mandarat/user/add")
    public RedirectView userAdd(@RequestParam(value = "code", required = false) String code) throws Exception {
        String accessToken = memberService.getAccessToken(code);
        HashMap<String, Object> userInfo = memberService.getUserInfo(accessToken);

        RedirectView redirectView = new RedirectView();

        if(userInfo.get("userKakaoId") == null) { // kakao Id를 통해 불러오는 과정에서 실패 시 null return
            redirectView.setUrl("http://localhost:8080/target?param=" + userInfo.get("userKakaoId"));

            return redirectView;
        } else {
//            Long memberId = memberService.addUser(userInfo);
            redirectView.setUrl("http://localhost:8080/target?param=" + userInfo.get("userKakaoId"));

            return redirectView;
        }
    }

    @GetMapping("mandarat/user/login/{accessToken}")
    public String userLogin(@PathVariable String accessToken) {
        HashMap<String, Object> userInfo = memberService.getUserInfo(accessToken);

        return "redirect:http://localhost:8080/target?param=" + userInfo.get("userKakaoId");
    }


    @PostMapping("/mandarat/user/add")
    public ResponseEntity<Long> addUser(@RequestBody MemberRequest memberRequest) {
        Long memberId = memberService.addUser(memberRequest);
        System.out.println(memberRequest.getPasswd());

        return ResponseEntity.ok(memberId);
    }

    @PostMapping("/mandarat/user/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody LoginRequest loginRequest) {
        Boolean isLogin = memberService.loginUser(loginRequest);

        return ResponseEntity.ok(isLogin);
    }

    @GetMapping("/mandarat/user/get/{email}")
    public ResponseEntity<MemberResponse> getUser(@PathVariable String email) {
        Member member = memberService.getMember(email);
        MemberResponse memberResponse = MemberResponse.toResponse(member);

        return ResponseEntity.ok(memberResponse);
    }

    @PostMapping("/mandart/user/update")
    public ResponseEntity<?> updateUser(@RequestBody MemberRequest memberRequest) {
        String email = memberService.updateMember(memberRequest);

        return ResponseEntity.ok(email);
    }
}
