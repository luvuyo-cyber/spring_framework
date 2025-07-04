import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        boolean pairEquals20 = (a + b == 20) || (a + c == 20) || (b + c == 20);
        System.out.println(pairEquals20);
    }
}