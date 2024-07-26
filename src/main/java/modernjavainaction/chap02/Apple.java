package modernjavainaction.chap02;

public class Apple {
    enum Color {
        GREEN,
        RED
    }

    private Color color;
    private int weight;
    private Inner inner;

    private Apple(Color color) {
        this.color = color;
        this.weight = (int) (Math.random() * 100);
        this.inner = new Inner();

    }

    public static Apple of(Color color) {
        return new Apple(color);
    }

    public Color getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public Inner getInner() {
        return inner;
    }

    class Inner implements Comparable<Inner> {
        @Override
        public int compareTo(Inner o) {
            return 0;
        }

    }

    @Override
    public String toString() {
        return "Apple{" +
                "color=" + color +
                ", weight=" + weight +
                ", inner=" + inner +
                '}';
    }
}
