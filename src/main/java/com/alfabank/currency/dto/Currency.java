package com.alfabank.currency.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Currency {

    private Long timestamp;
    private String base;
    private Map<String, BigDecimal> rates;

}
