import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double distance = scanner.nextDouble();
        double hours = scanner.nextDouble();

        double averageSpeed = distance / hours;

        System.out.println(averageSpeed);

    }
}