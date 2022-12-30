package by.morunov.solva.test.transaction.api.repositories;

import by.morunov.solva.test.transaction.api.model.entity.UsdLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex Morunov
 */
@Repository
public interface LimitRepo extends JpaRepository<UsdLimit, Long> {

    @Query(value = "SELECT * FROM usd_limit l JOIN transaction t ON t.usd_limit_id = l.id WHERE t.account_from = ?1"
            , nativeQuery = true)
    List<UsdLimit> findAllByAccountFrom(String account);

    @Query(value = "SELECT * FROM usd_limit l JOIN transaction t ON t.usd_limit_id = l.id\n" +
            "JOIN transaction_category ulc on t.id = ulc.transaction_id \n" +
            "WHERE t.account_from = ?1 and ulc.category = ?2 ORDER BY l.id desc limit 1"
            , nativeQuery = true)

//    @Query(value = "SELECT * FROM usd_limit l JOIN usd_limit_category ulc ON l.id = ulc.usd_limit_id\n" +
//            "\n" +
//            "JOIN transaction t ON l.id = t.usd_limit_id\n" +
//            "WHERE t.account_from = ?1 AND ulc.category = ?2\n" +
//            "ORDER BY l.id desc limit 1", nativeQuery = true)
    UsdLimit findLastUsdLimitByAccountByCategory(String account, String category);

}


