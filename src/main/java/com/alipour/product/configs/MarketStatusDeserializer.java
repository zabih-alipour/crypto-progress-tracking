package com.alipour.product.configs;

import com.alipour.product.models.MarketStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MarketStatusDeserializer extends StdDeserializer<MarketStatus> {

    protected MarketStatusDeserializer() {
        super(MarketStatus.class);
    }

    @Override
    public MarketStatus deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        MarketStatus marketStatus = new MarketStatus();
        marketStatus.setOpen(node.get("open").asDouble());
        marketStatus.setClose(node.get("close").asDouble());
        marketStatus.setHigh(node.get("high").asDouble());
        marketStatus.setLow(node.get("low").asDouble());
        marketStatus.setLast(node.get("last").asDouble());
        marketStatus.setVolume(node.get("volume").decimalValue());
        marketStatus.setPeriod(node.get("period").asLong());
        return marketStatus;
//        return codec.treeToValue(node, MarketStatus.class);
    }
}
