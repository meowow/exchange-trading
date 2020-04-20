package vovandev.exchangetrading.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vovandev.exchangetrading.model.TradeResponse;

import java.io.IOException;
import java.util.List;

@Service
public class ExchangeMessageHandler implements WebsocketClientEndpoint.MessageHandler {

    @Autowired
    private CandlesSavePhase candlesSavePhase;

    private Logger logger = LoggerFactory.getLogger(ExchangeMessageHandler.class);

    @Override
    public void handleMessage(String message) {
        List<TradeResponse> tradeResponses = getTradeResponse(message);
        candlesSavePhase.extractAndSave(tradeResponses);


    }

    /**
     *
     *  This method tries to unmarshal received message to the @{TradeResponse}
     *  If it receives non informative message it will log it and return empty list
     *
     * @param message The received message
     * @return The Data object from response message
     */
    private List<TradeResponse> getTradeResponse(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            return objectMapper.readValue(message, new TypeReference<>(){});
        } catch (IOException e) {
            logger.error("Probably parser skipping the non informative messages {}", e.getMessage(), e);
            return List.of();
        }
    }

}
