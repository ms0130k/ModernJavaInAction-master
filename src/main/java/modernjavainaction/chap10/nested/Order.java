package modernjavainaction.chap10.nested;

public class Order {
    public static Order order(String customer, Trade... trades) {
        Order order = new Order();
        return order;
    }
}
