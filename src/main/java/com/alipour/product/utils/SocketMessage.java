package com.alipour.product.utils;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SocketMessage {
    private String method;
    private String[] params;
    private Long id;
}
