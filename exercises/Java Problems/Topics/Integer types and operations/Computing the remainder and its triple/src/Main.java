import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        // The first operation goes here
        int remainder = input % 2;
        System.out.println(remainder);
        // The second operation goes here
        int result = remainder * 3;
        System.out.println(result);

        scanner.close();
    }
}