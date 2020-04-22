package vovandev.exchangetrading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vovandev.exchangetrading.entity.Candlestick;

import java.util.List;

@Repository
public interface CandlestickRepository extends JpaRepository<Candlestick, Long> {
    List<Candlestick> findAllByOrderByTimestampDesc();
    // Get the last 2 inserted because we have only two pairs hardcoded in the config
    List<Candlestick> findTop2ByOrderByTimestampDesc();
}
