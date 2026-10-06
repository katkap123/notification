package com.katta.notification.notification.client;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.katta.notification.notification.dto.StudentContactResponse;

@Component
public class StudentContactClient {

    private final RestClient restClient;
    private final String internalApiKey;

    public StudentContactClient(
            RestClient.Builder restClientBuilder,
            @Value("${auth.api.url}") String authApiUrl,
            @Value("${internal.api.key}") String internalApiKey) {

        this.restClient = restClientBuilder
                .baseUrl(authApiUrl)
                .build();

        this.internalApiKey = internalApiKey;
    }

    public List<StudentContactResponse> getContacts(UUID studentId) {

        StudentContactResponse[] contacts = restClient.get()
                .uri("/api/internal/students/{studentId}/contacts", studentId)
                .header("X-Internal-Api-Key", internalApiKey)
                .retrieve()
                .body(StudentContactResponse[].class);

        if (contacts == null) {
            return List.of();
        }

        return Arrays.asList(contacts);
    }
}