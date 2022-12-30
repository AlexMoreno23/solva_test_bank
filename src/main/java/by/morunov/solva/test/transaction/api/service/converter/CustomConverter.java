package by.morunov.solva.test.transaction.api.service.converter;

import by.morunov.solva.test.exchange.rate.api.service.impl.UsdRateServiceImplements;
import by.morunov.solva.test.transaction.api.model.dto.TransactionDto;
import by.morunov.solva.test.transaction.api.model.dto.UsdLimitDto;
import by.morunov.solva.test.transaction.api.model.entity.Transaction;
import by.morunov.solva.test.transaction.api.model.entity.UsdLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Morunov
 */
@Component
public class CustomConverter {

    @Autowired
    private UsdRateServiceImplements usdRateServiceImplements;

    public BigDecimal convertToUsd(BigDecimal bigDecimal) {
        return bigDecimal.divide(usdRateServiceImplements.getUsdRate().getRate(), 2, RoundingMode.CEILING);
    }


    public Transaction convertTransactionFromDto(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setAccountFrom(transactionDto.getAccountFrom());
        transaction.setAccountTo(transactionDto.getAccountTo());
        transaction.setTransactionAmount(transactionDto.getTransactionAmount());
        transaction.setCategory(transactionDto.getCategory());
        transaction.setDatetime(transactionDto.getDatetime());
        transaction.setCurrencyShortName(transactionDto.getCurrencyShortName());
        return transaction;
    }

    public List<Transaction> convertTransactionListFromDto(List<TransactionDto> transactionDtoList) {
        return transactionDtoList.stream().map(this::convertTransactionFromDto)
                .collect(Collectors.toList());
    }

    public UsdLimit convertLimitFromDto(UsdLimitDto usdLimitDto) {
        UsdLimit usdLimit = new UsdLimit();
        usdLimit.setId(usdLimitDto.getId());
        usdLimit.setSumLimit(usdLimitDto.getSumLimit());
        usdLimit.setLimitExceeded(usdLimitDto.isLimitExceeded());
        usdLimit.setCategory(usdLimitDto.getCategory());
        usdLimit.setRemainingLimit(usdLimitDto.getRemainingLimit());
        usdLimit.setTimeCreateLimit(usdLimitDto.getTimeCreatedLimit());
        usdLimit.setTransactions(convertTransactionListFromDto(usdLimitDto.getTransactionDtos()));
        return usdLimit;
    }

    public List<UsdLimit> convertLimitListFromDto(List<UsdLimitDto> usdLimitDtoList) {
        return usdLimitDtoList.stream().map(this::convertLimitFromDto)
                .collect(Collectors.toList());
    }

}
