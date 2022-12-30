package by.morunov.solva.test.transaction.api.model.dto;

import by.morunov.solva.test.transaction.api.model.entity.Category;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author Alex Morunov
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsdLimitDto implements Serializable {

    private Long id;
    private BigDecimal sumLimit;
    private Set<Category> category;
    private ZonedDateTime timeCreatedLimit;
    private String currencyShortName;
    private boolean limitExceeded;
    private BigDecimal remainingLimit;
    private List<TransactionDto> transactionDtos;
}
