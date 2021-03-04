package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String message = "we found a treasure!";
        System.out.println(encrypt(message));
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
