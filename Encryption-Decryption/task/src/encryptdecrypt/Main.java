package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String op = scanner.nextLine();
        String message = scanner.nextLine();
        int key = scanner.nextInt();

        if ("enc".equals(op)) {
            System.out.println(encryptCaesar(message, key));
        } else if ("dec".equals(op)) {
            System.out.println(encryptCaesar(message, -key));
        }
    }

    private static String encryptCaesar(String message, int key) {
        int SIZE = 256;

        StringBuilder strb = new StringBuilder();

        key %= SIZE;
        char ch;
        for (int i = 0; i < message.length(); i++) {
            ch =  message.charAt(i);
            ch =  (char) ( 'a' + (ch - 'a' + key) % SIZE );
            strb.append(ch);
        }
        return strb.toString();
    }
}
