package com.alfabank.currency.service;

import com.alfabank.currency.dto.Currency;

import java.time.LocalDate;
import java.util.Optional;

public interface CurrencyHelpService {

    /**
     * Получить курс валют на дату
     *
     * @param date дата
     * @return курс
     */
    Optional<Currency> getCurrency(LocalDate date);
}
