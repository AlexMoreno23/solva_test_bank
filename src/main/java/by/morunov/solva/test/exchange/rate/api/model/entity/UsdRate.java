package by.morunov.solva.test.exchange.rate.api.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Alex Morunov
 */

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
public class UsdRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String symbol;
    @NotNull
    private BigDecimal rate;

    @NotNull
    private String time;

    @PrePersist
    public void setDateTime() {
        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z");
        time = zonedDateTimeNow.format(formatter2);

    }

}
