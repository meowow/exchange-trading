package vovandev.exchangetrading.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vovandev.exchangetrading.entity.Candlestick;
import vovandev.exchangetrading.model.TradeResponse;
import vovandev.exchangetrading.repository.CandlestickRepository;

import java.util.List;

@Service
public class CandlesSavePhase {

    @Autowired
    PairConverterService converter;
    @Autowired
    CandlestickRepository candlestickRepository;

    private Logger logger = LoggerFactory.getLogger(CandlesSavePhase.class);

    public void extractAndSave(List<TradeResponse> tradeResponses) {
        List<Candlestick> japanCandles = converter.getJapanCandlesticks(tradeResponses);
        if (japanCandles == null || japanCandles.isEmpty()) {
            logger.warn("The treading response is missing. Saving skipped");
            return;
        }

        candlestickRepository.saveAll(japanCandles);
    }
}
