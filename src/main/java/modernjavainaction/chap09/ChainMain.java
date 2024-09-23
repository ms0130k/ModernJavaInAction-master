package modernjavainaction.chap09;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ChainMain {
    static abstract class ProcessingObj<T> {
        protected ProcessingObj<T> chain;

        public void setProcessingObj(ProcessingObj chain) {
            this.chain = chain;
        }

        public T handle(T t) {
            T r = work(t);
            if (chain != null) {
                return chain.handle(r);
            }
            return r;
        }

        abstract protected T work(T t);
    }

    public static void main(String[] args) {
        ProcessingObj<String> doub = new ProcessingObj<>() {
            @Override
            protected String work(String s) {
                return s + s;
            }
        };

        ProcessingObj<String> min = new ProcessingObj<>() {
            @Override
            protected String work(String s) {
                return s + "-";
            }
        };

        doub.setProcessingObj(min);

        String a = doub.handle("a");
        System.out.println(a);

        Function<String, String> doubler = s -> s + s;
        Function<String, String> minus = s -> s + "-";
        Function<String, String> doubleAndMinus = doubler.andThen(minus);
        String bb = doubleAndMinus.apply("bb");
        System.out.println(bb);
    }
}
