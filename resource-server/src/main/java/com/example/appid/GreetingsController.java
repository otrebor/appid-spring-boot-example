package com.example.appid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GreetingsController {
    @GetMapping(path = "/greeting", produces = MediaType.APPLICATION_JSON_VALUE)
    public GreetingDto getGreeting() {
        return new GreetingDto("Greetings dear user!");
    }

    record GreetingDto(String message) {
    }
}
