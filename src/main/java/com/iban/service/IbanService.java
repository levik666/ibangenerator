package com.iban.service;

import org.iban4j.CountryCode;

import java.util.List;

public interface IbanService {

    String generateIban();

    String generateIban(String countryCode, String bankCode, String accountNumber);

    String generateIban(String countryCode);

    List<CountryCode> getCountryCodes();

    void validateCountryCodes(String countryCode);
}
