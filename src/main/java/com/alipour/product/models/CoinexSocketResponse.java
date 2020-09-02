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
public class CoinexSocketResponse extends ParentEntity {
    private Market market;

    private Double last;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private BigDecimal volume;
    private Double sell_total;
    private Double buy_total;
    private BigDecimal deal;
}
