package modernjavainaction.chap10.lambda;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String customer;
    private List<Trade> trades = new ArrayList<>();

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
    }
}
