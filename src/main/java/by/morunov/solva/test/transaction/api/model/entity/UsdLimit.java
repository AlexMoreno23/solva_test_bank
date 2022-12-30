package by.morunov.solva.test.transaction.api.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author Alex Morunov
 */
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usd_limit")
public class UsdLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @ApiModelProperty(notes = "Limit ID", example = "1", required = true)
    private Long id;

    @Column(name = "sum_limit")
    @ApiModelProperty(notes = "Sum limit", example = "1000", required = true)
    private BigDecimal sumLimit = BigDecimal.valueOf(0);

    @Column(name = "category")
    @ElementCollection(targetClass = Category.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "Category of spending", example = "PRODUCT", required = true)
    private Set<Category> category;

    @Column(name = "timeCreateLimit")
    private ZonedDateTime timeCreateLimit;

    @Column(name = "currencyShortName")
    @ApiModelProperty(notes = "Currency short name. Dont  write!", required = true)
    private final String currencyShortName = "USD";

    @Column(name = "limitExceeded")
    @ApiModelProperty(notes = "Limit Exceeded. Dont write", required = true)
    private Boolean limitExceeded = false;

    @Column(name = "remainingLimit")
    @ApiModelProperty(notes = "Remaining Limit. Dont write", required = true)
    private BigDecimal remainingLimit = BigDecimal.valueOf(0);

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usdLimit")
    @JsonManagedReference
    @ToString.Exclude
    private List<Transaction> transactions;


    @PrePersist
    public void setDateTime() {
        timeCreateLimit = ZonedDateTime.now();
    }


}
