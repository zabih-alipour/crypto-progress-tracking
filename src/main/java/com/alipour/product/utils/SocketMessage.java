package com.alipour.product.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SocketMessage {
    private String method;
    private String[] params;
    private Long id;
}
