package com.expedux.services;

import reactor.core.publisher.Mono;

public interface AIIntegrationService {

	Mono<String> generateText(String prompt);
	
}
