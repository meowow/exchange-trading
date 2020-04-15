package vovandev.exchangetrading.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class CommunicationService {

    @Autowired
    private ExchangeMessageHandler handler;

    Logger logger = LoggerFactory.getLogger(CommunicationService.class);

    public static final String URL = "wss://www.bitmex.com/realtime";

    public void establishConnection() {
        try {
            // open websocket
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(URL));

            // add listener
            clientEndPoint.addMessageHandler(handler);

            // send message to websocket
            clientEndPoint.sendMessage("{\"op\": \"subscribe\", \"args\": [\"tradeBin1m\"]}");

        } catch (URISyntaxException ex) {
            logger.error("URISyntaxException exception: " + ex.getMessage());
        }
    }

}
