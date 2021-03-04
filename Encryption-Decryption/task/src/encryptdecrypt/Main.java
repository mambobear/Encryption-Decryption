package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();
        int key = scanner.nextInt();
        System.out.println(encryptCaesar(message, key));
    }

    private static String encryptCaesar(String message, int key) {
        StringBuilder strb = new StringBuilder();

        key %= 26;
        char ch;
        for (int i = 0; i < message.length(); i++) {
            ch = Character.toLowerCase(message.charAt(i));
            if ('a' <= ch && ch <= 'z') {
                ch =  (char) ( 'a' + (ch - 'a' + key) % 26 );
            }
            strb.append(ch);
        }
        return strb.toString();
    }

    private static String encrypt(String message) {
        StringBuilder strb = new StringBuilder();

        char ch;
        for (int i = 0; i < message.length(); i++) {
            ch = Character.toLowerCase(message.charAt(i));
            if ('a' <= ch && ch <= 'z') {
                ch =  (char) ('a' + ((int) 'z') - ((int) ch));
            }
            strb.append(ch);
        }
        return strb.toString();
    }
}
