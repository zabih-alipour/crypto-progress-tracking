package com.alipour.product.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MarketStatus extends ParentEntity {
    private String market;

    private Double last;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Double sell_total;
    private Double buy_total;
    private BigDecimal volume;
    private BigDecimal deal;
    private Long period;


    @Override
    public String toString() {
        return "MarketStatus{" +
                "market=" + market +
                ", last=" + last +
                ", open=" + open +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                '}';
    }
}
