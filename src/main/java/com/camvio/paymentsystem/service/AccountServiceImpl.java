package com.camvio.paymentsystem.service;

import com.camvio.paymentsystem.entity.PaymentEntity;
import com.camvio.paymentsystem.model.*;
import com.camvio.paymentsystem.repo.PaymentEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Service
public class AccountServiceImpl implements AccountService {

	private static final String X_API_TOKEN = "IQpGaVfcE2VPEzSlyTte0d1BvfxXmWcEsd7p6HWr";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PaymentEntityRepo paymentEntityRepo;

    /**
     * Display balance after fatching account id
     */
    @Override
    public BalanceResponse displayBalance(String accountNumber) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-token", X_API_TOKEN);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Account>> responseBody = requestForAccount(accountNumber, entity);

        Integer accountId = responseBody.getBody().get(0).getId();
        ResponseEntity<Balance> balance = requestForBalance(entity, accountId);
        String balanceData = balance.getBody().getBalance().toString();
        BalanceResponse balanceResponse = new BalanceResponse();
        balanceResponse.setAccountId(accountId);
        balanceResponse.setBalance(balanceData);
        return balanceResponse;
    }

    /**
     * Method is for submit balance and save payment status into database.
     */
    @Override
    public PaymentStatus payBalance(BalanceResponse balanceResponse) {

        PaymentRequest paymentRequest = generatePaymentRequest(balanceResponse);

        Map<String, Integer> urlParams = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-token", X_API_TOKEN);
        HttpEntity<?> entity = new HttpEntity<>(paymentRequest, headers);
        urlParams.put("accountId", balanceResponse.getAccountId());
        URI uri = UriComponentsBuilder
                .fromUriString("https://dev-api.camvio.cloud/aboss-api/rest/v1/account/{accountId}/payment/external")
                .buildAndExpand(urlParams).toUri();
        ResponseEntity<PaymentStatus> balance = restTemplate.exchange(uri, HttpMethod.POST, entity,
                PaymentStatus.class);
        PaymentStatus paymentStatus = balance.getBody();
        savePaymentEntity(paymentStatus, paymentRequest);
        return paymentStatus;
    }

    /**
     * Method generate payment request object with some default values
     */
    private PaymentRequest generatePaymentRequest(BalanceResponse balanceResponse) {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setPaymentAmount(new BigDecimal(balanceResponse.getBalance()));
        paymentRequest.setLocationId(102);
        paymentRequest.setPaymentOption("ALL_UNPAID_INVOICES");
        paymentRequest.setReference(getRandomReferenceId());
        return paymentRequest;
    }

    /**
     * Method save records into PaymentEntity table.
     */
    private void savePaymentEntity(PaymentStatus paymentStatus, PaymentRequest paymentRequest) {

        PaymentEntity paymentObj = new PaymentEntity();
        paymentObj.setId(paymentStatus.getId());
        paymentObj.setMessage(paymentStatus.getMessage());
        paymentObj.setSuccess(paymentStatus.isSuccess());
        paymentObj.setReferenceId(paymentRequest.getReference());
        paymentEntityRepo.save(paymentObj);
    }

    /**
     * Send request to the third party API
     */
    private ResponseEntity<List<Account>> requestForAccount(String accountNumber, HttpEntity<?> entity) {
        String urlTemplate = UriComponentsBuilder
                .fromHttpUrl("https://dev-api.camvio.cloud/aboss-api/rest/v1/accounts?searchType=ACCOUNT+NUMBER")
                .queryParam("term", accountNumber).encode().toUriString();
        ResponseEntity<List<Account>> responseBody = restTemplate.exchange(urlTemplate, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<Account>>() {
                });
        return responseBody;
    }

    /**
     * Send request to the third party API
     */
    private ResponseEntity<Balance> requestForBalance(HttpEntity<?> entity, Integer accountId) {
        Map<String, Integer> urlParams = new HashMap<>();
        urlParams.put("accountId", accountId);
        URI uri = UriComponentsBuilder
                .fromUriString("https://dev-api.camvio.cloud/aboss-api/rest/v1/account/{accountId}/balance")
                .buildAndExpand(urlParams).toUri();
        ResponseEntity<Balance> balance = restTemplate.exchange(uri, HttpMethod.GET, entity, Balance.class);
        return balance;
    }

    private String getRandomReferenceId() {
        String randChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random rnd = new Random();
        while (stringBuilder.length() < 10) {
            int index = (int) (rnd.nextFloat() * randChars.length());
            stringBuilder.append(randChars.charAt(index));
        }
        String randomString = stringBuilder.toString();
        return randomString;

    }

}
