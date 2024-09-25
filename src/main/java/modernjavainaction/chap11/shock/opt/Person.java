package modernjavainaction.chap11.shock.opt;

import java.util.Optional;

public class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public String getCarInsuranceName() {
        return Optional.of(this)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .flatMap(Insurance::getName)
                .orElse("Unknown");
    }
}

/*
에러의 근원이다: NullPointerException은 자바에서 가장 흔히 발생하는 에러다.
코드를 어지럽힌다: 때로는 중첩된 null 확인 코드를 추가해야 하므로 null 때문에 코드 가독성이 떨어진다.
아무 의미가 없다: null은 아무 의미도 표현하지 않는다. 특히 정적 형식 언어에서 값이 없음을 표현하는 방법으로는 적절하지 않다.
자바 철학에 위배된다: 자바는 개발자로부터 모든 포인터를 숨겼다. 하지만 예외가 있는데 그것이 바로 null 포인터다.
형식 시스템에 구멍을 만든다: null은 무형식이며 정보를 포함하고 있지 않으므로 모든 참조 형식에 null을 할당할 수 있다. 이런 식으로 null이 할당되기 시작하면서 시스템의 다른 부분으로 null이 퍼졌을 때 애초에 null이 어떤 의미로 사용되었는지 알 수 없다.
 */
