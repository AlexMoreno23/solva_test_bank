package by.morunov.solva.test.transaction.api.repositories;

import by.morunov.solva.test.transaction.api.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alex Morunov
 */
@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {


}
