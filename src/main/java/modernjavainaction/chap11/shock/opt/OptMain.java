package modernjavainaction.chap11.shock.opt;

import java.util.Optional;

public class OptMain {
    public static void main(String[] args) {
        Person person = new Person();
        String carInsuranceName = person.getCarInsuranceName();
        System.out.println(carInsuranceName);

    }
}
