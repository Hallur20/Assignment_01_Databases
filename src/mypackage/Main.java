package mypackage;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    static String path = System.getProperty("user.dir") + "\\Files\\binaryFile.txt";
    static Scanner scanner = new Scanner(System.in);
    static String instructions = "welcome! instructions:\n\ttype 'get all' to find all data\n\ttype 'get <key value>' to find a key-row!\n\ttype 'set <key value> <name value> <age value>' to create something!\n\ttype 'instructions' to see instructions again\n\ttype 'hashmap' to see the keys and their offsets in the binary file\n\ttype 'exit' to close program";

    public static void main(String[] args) throws IOException {
        Logic logic = new Logic();
        logic.fileChecker(path); //first check if file exists, if there is data set key and offsets in hashmap
        System.out.println(instructions);
        while (true) {
            try {
                String s = scanner.nextLine();
                String[] command = s.split(" ");
                if (s.equals("get all")) {
                    logic.getAll(path);
                    continue;
                }
                if (s.startsWith("get")) {
                    String key = command[1];
                    logic.getRow(key, path);
                }
                if (s.startsWith("set")) {
                    String key = command[1];
                    String name = command[2];
                    int age = Integer.parseInt(command[3]);
                    logic.set(key, name, age, path);
                }
                if(s.equals("instructions")){
                    System.out.println(instructions);
                }
                if(s.equals("hashmap")){
                    logic.showHashMap();
                }
                if (s.equals("exit")) {
                    System.out.println("bye...");
                    break;
                }
            } catch (Exception e) {
                System.out.println("you did something wrong, try again");
            }
        }
    }
}
