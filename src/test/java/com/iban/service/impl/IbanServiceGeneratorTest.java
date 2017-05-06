package com.iban.service.impl;

import com.iban.service.IbanService;
import org.iban4j.CountryCode;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IbanServiceGeneratorTest {

    private static final String COUNTRY_CODE = "NL";
    private static final String COUNTRY_CODE_AT = "AT";
    private static final String BANK_CODE = "19043";
    private static final String ACCOUNT_NUMBER = "00234573201";
    private static final String IBAN = "AT611904300234573201";
    private static final int COUNTRY_SIZE = 250;
    private static final String INCORRECT_COUNTRY_CODE = "FE";

    private IbanService ibanService = new IbanServiceGenerator();

    @Test
    public void generateIban() throws Exception {
        final String iban = ibanService.generateIban();
        assertNotNull("Iban should not be null", iban);
    }

    @Test
    public void generateIbanByCountryCode() throws Exception {
        String iban = ibanService.generateIban(COUNTRY_CODE);
        String prefix = iban.substring(0, 2);
        assertEquals(COUNTRY_CODE, prefix);
    }

    @Test
    public void generateIBanByCountryCodeAndBankCodeAndAccountNumber() throws Exception {
        String iban = ibanService.generateIban(COUNTRY_CODE_AT, BANK_CODE, ACCOUNT_NUMBER);
        assertEquals(IBAN, iban);
    }

    @Test
    public void getCountryCodes() throws Exception {
        List<CountryCode> countryCodes = ibanService.getCountryCodes();
        assertNotNull(countryCodes);
        assertEquals(COUNTRY_SIZE, countryCodes.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateIncorrectCountryCodes() throws Exception {
        ibanService.validateCountryCodes(INCORRECT_COUNTRY_CODE);
    }

    @Test
    public void validateCorrectCountryCodes() throws Exception {
        ibanService.validateCountryCodes(COUNTRY_CODE);
    }

}