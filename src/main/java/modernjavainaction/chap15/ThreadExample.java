package modernjavainaction.chap15;

import static modernjavainaction.chap15.Functions.f;
import static modernjavainaction.chap15.Functions.g;

class ThreadExample {

  public static void main(String[] args) throws InterruptedException {
    int x = 1337;
    Result result = new Result();

    Thread t1 = new Thread(() -> {
      int f = f(x);
      result.left = f;
    });
    Thread t2 = new Thread(() -> {
      int g = g(x);
      result.right = g;
    });

    t1.start();
    t2.start();
    t1.join(0);
    t2.join(0);
    System.out.println(result.left + " " + result.right);
  }

  static int f(int x) {
    return x + 2;
  }

  static int g(int x) {
    return x + 1;
  }

  static class Result {
    public int left;
    public int right;
  }
}
