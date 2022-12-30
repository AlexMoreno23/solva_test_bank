package by.morunov.solva.test.exchange.rate.api.repository;

import by.morunov.solva.test.exchange.rate.api.model.entity.UsdRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alex Morunov
 */
@Repository
public interface UsdRateRepo extends JpaRepository<UsdRate, Long> {


    UsdRate findTopByOrderByIdDesc();
}
