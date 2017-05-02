package com.iban.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.iban4j.CountryCode;

import javax.validation.constraints.Pattern;

public class RequestIbanDto {
    @NotEmpty
    private CountryCode countryCode;
    @NotEmpty
    @Pattern(regexp="[0-9]+", message="Wrong bankCode!")
    private String bankCode;
    @NotEmpty
    @Pattern(regexp="[0-9]+", message="Wrong accountNumber!")
    private String accountNumber;

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
