import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int squirrels = scanner.nextInt();
        int nuts = scanner.nextInt();
        int nutsLeft = nuts % squirrels;
        System.out.println(nutsLeft);

        scanner.close();
    }
}