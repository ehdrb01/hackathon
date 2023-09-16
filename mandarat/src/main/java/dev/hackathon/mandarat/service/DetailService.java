package dev.hackathon.mandarat.service;

import dev.hackathon.mandarat.dto.CheckDto;
import dev.hackathon.mandarat.dto.DetailDto;
import dev.hackathon.mandarat.entity.CheckWord;
import dev.hackathon.mandarat.entity.Detail;
import dev.hackathon.mandarat.entity.Post;
import dev.hackathon.mandarat.repository.CheckRepository;
import dev.hackathon.mandarat.repository.DetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DetailService {
    private final DetailRepository detailRepository;
    private final CheckRepository checkRepository;

    @Transactional
    public List<Detail> addDetail(List<DetailDto> detailDtoList, Post post) {
        List<Detail> detailList = detailDtoList.stream()
                .map((DetailDto detailDto) -> Detail.toAdd(detailDto, post)).collect(Collectors.toList());

        for(DetailDto detailDto : detailDtoList) {
            Detail detail = Detail.toAdd(detailDto, post);
            detailRepository.save(detail);
            for(CheckDto checkDto : detailDto.getCheckDtoList()) {
                CheckWord checkWord = CheckWord.toAdd(checkDto, detail);
                checkRepository.save(checkWord);
            }
        }

        return detailList;
    }

    @Transactional
    public List<DetailDto> getAllDetail(Long postId) {
        List<Detail> detailList = detailRepository.findDetailByPostId(postId);

        List<DetailDto> detailDtoList = detailList.stream().map(DetailDto::toResponse).collect(Collectors.toList());

        return detailDtoList;
    }
}
