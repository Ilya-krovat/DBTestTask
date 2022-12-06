package spring;

import DB.DBQuery;
import model.CurrencyPairModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.APIService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class ScheduledTasks {

    private final DBQuery dbQuery;
    private APIService service = new APIService();

    @Autowired
    public ScheduledTasks(DBQuery dbQuery) {
        this.dbQuery = dbQuery;
    }


    @Scheduled(fixedRate = 3000)
    @Async
    public void updateExchangeRates() {
        List<CurrencyPairModel> listOfPairs = dbQuery.getCurrencyPairs();
        listOfPairs.forEach(x-> dbQuery.insertExchangeRate(new Timestamp(new Date().getTime()).toString(),
                service.getRate(x.getBase_charcode(), x.getQuoted_charcode()).toString(),
                x.getId()));
    }
}
