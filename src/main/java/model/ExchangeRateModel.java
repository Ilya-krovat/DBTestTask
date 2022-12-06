package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ExchangeRateModel {
    private Integer id;
    private String rate_date;
    private String rate_value;
    private int currency_pair_id;
}
