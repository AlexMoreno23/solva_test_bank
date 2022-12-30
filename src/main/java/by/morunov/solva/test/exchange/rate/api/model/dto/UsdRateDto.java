package by.morunov.solva.test.exchange.rate.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Alex Morunov
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsdRateDto implements Serializable {


    @NotNull
    private String symbol;
    @NotNull
    private BigDecimal rate;

}
