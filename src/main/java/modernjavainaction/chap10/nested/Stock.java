package modernjavainaction.chap10.nested;

public class Stock {
    private String symbol;
    public static Stock stock(String symbol, String market) {
//        stock("IBM", on("NYSE"))
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        return stock;
    }

    private void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static String on(String market) {
        return market;
    }

    public static double at(double price) {
        return price;
    }
}
