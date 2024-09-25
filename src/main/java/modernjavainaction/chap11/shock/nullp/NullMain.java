package modernjavainaction.chap11.shock.nullp;

public class NullMain {
    public static void main(String[] args) {
        Car car = new Car();
        Insurance insurance = car.getInsurance();
        insurance.getName();
    }
}
