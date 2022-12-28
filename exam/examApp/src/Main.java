import java.util.Base64;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("started");
        int counter = 0;
        while (true) {
            Thread.sleep(1_000);
            counter++;
            if (counter % 60 == 0) {
                System.out.println("Another minute passed");
            }
        }

    }
}
