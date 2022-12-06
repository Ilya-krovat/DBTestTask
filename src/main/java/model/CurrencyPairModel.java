package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CurrencyPairModel {

    private Integer id;
    private String base_charcode;
    private String quoted_charcode;
    private String description;
}
