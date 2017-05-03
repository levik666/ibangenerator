package com.iban.service.impl;

import com.iban.service.IbanService;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class IbanServiceGenerator implements IbanService {

    @Override
    public String generateIban() {
        return Iban.random().toString();
    }

    @Override
    public String generateIban(String countryCode, String bankCode, String accountNumber) {
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.valueOf(countryCode))
                .bankCode(bankCode)
                .accountNumber(accountNumber)
                .build();
        return iban.toString();
    }

    @Override
    public String generateIban(String countryCode) {
        return Iban.random(CountryCode.valueOf(countryCode)).toString();
    }

    @Override
    public List<CountryCode> getCountryCodes() {
        return Arrays.asList(CountryCode.values());
    }

    @Override
    public void validateCountryCodes(String countryCode) {
        CountryCode.valueOf(countryCode);
    }
}
