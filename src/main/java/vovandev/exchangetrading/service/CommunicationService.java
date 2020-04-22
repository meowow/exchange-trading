package vovandev.exchangetrading.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vovandev.exchangetrading.entity.Candlestick;
import vovandev.exchangetrading.repository.CandlestickRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class CommunicationService {

    @Value("${app.exchange.url}")
    private String BITMEX_URL;

    private ExchangeMessageHandler handler;
    private CandlestickRepository candlestickRepository;

    public CommunicationService(ExchangeMessageHandler handler, CandlestickRepository candlestickRepository) {
        this.handler = handler;
        this.candlestickRepository = candlestickRepository;
    }

    Logger logger = LoggerFactory.getLogger(CommunicationService.class);

    public void establishConnection() {
        try {
            if (BITMEX_URL == null || BITMEX_URL.isEmpty()) {
                logger.error("Connection error. Urs is missing");
                return;
            }
            // open websocket
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(BITMEX_URL));

            // add listener
            clientEndPoint.addMessageHandler(handler);

            // send message to websocket
            clientEndPoint.sendMessage("{\"op\": \"subscribe\", \"args\": [\"tradeBin1m\"]}");

        } catch (URISyntaxException ex) {
            logger.error("URISyntaxException exception: " + ex.getMessage());
        }
    }

    public List<Candlestick> getAllCandles() {
        List<Candlestick> ret = candlestickRepository.findAllByOrderByTimestampDesc();
        return ret;
    }

    public List<Candlestick> getLastInsertedCandles() {
        List<Candlestick> ret = candlestickRepository.findTop2ByOrderByTimestampDesc();
        return ret;
    }

}
