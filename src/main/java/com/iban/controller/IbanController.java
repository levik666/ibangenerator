package com.iban.controller;

import com.iban.dto.Message;
import com.iban.dto.RequestIbanDto;
import com.iban.service.IbanService;
import org.iban4j.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Message iban(@RequestBody @Valid RequestIbanDto requestIbanDto) {
        Message message = new Message();
        message.setIban(ibanService.generateIban(requestIbanDto));
        return message;
    }

    @RequestMapping(value = "/countryCode", method = RequestMethod.GET)
    public List<CountryCode> countryCode() {
        return ibanService.getCountryCodes();
    }
}
