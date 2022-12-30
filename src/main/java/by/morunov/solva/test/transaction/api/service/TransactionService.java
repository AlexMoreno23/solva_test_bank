package by.morunov.solva.test.transaction.api.service;

import by.morunov.solva.test.transaction.api.model.entity.Transaction;
import by.morunov.solva.test.transaction.api.model.entity.UsdLimit;
import by.morunov.solva.test.transaction.api.repositories.LimitRepo;
import by.morunov.solva.test.transaction.api.repositories.TransactionRepo;
import by.morunov.solva.test.transaction.api.service.converter.CustomConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Alex Morunov
 */
@Service
@Transactional
@Slf4j
public class TransactionService implements CheckTransactionAndLimit {

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    CustomConverter converter;

    @Autowired
    LimitRepo limitRepo;

    public void saveTransaction(Transaction transaction) {
        if (transaction.getCurrencyShortName().equals("KZT")) {
            transactionRepo.save(checkLimit(transaction));
            log.info("add transaction from account:" + transaction.getAccountFrom());
        }
    }

    public List<UsdLimit> getAllLimits(String account) {
        return limitRepo.findAllByAccountFrom(account);
    }


    @Override
    public Transaction checkLimit(Transaction transaction) {
        if (transaction.getUsdLimit() == null) {
            UsdLimit usdLimitLast = getLastLimitByAccountByCategory(transaction.getAccountFrom(),
                    transactionCategory(transaction));
            UsdLimit usdLimit = new UsdLimit();
            transaction.setUsdLimit(usdLimit);
            if (usdLimitLast != null) {
                transaction.getUsdLimit().setRemainingLimit(usdLimitLast.getRemainingLimit()
                        .subtract(converter.convertToUsd(
                                transaction.getTransactionAmount()
                        )));
                checkLimitExceeded(transaction);
            } else {
                transaction.setUsdLimit(usdLimit);
            }
        } else {
            limitCalculation(transaction);
            checkLimitExceeded(transaction);
        }
        return transaction;
    }


    @Override
    public boolean checkCategory(Transaction transaction) {
        return transaction.getCategory().equals(transaction.getUsdLimit().getCategory());
    }

    @Override
    public Transaction limitCalculation(Transaction transaction) {

        if (checkCategory(transaction)) {
            transaction.getUsdLimit().setRemainingLimit(transaction.getUsdLimit().getSumLimit()
                    .subtract(converter.convertToUsd(transaction.getTransactionAmount())));
        } else {
            UsdLimit usdLimit = limitRepo.findLastUsdLimitByAccountByCategory(transaction.getAccountFrom(),
                    transactionCategory(transaction));
            if (usdLimit.getCategory().equals(transaction.getCategory())) {
                transaction.getUsdLimit().setRemainingLimit(usdLimit.getRemainingLimit().subtract(
                        converter.convertToUsd(transaction.getTransactionAmount())
                ));
            } else {


            }
        }
        return transaction;
    }

    @Override
    public Transaction checkLimitExceeded(Transaction transaction) {
        if (transaction.getUsdLimit().getRemainingLimit().compareTo(BigDecimal.ZERO) <= 0) {
            transaction.getUsdLimit().setLimitExceeded(true);
        } else {
            transaction.getUsdLimit().setLimitExceeded(false);
        }
        return transaction;
    }

    public UsdLimit getLastLimitByAccountByCategory(String account, String category) {
        return limitRepo.findLastUsdLimitByAccountByCategory(account, category);
    }

    public String transactionCategory(Transaction transaction) {
        if (transaction.getCategory().toString().equals("[Category.PRODUCT]")) {
            return "PRODUCT";
        } else {
            return "SERVICE";
        }
    }
}
