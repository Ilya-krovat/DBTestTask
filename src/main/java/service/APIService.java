package service;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class APIService {
    private final String API_URL = "https://currate.ru/api/";
    private final String API_KEY = "bce48a7fa5fed7bf2eed34e970254096";

    private RestTemplate restTemplate = new RestTemplate();

    public Float getRate(String firstCurrency, String secondCurrency) {
        String json = restTemplate.getForObject(API_URL+
                "?get=rates&pairs="+
                firstCurrency+
                secondCurrency+
                "&key="+
                API_KEY
                ,String.class);

        JSONObject obj = new JSONObject(json);
        String rateValue = obj.getJSONObject("data").getString(firstCurrency + secondCurrency);
        return Float.parseFloat(rateValue);
    }
}
