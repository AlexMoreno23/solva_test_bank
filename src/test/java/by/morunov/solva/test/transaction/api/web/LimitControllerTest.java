package by.morunov.solva.test.transaction.api.web;

import by.morunov.solva.test.transaction.api.model.entity.Category;
import by.morunov.solva.test.transaction.api.model.entity.Transaction;
import by.morunov.solva.test.transaction.api.model.entity.UsdLimit;
import by.morunov.solva.test.transaction.api.repositories.TransactionRepo;
import by.morunov.solva.test.transaction.api.service.TransactionService;
import org.assertj.core.api.CollectionAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.ITERABLE;

/**
 * @author Alex Morunov
 */
@ExtendWith(MockitoExtension.class)
class LimitControllerTest {

    @InjectMocks
    LimitController limitController;

    @Mock
    TransactionRepo transactionRepo;

    @Mock
    TransactionService transactionService;

    @Test
    void getAllLimits() {
        Transaction transaction = new Transaction();
        UsdLimit usdLimit = new UsdLimit();
        usdLimit.setId(1L);
        usdLimit.setSumLimit(BigDecimal.valueOf(1000));
        usdLimit.setCategory(Collections.singleton(Category.PRODUCT));
        transaction.setId(1L);
        transaction.setAccountFrom("1111111111");
        transaction.setAccountTo("2222222222");
        transaction.setCurrencyShortName("KZT");
        transaction.setCategory(Collections.singleton(Category.PRODUCT));
        transaction.setTransactionAmount(BigDecimal.valueOf(1000));
        transaction.setUsdLimit(usdLimit);
        Transaction transaction2 = new Transaction();
        UsdLimit usdLimit2 = new UsdLimit();
        usdLimit2.setId(1L);
        usdLimit2.setSumLimit(BigDecimal.valueOf(1000));
        usdLimit2.setCategory(Collections.singleton(Category.PRODUCT));
        transaction2.setId(2L);
        transaction2.setAccountFrom("1111111111");
        transaction2.setAccountTo("2222222222");
        transaction2.setCurrencyShortName("KZT");
        transaction2.setCategory(Collections.singleton(Category.PRODUCT));
        transaction2.setTransactionAmount(BigDecimal.valueOf(1000));
        transaction2.setUsdLimit(usdLimit2);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        transactions.add(transaction2);

        ResponseEntity<List<UsdLimit>> result = limitController.getAllLimits("1111111111");

        assertThat(result.getStatusCodeValue()).isEqualTo(200);


    }
}