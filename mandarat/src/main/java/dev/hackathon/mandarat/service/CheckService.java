package dev.hackathon.mandarat.service;

import dev.hackathon.mandarat.dto.CheckDto;
import dev.hackathon.mandarat.repository.CheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckService {
    private final CheckRepository checkRepository;

    @Transactional
    public void addCheck(List<CheckDto> checkDtoList) {

    }
}
