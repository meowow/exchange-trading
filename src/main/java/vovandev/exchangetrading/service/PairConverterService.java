package vovandev.exchangetrading.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vovandev.exchangetrading.entity.Candlestick;
import vovandev.exchangetrading.model.DataItem;
import vovandev.exchangetrading.model.TradeResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PairConverterService {

    @Value("${app.pairs}")
    private List<String> pairs;

    Logger logger = LoggerFactory.getLogger(PairConverterService.class);

    public List<Candlestick> getJapanCandlesticks(List<TradeResponse> tradeResponse) {
        if (tradeResponse.isEmpty()) {
            logger.error("Trade response is empty");
            return List.of();
        }

        return extractCandles(tradeResponse);
    }

    private List<Candlestick> extractCandles(List<TradeResponse> tradeResponse) {
        return tradeResponse.stream()
                .filter(r -> r.getData() != null && !r.getData().isEmpty())
                .flatMap(r -> r.getData().stream())
                .filter(this::filterForTheNeededPairs)
                .map(this::createCandlestick)
                .collect(Collectors.toList());
    }

    private boolean filterForTheNeededPairs(DataItem dataItem) {
        /* Removing the dot at the 1 position */
        String symbolWithoutDot = dataItem.getSymbol().substring(1);
        return pairs.stream().anyMatch(p -> p.equalsIgnoreCase(symbolWithoutDot));
    }

    private Candlestick createCandlestick(DataItem dataItem) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Candlestick ret = new Candlestick();
        try {
            ret.setTimestamp(formatter.parse(dataItem.getTimestamp()));
        } catch (ParseException e) {
            logger.error("Parsing time stamp error {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
        ret.setLabel(dataItem.getSymbol());
        ret.setHigh(dataItem.getHigh());
        ret.setLow(dataItem.getLow());
        ret.setOpen(dataItem.getOpen());
        ret.setClose(dataItem.getClose());

        return ret;
    }
}
