package by.anthony.model;

import java.util.Scanner;

public class GameSpeaker {

    private Scanner scanner;

    public GameSpeaker() {
        scanner = new Scanner(System.in);
    }

    public int defineSize() {
        int size;
        System.out.println("Enter the size of table");
        while (!scanner.hasNextInt()) {
            System.out.println("Sorry, you has entered not an integer");
            scanner.next();
        }
        size = scanner.nextInt();
        return size;
    }

    public void destroy(){
        scanner.close();
    }

}
