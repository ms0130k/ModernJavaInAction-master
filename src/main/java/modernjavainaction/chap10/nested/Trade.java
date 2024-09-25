package modernjavainaction.chap10.nested;

public class Trade {
    private int quantity;
//    private
//    private Trade() {
//
//    }
    public static Trade buy(int quantity, Stock stock, double price) {
        return new Trade();
    }

    public static Trade sell(int quantity, Stock stock, double price) {
        return null;
    }
}
