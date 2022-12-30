package by.morunov.solva.test.exchange.rate.api.service.impl;


import by.morunov.solva.test.exchange.rate.api.ExchangeRequestApi;
import by.morunov.solva.test.exchange.rate.api.model.dto.UsdRateDto;
import by.morunov.solva.test.exchange.rate.api.model.entity.UsdRate;
import by.morunov.solva.test.exchange.rate.api.repository.UsdRateRepo;
import by.morunov.solva.test.exchange.rate.api.service.UsdRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;


/**
 * @author Alex Morunov
 */
@Service
@Transactional
public class UsdRateServiceImplements implements UsdRateService {


    @Autowired
    private UsdRateRepo usdCourseRepo;

    @Autowired
    private ExchangeRequestApi courseRequestApi;



    private UsdRate getUsdRateFromDto(UsdRateDto usdRateDto){
        UsdRate usdRate = new UsdRate();
        usdRate.setRate(usdRateDto.getRate());
        usdRate.setSymbol(usdRateDto.getSymbol());
        return usdRate;
    }


    @PostConstruct
    @Scheduled(cron = "0 0 9,15,19 * * MON-FRI")
    @Override
    public void saveRateToDb() {
        usdCourseRepo.save(getUsdRateFromDto(courseRequestApi.getCourse()));
    }

    @Override

    public UsdRate getUsdRate() {
        return usdCourseRepo.findTopByOrderByIdDesc();
    }


}




