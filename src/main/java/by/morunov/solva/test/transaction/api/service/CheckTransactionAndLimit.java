package by.morunov.solva.test.transaction.api.service;

import by.morunov.solva.test.transaction.api.model.entity.Transaction;

/**
 * @author Alex Morunov
 */

public interface CheckTransactionAndLimit {

    Transaction checkLimit(Transaction transaction);

    boolean checkCategory(Transaction transaction);

    Transaction limitCalculation(Transaction transaction);

    Transaction checkLimitExceeded(Transaction transaction);




}
