package by.morunov.solva.test.exchange.rate.api.service;


import by.morunov.solva.test.exchange.rate.api.model.entity.UsdRate;

/**
 * @author Alex Morunov
 */
public interface UsdRateService {

 void saveRateToDb();
 UsdRate getUsdRate();
}
