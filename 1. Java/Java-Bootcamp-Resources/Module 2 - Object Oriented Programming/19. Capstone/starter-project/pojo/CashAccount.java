package pojo;

import java.math.BigDecimal;



public class CashAccount extends TradeAccount{
 private BigDecimal cashBalance;

    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(BigDecimal cashBalance) {
        this.cashBalance = cashBalance;
    }
    public CashAccount(String id,BigDecimal cashBalance)
    {
        super(id);
        this.cashBalance = cashBalance;
    }
    public TradeAccount clone()
    {
        CashAccount obj = new CashAccount(getId(), getCashBalance());
        return obj;
    }

}
