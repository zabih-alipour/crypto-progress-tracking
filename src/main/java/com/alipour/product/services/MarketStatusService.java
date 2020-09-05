package com.alipour.product.services;

import com.alipour.product.models.MarketStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MarketStatusService {
    public MarketStatus add(MarketStatus marketStatus){
      log.info(marketStatus.toString());
      return null;
    }
}
