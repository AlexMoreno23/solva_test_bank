package by.morunov.solva.test.transaction.api.model.dto;

import by.morunov.solva.test.transaction.api.model.entity.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * @author Alex Morunov
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDto implements Serializable {
    private Long id;
    @NotNull
    @Pattern(regexp = "^[0-9]{10}")
    private String accountFrom;
    @NotNull
    @Pattern(regexp = "^[0-9]{10}")
    private String accountTo;
    @NotBlank
    private String currencyShortName;
    private Set<Category> category;
    private BigDecimal transactionAmount;
    private UsdLimitDto usdLimit;
    private ZonedDateTime datetime;




}
