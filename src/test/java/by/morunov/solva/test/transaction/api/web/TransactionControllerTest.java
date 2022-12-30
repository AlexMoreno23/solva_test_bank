package by.morunov.solva.test.transaction.api.web;

import by.morunov.solva.test.transaction.api.model.entity.Transaction;
import by.morunov.solva.test.transaction.api.repositories.TransactionRepo;
import by.morunov.solva.test.transaction.api.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alex Morunov
 */
@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @InjectMocks
    TransactionController transactionController;

    @Mock
    TransactionRepo transactionRepo;

    @Mock
    TransactionService transactionService;

    @Test
    void saveTransaction() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        Transaction transaction = new Transaction();
        ResponseEntity<Transaction> response = transactionController.saveTransaction(transaction);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);


    }
}