package Lab01;

import java.util.Scanner;

/**
 * Created by Serato & Amora on 2/17/17.
 */
public class InputGathering {
    public static void main(String[] args) {
        System.out.print("Basic Sampling Methods\n" +
                "1. Simple Random Sampling\n" +
                "2. Systematic Sampling\n" +
                "3. Stratified Sampling\n" +
                "4. Quit\n" +
                "Please enter choice: ");
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            choice = sc.nextInt();
        } while (choice < 1 || choice > 4);


    }
}
