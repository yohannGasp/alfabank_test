package com.alfabank.currency.service;

import com.alfabank.currency.dto.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CompareCurrencyServiceImpl implements CompareCurrencyService {

    private static final String RATE_UP = "https://static8.depositphotos.com/1043073/1040/i/950/depositphotos_10406112-stock-photo-blue-data-space.jpg";
    private static final String RATE_DOWN = "https://st2.depositphotos.com/2612745/6047/i/950/depositphotos_60472967-stock-photo-devaluation.jpg";
    private static final String RATE_EQ = "https://st.depositphotos.com/1202020/4899/i/950/depositphotos_48990703-stock-photo-usd-jpy-exchange-rate.jpg";

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
                return String.format("<img src=\"%s\">", RATE_UP);
            } else if (todayValue.compareTo(prevValue) < 0) {
                return String.format("<img src=\"%s\">", RATE_DOWN);
            } else {
                return String.format("<img src=\"%s\">", RATE_EQ);
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
