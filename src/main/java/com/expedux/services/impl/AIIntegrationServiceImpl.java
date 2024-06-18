package com.expedux.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import com.expedux.services.AIIntegrationService;

@Service
public class AIIntegrationServiceImpl implements AIIntegrationService {

	@Value("${openai.api.key}")
	private String apiKey;

	private final WebClient webClient;

	public AIIntegrationServiceImpl(WebClient.Builder webClientBuilder) {
	        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
	    }

	private static class RequestPayload {
		private final String model = "gpt-3.5-turbo-16k";
		private final String prompt;
		private final int max_tokens = 60;

		public RequestPayload(String prompt) {
			this.prompt = prompt;
		}

		public String getModel() {
			return model;
		}

		public String getPrompt() {
			return prompt;
		}

		public int getMaxTokens() {
			return max_tokens;
		}
	}

	private static class ResponsePayload {
		private List<Choice> choices;

		public List<Choice> getChoices() {
			return choices;
		}

		private static class Choice {
			private String text;

			public String getText() {
				return text;
			}
		}
	}

	@Override
	public Mono<String> generateText(String prompt) {
		return webClient.post().uri("/completions").header("Authorization", "Bearer " + apiKey)
				.bodyValue(new RequestPayload(prompt)).retrieve().bodyToMono(ResponsePayload.class)
				.map(response -> response.getChoices().get(0).getText());
	}
}
