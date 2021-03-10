package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static final String MODE = "-mode";
    static String KEY = "-key";
    static final String DATA = "-data";
    static final String IN = "-in";
    static final String OUT = "-out";
    static final String ERROR = "err";

    public static void main(String[] args) {

        HashMap<String, String> params = new HashMap<>();

        params.put(MODE, "enc");
        params.put(KEY, "0");
        params.put(DATA, null);
        params.put(IN, null);
        params.put(OUT, null);
        params.put(ERROR, null);

        parseArguments(params, args);
        // printParams(params);

        String err = params.get(ERROR);
        if (err != null) {
            System.out.println(err);
            return;
        }

        if (params.get(DATA) == null) {
            String inFileName = params.get(IN);
            if (inFileName != null) {
                try {
                    String data = new String(Files.readAllBytes(Paths.get(inFileName)));
                    params.put(DATA, data);
                } catch (IOException e) {
                    System.out.println("Error: The file could not be opened");
                    return;
                }
            } else {
                params.put(DATA, "");
            }
        }

        int key;
        try {
            key = Integer.parseInt(params.get(KEY));
        } catch (NumberFormatException e) {
            System.out.println("Error: invalid format for key");
            return;
        }

        String result;
        String mode = params.get(MODE);
        if ("dec".equals(mode)) {
            key = -key;
        }

        result = encryptCaesar(params.get(DATA), key);
        String outFileName = params.get(OUT);
        if (outFileName == null) {
            System.out.println(result);
        } else {
            File file = new File(outFileName);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(result);
            } catch (IOException e) {
                System.out.println("Error: Error writing to file");
            }
        }
    }

    static void parseArguments(HashMap<String, String> params, String[] args) {
        int idx = 0;
        while (idx < args.length) {
            String name = args[idx];
            if (params.containsKey(name)) {
                idx++;
                if ( idx < args.length) {
                    if (params.containsKey(args[idx])) {
                        params.put(ERROR, String.format("Error: Invalid argument string, missing value for %s", name));
                        break;
                    }
                    params.put(name, args[idx]);
                    idx++;
                } else {
                    params.put(ERROR, String.format("Error: Invalid argument string, missing value for %s", name));
                    break;
                }
            } else  {
                params.put(ERROR, "Error: Invalid argument string");
                break;
            }
        }
    }

    private static void printParams(Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.printf("[%s, %s]\n", key, value);
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