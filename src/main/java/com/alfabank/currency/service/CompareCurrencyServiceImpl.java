package com.alfabank.currency.service;

import com.alfabank.currency.dto.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CompareCurrencyServiceImpl implements CompareCurrencyService {

    @Autowired
    private CurrencyHelpService currencyHelpService;

    /**
     * Сравнить курсы валют
     *
     * @param code     код валюты
     * @param prevDate предыдущая дата
     * @param today    текущая дата
     * @return ссылка на картинку
     */
    @Override
    public String compareRate(String code, LocalDate prevDate, LocalDate today) {

        BigDecimal prevValue = getCur(code, prevDate);
        BigDecimal todayValue = getCur(code, today);

        if (prevValue != null && todayValue != null) {
            if (todayValue.compareTo(prevValue) > 0) {
                return "ссылка 1";
            } else if (todayValue.compareTo(prevValue) < 0) {
                return "ссылка 2";
            } else {
                return "курсы равны";
            }
        }
        return "";
    }


    private BigDecimal getCur(String code, LocalDate date) {
        Optional<Currency> curr = currencyHelpService.getCurrency(date);
        if (curr.isPresent()) {
            return curr.get().getRates().get(code);
        }
        return null;
    }

}
