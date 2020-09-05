package com.alipour.product.services;

import com.alipour.product.models.MarketStatus;
import com.alipour.product.repositories.MarketStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MarketStatusService {
    private MarketStatusRepository repository;

    public MarketStatusService(MarketStatusRepository repository) {
        this.repository = repository;
    }


    public void add(String marketCode, MarketStatus marketStatus) {
        marketStatus.setMarket(marketCode);
        repository.save(marketStatus);
    }

    public List<MarketStatus> findByMarket(String marketCode) {
        return repository.findByMarket(marketCode);
    }
}
