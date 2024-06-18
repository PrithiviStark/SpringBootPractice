package com.expedux.restControllers;


import com.expedux.services.AIIntegrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class OpenAIController {

	@Autowired
    AIIntegrationService aiService;

    @GetMapping("generate-text")
    public Mono<String> generateText(@RequestParam String prompt) {
        return aiService.generateText(prompt);
    }
}
