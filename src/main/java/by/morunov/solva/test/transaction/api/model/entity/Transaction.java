package by.morunov.solva.test.transaction.api.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    @ApiModelProperty(notes = "Transaction ID", example = "1", required = true)
    private Long id;

    @NotNull
    @Column(name = "account_from", nullable = false, length = 10)
    @Pattern(regexp = "^[0-9]{10}")
    @ApiModelProperty(notes = "Account ID. Only numbers! 10 symbols! ", example = "0000000001", required = true)
    private String accountFrom;

    @NotNull
    @Column(name = "account_to", nullable = false, length = 10)
    @Pattern(regexp = "^[0-9]{10}")
    @ApiModelProperty(notes = "Account ID. Only numbers! 10 symbols! ", example = "0000000001", required = true)
    private String accountTo;

    @NotBlank
    @Column(name = "currency_short_name", nullable = false, length = 5)
    @ApiModelProperty(notes = "Currency short name", example = "KZT", required = true)
    private String currencyShortName;

    @Column(name = "category")
    @ElementCollection(targetClass = Category.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Category of spending", example = "PRODUCT", required = true)
    private Set<Category> category;

    @Column(name = "sum")
    @ApiModelProperty(notes = "Transfer amount", example = "2000", required = true)
    private BigDecimal transactionAmount;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "usdLimit_id")
    @JsonBackReference
    private UsdLimit usdLimit;

    @Column(name = "datetime", nullable = false)
    private ZonedDateTime datetime;

    @PrePersist
    public void setDateTime() {
        datetime = ZonedDateTime.now();
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount.setScale(2, RoundingMode.CEILING);
    }


}
