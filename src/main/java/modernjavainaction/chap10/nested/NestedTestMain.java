package modernjavainaction.chap10.nested;

import static modernjavainaction.chap10.nested.Order.order;
import static modernjavainaction.chap10.nested.Stock.*;
import static modernjavainaction.chap10.nested.Trade.buy;
import static modernjavainaction.chap10.nested.Trade.sell;

public class NestedTestMain {
    public static void main(String[] args) {
        Order order = order("BigBank",
                buy(80,
                        stock("IBM", on("NYSE")), at(125.00)),
                sell(50,
                        stock("GOOGLE", on("NASDAQ")), at(375.00))
        );
    }
}
