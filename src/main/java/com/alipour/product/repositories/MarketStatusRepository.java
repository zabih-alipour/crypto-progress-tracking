package com.alipour.product.repositories;

import com.alipour.product.models.MarketStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketStatusRepository extends MongoRepository<MarketStatus, String> {
    public List<MarketStatus> findByMarket(String marketCode);
}
