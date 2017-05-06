package com.iban.controller;

import com.iban.dto.ErrorDto;
import com.iban.dto.MessageDTO;
import com.iban.service.IbanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.iban4j.CountryCode;
import org.iban4j.Iban4jException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(basePath = "/", value = "IBan", description = "Service for random IBan generator", produces = "application/json")
@RestController
public class IbanController {

    private IbanService ibanService;

    @Autowired
    public IbanController(IbanService ibanService) {
        this.ibanService = ibanService;
    }

    @ApiOperation(value = "Generate random IBan", notes = "Generate IBan in different provinces")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public MessageDTO generateIban() {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setIban(ibanService.generateIban());
        return messageDTO;
    }

    @ApiOperation(value = "Generate random IBan by country code & bank code & account number",
                notes = "Generate random IBan by country code & bank code & account number")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Fields are with validation errors"),
            @ApiResponse(code = 200, message = "") })
    @RequestMapping(value = "/{countryCode}/{bankCode}/{accountNumber}", method = RequestMethod.GET)
    public MessageDTO iban(@PathVariable("countryCode") String countryCode,
                           @PathVariable("bankCode") String bankCode,
                           @PathVariable("accountNumber") String accountNumber) {
        ibanService.validateCountryCodes(countryCode);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setIban(ibanService.generateIban(countryCode, bankCode, accountNumber));
        return messageDTO;
    }

    @ApiOperation(value = "Generate random IBan by country code", notes = "Generate IBan by country code")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Field are with validation errors"),
            @ApiResponse(code = 200, message = "") })
    @RequestMapping(value = "/{countryCode}", method = RequestMethod.GET)
    public MessageDTO iban(@PathVariable("countryCode") String countryCode) {
        ibanService.validateCountryCodes(countryCode);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setIban(ibanService.generateIban(countryCode));
        return messageDTO;
    }

    @ApiOperation(value = "Return list  of country code", notes = "Return list of available country code")
    @RequestMapping(value = "/countryCode", method = RequestMethod.GET)
    public List<CountryCode> countryCode() {
        return ibanService.getCountryCodes();
    }

    @ExceptionHandler({Iban4jException.class, IllegalArgumentException.class})
    public ErrorDto handelException(Exception exe, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return new ErrorDto(exe.getMessage(), exe.getClass().getCanonicalName());
    }
}
