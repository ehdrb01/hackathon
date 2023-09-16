package dev.hackathon.mandarat.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.hackathon.mandarat.dto.request.LoginRequest;
import dev.hackathon.mandarat.dto.request.MemberRequest;
import dev.hackathon.mandarat.entity.Member;
import dev.hackathon.mandarat.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Value("${kakao.clientId}")
    private String clientId;

    @Value("${kakao.redirectUri}")
    private String redirectUri;

    @Transactional
    public String getAccessToken(String code) throws IOException {
        String reqURL = "https://kauth.kakao.com/oauth/token";
        String access_token = "";
        String refresh_token = "";
        URL url = new URL(reqURL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            urlConnection.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + clientId);
            sb.append("&redirect_uri=" + "http://localhost:8080/mandarat/user/add");
            sb.append("&code=" + code);

            bw.write(sb.toString());
            bw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }


            // json parsing
            JsonParser parser = new JsonParser();
            JsonElement elem = parser.parse(result);

            access_token = elem.getAsJsonObject().get("access_token").getAsString();
            refresh_token = elem.getAsJsonObject().get("refresh_token").getAsString();


            String token = access_token;

            br.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return access_token;
    }
    @Transactional
    public HashMap<String, Object> getUserInfo(String accessToken) {
        HashMap<String, Object> userInfo = new HashMap<>();

        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);



            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }


            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            Long userId = element.getAsJsonObject().get("id").getAsLong();

            String email = null;
            String name = null;
            String phoneNumber = null;
            String birthDay = null;

            if(kakaoAccount.getAsJsonObject().get("email") != null) {
                email = kakaoAccount.getAsJsonObject().get("email").getAsString();
            }

            if(kakaoAccount.getAsJsonObject().get("nickname") != null) {
                name = kakaoAccount.getAsJsonObject().get("nickname").getAsString();
            }

            if(kakaoAccount.getAsJsonObject().get("phone_number") != null) {
                phoneNumber = kakaoAccount.getAsJsonObject().get("phone_number").getAsString();
            }

            if(kakaoAccount.getAsJsonObject().get("birthday") != null) {
                birthDay = kakaoAccount.getAsJsonObject().get("birthday").getAsString();
            }



            userInfo.put("nickname", name);
            userInfo.put("email", email);
            userInfo.put("phoneNumber", phoneNumber);
            userInfo.put("birthDay", birthDay);
            userInfo.put("userKakaoId", userId);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    @Transactional
    public Long addUser(MemberRequest memberRequest) {
        Member test = memberRepository.findMemberByEmail(memberRequest.getEmail());
        Member member;
        if(test != null) {
            member = memberRepository.save(Member.toAdd(memberRequest));
        } else {
            member = null;
        }



        return member.getId();
    }

    @Transactional
    public Boolean loginUser(LoginRequest loginRequest) {
        Member member = memberRepository.findMemberByEmail(loginRequest.getEmail());
        if(member == null) {
            return false;
        } else {
            if(member.getPasswd().equals(loginRequest.getPasswd())) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Transactional
    public Member getMember(String email) {
        Member member = memberRepository.findMemberByEmail(email);

        return member;
    }

    @Transactional
    public String updateMember(MemberRequest memberRequest) {
        Member member = memberRepository.findMemberByEmail(memberRequest.getEmail());

        member.setName(memberRequest.getName());
        member.setPasswd(memberRequest.getPasswd());

        return memberRequest.getEmail();
    }
}