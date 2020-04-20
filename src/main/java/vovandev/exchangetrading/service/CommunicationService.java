package vovandev.exchangetrading.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class CommunicationService {

    @Value("${app.exchange.url}")
    private String URL;

    @Autowired
    private ExchangeMessageHandler handler;

    Logger logger = LoggerFactory.getLogger(CommunicationService.class);

    public void establishConnection() {
        try {
            if (URL == null || URL.isEmpty()) {
                logger.error("Connection error. Urs is missing");
                return;
            }
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
