package spring.controllers;

import DB.DBQuery;
import model.CurrencyPairModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    private final DBQuery dbQuery;

    @Autowired
    public Controller(DBQuery dbQuery) {
        this.dbQuery = dbQuery;
    }


    @GetMapping
    public String getExchangeRate(@RequestParam("currencyPairId") int currencyPairId,
                                  @RequestParam("rateDate") String rateDate) {
        return dbQuery.getExchangeRate(currencyPairId, rateDate);
    }

    @GetMapping
    public String getCurrentExchangeRate(@RequestParam("currencyPairId") int currencyPairId) {
        return dbQuery.getCurrentExchangeRate(currencyPairId);
    }

    @GetMapping
    public List<CurrencyPairModel> getCurrencyPairs() {
        System.out.println("hello");
        return dbQuery.getCurrencyPairs();
    }

    @PostMapping
    public void addCurrencyPair(@RequestParam("firstCur") String firstCur,
                                @RequestParam("secondCur") String secondCur) {
        dbQuery.addCurrencyPair(firstCur, secondCur);
    }
}
