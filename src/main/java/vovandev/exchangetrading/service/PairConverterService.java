package vovandev.exchangetrading.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vovandev.exchangetrading.model.Candlestick;
import vovandev.exchangetrading.model.TradeResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class PairConverterService {

    @Value("${app.pairs}")
    private List<String> pairs;

    Logger logger = LoggerFactory.getLogger(PairConverterService.class);

    public List<Candlestick> getJapanCandlesticks(List<TradeResponse> tradeResponse) {
        if (!tradeResponse.isEmpty()) {
            logger.error("Trade response is empty");
            return List.of();
        }
        List<Candlestick> ret = new ArrayList<>();



        return ret;
    }
}
