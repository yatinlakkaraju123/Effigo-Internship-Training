package repository;

import java.util.HashMap;
import java.util.Map;

import pojo.TradeAccount;

public class TradeAccountRepository {
    private Map<String, TradeAccount> datastore = new HashMap<>();

    public void createTradeAccount(TradeAccount tradeAccount) {
        TradeAccount clone = tradeAccount.clone();
        datastore.put(tradeAccount.getId(), clone);
    }

    public TradeAccount retrieveTradeAccount(String id) {
        if (this.datastore.get(id) == null) {
            return null;
        } else {
            return this.datastore.get(id).clone();
        }

    }
    public void updateTradeAccount(TradeAccount tradeAccount)
    {
        datastore.put(tradeAccount.getId(),tradeAccount.clone());
    }
    public void deleteTradeAccount(String id)
    {
        datastore.remove(id);
    }
}
