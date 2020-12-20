package com.alfabank.currency.service;

import com.alfabank.currency.dto.Currency;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CompareCurrencyServiceImplTest {

    public static final BigDecimal YESTERDAY_RATE = new BigDecimal("56.00");
    public static final BigDecimal NOW_RATE = new BigDecimal("58.00");
    public static final BigDecimal NOW_RATE_1 = new BigDecimal("55.00");

    private static final String LINK_1 = "ссылка 1";
    private static final String LINK_2 = "ссылка 2";

    @InjectMocks
    private CompareCurrencyServiceImpl compareCurrencyService;

    @Mock
    private CurrencyHelpServiceImpl currencyHelpService;

    /**
     * Тестируем метод compareRate
     */
    @Test
    void compareRate() {

        LocalDate now = LocalDate.now();
        LocalDate yesterday = now.minusDays(1);

        /* курс стал выше */
        when(currencyHelpService.getCurrency(yesterday)).thenReturn(Optional.of(currency("USD", YESTERDAY_RATE)));
        when(currencyHelpService.getCurrency(now)).thenReturn(Optional.of(currency("USD", NOW_RATE)));

        String link = compareCurrencyService.compareRate("USD", yesterday, now);
        assertThat(link).isEqualTo(LINK_1);

        /* курс стал ниже */
        when(currencyHelpService.getCurrency(now)).thenReturn(Optional.of(currency("USD", NOW_RATE_1)));

        link = compareCurrencyService.compareRate("USD", yesterday, now);
        assertThat(link).isEqualTo(LINK_2);

    }

    private Currency currency(String base, BigDecimal value) {
        return Currency.builder().timestamp(1608465606L).base(base).rates(Map.of("USD", value)).build();
    }

}