package by.morunov.solva.test.exchange.rate.api;


import by.morunov.solva.test.exchange.rate.api.model.dto.UsdRateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Alex Morunov
 */

@Slf4j
@Component
public class ExchangeRequestApi {

    @Value("${exchange.key}")
    private String URL;

    @Autowired
    private RestTemplate restTemplate;


    public UsdRateDto getCourse(){
        ResponseEntity<UsdRateDto> response = restTemplate.getForEntity(URL, UsdRateDto.class);
        return response.getBody();

    }








}
