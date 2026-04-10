package com.project.retailstorefeedback.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeminiService {
    @Value("${gemini.api-key}")
    private String apiKey;

    @Value("${gemini.api-url}")
    private String apiUrl;

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    public String generateContent(String promot){
        try{
            ObjectNode requestBody = objectMapper.createObjectNode();
            ArrayNode contents = objectMapper.createArrayNode();
            ObjectNode content = objectMapper.createObjectNode();
            ArrayNode parts = objectMapper.createArrayNode();
            ObjectNode textPart = objectMapper.createObjectNode();

            textPart.put("text", promot);
            parts.add(textPart);
            content.put("parts", parts);
            contents.add(content);
            requestBody.put("contents", contents);

            ObjectNode generationConfig = objectMapper.createObjectNode();
            generationConfig.put("temperature", 0.7);
            generationConfig.put("maxOutputTokens", 1024);
            requestBody.set("generationConfig", generationConfig);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String url = apiUrl + "?key=" + apiKey;
            HttpEntity<String> request = new HttpEntity<String>(requestBody.toString(), headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST , request, String.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                ObjectNode responseJson = (ObjectNode) objectMapper.readTree(response.getBody());
                ArrayNode candidates = (ArrayNode) responseJson.get("candidates");
                if (candidates != null && candidates.size() > 0) {
                    ObjectNode candidate = (ObjectNode) candidates.get(0);
                    ObjectNode candidateContent = (ObjectNode) candidate.get("content");
                    ArrayNode candidateParts = (ArrayNode) candidateContent.get("parts");
                    if (candidateParts != null && candidateParts.size() > 0) {
                        return candidateParts.get(0).get("text").asText();
                    }
                }
            }
            return "No response from Gemini";
        } catch (Exception e){
            System.err.println("Error calling Gemini Api: "+e.getMessage());
            e.printStackTrace();
            return "Error" + e.getMessage();
        }
    }

    public void testConnection() {
        String testPrompt = "Say 'Hello, World!' if you can hear me.";
        String response = generateContent(testPrompt);
        System.out.println("Gemini API Test Response: " + response);
    }
}
