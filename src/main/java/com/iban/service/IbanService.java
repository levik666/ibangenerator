package com.iban.service;

import com.iban.dto.RequestIbanDto;
import org.iban4j.CountryCode;

import java.util.List;

public interface IbanService {

    String generateIban();

    String generateIban(RequestIbanDto requestIbanDto);

    List<CountryCode> getCountryCodes();
}
