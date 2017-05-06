package com.iban.controller;

import com.iban.config.TestContext;
import com.iban.service.IbanService;
import org.iban4j.CountryCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class IbanControllerTest {

    private static final String IBAN = "AT611904300234573201";
    private static final String COUNTRY_CODE_AT = "AT";
    private static final String BANK_CODE = "19043";
    private static final String ACCOUNT_NUMBER = "00234573201";

    private static final String IBAN_RESULT_0 = "{\"iban\": \"AT611904300234573201\"}";
    private static final String IBAN_RESULT_1 = "{\"iban\": \"AT611904300234573201\"}";
    private static final String IBAN_RESULT_2 = "[\"AD\"]";

    private static final String INCORRECT_COUNTRY_CODE = "FE";

    private static final List<CountryCode> COUNTRIES = Arrays.asList(CountryCode.AD);

    private MockMvc mockMvc;

    @Autowired
    private IbanService ibanService;

    @InjectMocks
    private IbanController ibanController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.reset(ibanService);

        ibanController = new IbanController(ibanService);
        mockMvc = MockMvcBuilders.standaloneSetup(ibanController).build();
    }

    @Test
    public void generateIban() throws Exception {
        when(ibanService.generateIban()).thenReturn(IBAN);

         mockMvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
         .andExpect(content().json(IBAN_RESULT_0));
    }

    @Test
    public void generateIbanByCountryCodeAndBankCodeAndAccountNumber() throws Exception {
        when(ibanService.generateIban(COUNTRY_CODE_AT, BANK_CODE, ACCOUNT_NUMBER)).thenReturn(IBAN);

        mockMvc.perform(get("/" + COUNTRY_CODE_AT + "/" + BANK_CODE + "/" + ACCOUNT_NUMBER)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(IBAN_RESULT_1));
    }

    @Test
    public void generateIbanByCountryCode() throws Exception {
        when(ibanService.generateIban(COUNTRY_CODE_AT)).thenReturn(IBAN);

        mockMvc.perform(get("/" + COUNTRY_CODE_AT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(IBAN_RESULT_0));
    }

    @Test
    public void countryCode() throws Exception {
        when(ibanService.getCountryCodes()).thenReturn(COUNTRIES);

        mockMvc.perform(get("/countryCode")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(IBAN_RESULT_2));
    }

}