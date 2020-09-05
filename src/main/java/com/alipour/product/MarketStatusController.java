package com.alipour.product;

import com.alipour.product.models.MarketStatus;
import com.alipour.product.services.MarketStatusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/market-status")
public class MarketStatusController {

    private MarketStatusService service;

    public MarketStatusController(MarketStatusService service) {
        this.service = service;
    }

    @GetMapping("/market/{code}")
    public List<MarketStatus> getByMarket(@PathVariable("code") String code){
        return service.findByMarket(code);
    }
}

