package com.iban.service.impl;

import com.iban.dto.RequestIbanDto;
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
    public String generateIban(RequestIbanDto requestIbanDto) {
        Iban iban = new Iban.Builder()
                .countryCode(requestIbanDto.getCountryCode())
                .bankCode(requestIbanDto.getBankCode())
                .accountNumber(requestIbanDto.getAccountNumber())
                .build();
        return iban.toString();
    }

    @Override
    public List<CountryCode> getCountryCodes() {
        return Arrays.asList(CountryCode.values());
    }
}
