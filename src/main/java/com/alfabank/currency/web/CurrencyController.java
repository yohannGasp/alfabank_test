package com.alfabank.currency.web;

import com.alfabank.currency.service.CompareCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/compare_cur")
public class CurrencyController {

    @Autowired
    private CompareCurrencyService compareCurrencyService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<String> getBrokerAccount(@RequestParam(value = "code") final String code) {

        LocalDate now = LocalDate.now();
        LocalDate yesterday = now.minusDays(1);
        return ResponseEntity.ok(compareCurrencyService.compareRate(code, yesterday, now));
    }
}
