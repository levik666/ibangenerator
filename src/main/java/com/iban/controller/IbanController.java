package com.iban.controller;

import com.iban.dto.ErrorDto;
import com.iban.dto.Message;
import com.iban.service.IbanService;
import org.iban4j.CountryCode;
import org.iban4j.IbanFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IbanController {

    private IbanService ibanService;

    @Autowired
    public IbanController(IbanService ibanService) {
        this.ibanService = ibanService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Message generateIban() {
        Message message = new Message();
        message.setIban(ibanService.generateIban());
        return message;
    }

    @RequestMapping(value = "/{countryCode}/{bankCode}/{accountNumber}", method = RequestMethod.GET)
    public Message iban(@PathVariable("countryCode") String countryCode,
                        @PathVariable("bankCode") String bankCode,
                        @PathVariable("accountNumber") String accountNumber) {
        ibanService.validateCountryCodes(countryCode);
        Message message = new Message();
        message.setIban(ibanService.generateIban(countryCode, bankCode, accountNumber));
        return message;
    }

    @RequestMapping(value = "/countryCode", method = RequestMethod.GET)
    public List<CountryCode> countryCode() {
        return ibanService.getCountryCodes();
    }

    @ExceptionHandler({IbanFormatException.class, IllegalArgumentException.class})
    public ErrorDto handelException(Exception exe) {
        return new ErrorDto(exe.getMessage(), exe.getClass().getCanonicalName());
    }
}
