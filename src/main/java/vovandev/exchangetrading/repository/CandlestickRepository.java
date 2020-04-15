package vovandev.exchangetrading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vovandev.exchangetrading.entity.Candlestick;

@Repository
public interface CandlestickRepository extends JpaRepository<Candlestick, Long> {
}
