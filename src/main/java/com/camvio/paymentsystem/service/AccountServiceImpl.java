package com.camvio.paymentsystem.service;

import com.camvio.paymentsystem.model.Account;
import com.camvio.paymentsystem.model.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> displayBalance(String accountNumber) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-token", "IQpGaVfcE2VPEzSlyTte0d1BvfxXmWcEsd7p6HWr");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String urlTemplate = UriComponentsBuilder.fromHttpUrl("https://dev-api.camvio.cloud/aboss-api/rest/v1/accounts?searchType=ACCOUNT+NUMBER")
                .queryParam("term", accountNumber)
                .encode()
                .toUriString();
        ResponseEntity<List<Account>> responseBody = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Account>>() {
        });
        Integer accountId = responseBody.getBody().get(0).getId();
        Map<String, Integer> urlParams = new HashMap<>();
        urlParams.put("accountId", accountId);
        URI uri = UriComponentsBuilder.fromUriString("https://dev-api.camvio.cloud/aboss-api/rest/v1/account/{accountId}/balance")
                .buildAndExpand(urlParams)
                .toUri();
        ResponseEntity<String> balance = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

        return balance;
    }

    @Override
    public ResponseEntity<String> payBalance(PaymentRequest paymentRequest) {
        return null;
    }
}
