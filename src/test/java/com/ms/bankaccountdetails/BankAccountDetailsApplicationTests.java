package com.ms.bankaccountdetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.bankaccountdetails.controller.AccountController;
import com.ms.bankaccountdetails.controller.PaymentController;
import com.ms.bankaccountdetails.model.Account;
import com.ms.bankaccountdetails.repository.AccountRepository;
import com.ms.bankaccountdetails.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BankAccountDetailsApplicationTests {

    @Autowired
    private AccountRepository accountRepository;

    @Mock
    private AccountServiceImpl accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private AccountController accountController;


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PaymentController paymentController;

    @Test
    public void shouldNotAllowNullValues(){

        //test case fails
        accountRepository.save(new Account());

        //test case pass
        accountRepository.save(new Account(1L,"","","","",1.0));
    }

    @Test
    public void testGetAllAccounts() throws Exception {

        ClassPathResource resource = new ClassPathResource("account1.json");

        List<Account> accounts = Arrays.asList(
                new Account(1L, "USD", "Bank1", "Branch1", "123456", 1000.0),
                new Account(2L, "EUR", "Bank2", "Branch2", "789012", 500.0)
        );

        //mock the accountService method
        when(accountService.getAllAccounts()).thenReturn(accounts);

        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

        mockMvc.perform(get("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].currency").value("USD"))
                .andExpect(jsonPath("$[1].currency").value("EUR"));

        verify(accountService, times(1)).getAllAccounts();
        verifyNoMoreInteractions(accountService);

    }

    @Test
    public void testSaveAccount() throws Exception {

        Account account = new Account(12L,"INR","Axis","mau","9120534622",12345.0);
        String jsonData = objectMapper.writeValueAsString(account);
        mockMvc.perform(post("/api/accounts")
                .contentType("application/json")
                .content(jsonData))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.branch").value("mau"));
    }

//    @Test
    @ParameterizedTest
    @ValueSource(longs = {1,2,3,4})
    public void testAccountById(Long id) throws Exception {

        Account account = new Account(id, "USD", "Bank1", "Branch1", "123456", 1000.0);

        when(accountService.getAccountById(id)).thenReturn(account);
//        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

        //byte[] jsonPath = objectMapper.writeValueAsBytes(Files.readAllBytes(Path.of("account1.json")));
        mockMvc.perform(get("/api/accounts/"+id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
               // .andExpect(jsonPath("$.currency").value("USD"));

        //verify(accountService, times(1)).getAccountById(1L);
    }

    @Test
    public void testSendPaymentInstruction() throws Exception {

        String jsonData = objectMapper.writeValueAsString("paymentInstruction.json");
       // when(accountService.getAllAccounts()).thenReturn(accounts);

        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();

        mockMvc.perform(post("/api/payment/send-instruction")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData));
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//                .andExpect(jsonPath("").value(""));

    }
}
