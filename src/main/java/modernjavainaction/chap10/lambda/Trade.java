package modernjavainaction.chap10.lambda;

public class Trade {
    private Type type;
    private int quantity;
    private double price;
    private Stock stock;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public enum Type {BUY, SELL;}
    public void setType(Type type) {
        this.type = type;
    }
}
