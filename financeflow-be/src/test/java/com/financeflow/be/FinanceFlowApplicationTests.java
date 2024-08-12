package com.financeflow.be;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.financeflow.be.core.exceptions.DefaultAccountNotFoundException;
import com.financeflow.be.services.implementations.DefaultAccountService;
import com.financeflow.be.services.implementations.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FinanceFlowApplicationTests {

	@Test
	void contextLoads() throws DefaultAccountNotFoundException {
		DefaultAccountService accountService =  new DefaultAccountService();
		assert(accountService.changeCurrency("EUR").equals("Currency successfully changed."));
	}
}
