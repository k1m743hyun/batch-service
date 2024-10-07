package com.k1m743hyun.batchservice.presentation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.k1m743hyun.batchservice.application.data.dto.request.TableCreateRequest;
import com.k1m743hyun.batchservice.application.service.LinkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/batch/link")
@RequiredArgsConstructor
@RestController
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/table:create")
    public ResponseEntity<Void> createTable(
        @RequestBody TableCreateRequest request
    ) {
        linkService.createTable(request);
        return ResponseEntity.ok().build();
    }
}
