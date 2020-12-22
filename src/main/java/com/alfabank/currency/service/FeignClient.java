package com.alfabank.currency.service;

import com.alfabank.currency.dto.Currency;
import feign.Headers;
import feign.RequestLine;

public interface FeignClient {

    /**
     * Получить курс валют на дату
     *
     * @return курс
     */
    @RequestLine("GET")
    @Headers("Content-Type: application/json")
    Currency getCurrency();
}
