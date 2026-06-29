package com.github.iuryrayam.streaming.controller;

import com.github.iuryrayam.streaming.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;
}
