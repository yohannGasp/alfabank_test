package com.alfabank.currency.service;

import com.alfabank.currency.dto.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CurrencyHelpServiceImpl implements CurrencyHelpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyHelpServiceImpl.class);

    @Value("${app.url}")
    private String url;

    @Value("app.usd_base")
    private String usdBase; /* валюта по отношению к которой смотрится курс */

    @Autowired
    private RestTemplate restTemplate; /* заменишь на Feign */

    /**
     * Получить курс валют на дату
     *
     * @param date дата
     * @return курс
     */
    @Override
    public Optional<Currency> getCurrency(LocalDate date) {
        try {
            ResponseEntity<Currency> response = restTemplate.getForEntity(url, Currency.class);
            if (response.getStatusCode() == HttpStatus.OK)
                LOGGER.info(response.getBody().toString());
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            LOGGER.error("getCurrency " + e);
        }
        return Optional.empty();
    }
}
