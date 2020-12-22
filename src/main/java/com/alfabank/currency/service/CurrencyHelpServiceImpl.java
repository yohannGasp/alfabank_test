package com.alfabank.currency.service;

import com.alfabank.currency.dto.Currency;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CurrencyHelpServiceImpl implements CurrencyHelpService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyHelpServiceImpl.class);

    @Value("${app.url}")
    private String url;

    @Value("app.usd_base")
    private String usdBase; /* валюта по отношению к которой смотрится курс */

    /**
     * Получить курс валют на дату
     *
     * @param date дата
     * @return курс
     */
    @Override
    public Optional<Currency> getCurrency(LocalDate date) {
        try {
            FeignClient feignClient = Feign.builder()
                    .client(new OkHttpClient())
                    .encoder(new GsonEncoder())
                    .decoder(new GsonDecoder())
                    .logger(new Slf4jLogger(FeignClient.class))
                    .logLevel(feign.Logger.Level.FULL)
                    .target(FeignClient.class, url);

            Currency currency = feignClient.getCurrency();
            LOGGER.info(currency.toString());
            return Optional.of(currency);
        } catch (Exception e) {
            LOGGER.error("getCurrency " + e);
        }
        return Optional.empty();
    }
}
