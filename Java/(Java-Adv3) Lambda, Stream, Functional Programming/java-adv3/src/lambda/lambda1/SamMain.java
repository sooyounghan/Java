package lambda.lambda1;

public class SamMain {
    public static void main(String[] args) {
        SamInterface samInterface = () -> {
            System.out.println("sam");
        };

        samInterface.run();;

        /*
        NotSamInterface notSamInterface = () -> {
            System.out.println("Not sam");
        };

        notSamInterface.run();
        notSamInterface.go();
        */
    }
}
