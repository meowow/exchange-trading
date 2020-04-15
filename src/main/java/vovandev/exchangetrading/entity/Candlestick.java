package vovandev.exchangetrading.entity;

import javax.persistence.*;

@Entity
@Table(name = "CANDLESTICKS")
public class Candlestick {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



}
