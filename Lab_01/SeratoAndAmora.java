package Lab01;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Semora on February 18, 2017
 */
public class SeratoAndAmora {
    private static InputAndOutput gather = new InputAndOutput();

    public static void main(String[] args) {
        ArrayList<String> sampleFrame = new ArrayList<>();
        ArrayList<String> sample;
        Sampling technique;
        int N = 0, n = 0;
        Scanner sc = new Scanner(System.in);
        int choice;
        boolean error = false;
        do {
            if (error) {
                for(int i = 0; i < 10; i++) {
                    System.out.println("\n");
                }
                System.out.println("Invalid input.");
            }
            System.out.print("Basic Sampling Methods\n" +
                    "1. Simple Random Sampling\n" +
                    "2. Systematic Sampling\n" +
                    "3. Stratified Sampling\n" +
                    "4. Quit\n" +
                    "Please enter choice: ");
            try {
                if (error) {
                    sc.nextLine();
                }
                choice = sc.nextInt();
                if (choice < 1 || choice > 4) {
                    error = true;
                }
            } catch (InputMismatchException e) {
                choice = 0;
                error = true;
            }
        } while (choice < 1 || choice > 4);



        if (choice != 4) {
            error = false;
            int choice1;
            do {
                System.out.println();
                if (error) {
                    for(int i = 0; i < 5; i++) {
                        System.out.println("\n");
                    }
                    System.out.println("Invalid input.");
                }
                System.out.print("Choice of data input:\n" +
                        "1. Manual Input\n" +
                        "2. From Existing File\n" +
                        "Please enter choice: ");
                try {
                    if (error) {
                        sc.nextLine();
                    }
                    choice1 = sc.nextInt();
                    if (choice1 < 1 || choice1 > 2) {
                        error = true;
                    }
                } catch (InputMismatchException e) {
                    choice1 = 0;
                    error = true;
                }
            } while (choice1 < 1 || choice1 > 2);

            sampleFrame = gather.gather((choice1 == 1));
            N = gather.getPopulation();
            if(sampleFrame == null) {
                System.out.println("Press [ENTER] to continue.");
                sc.nextLine();
                sc.nextLine();
                for(int i = 0; i < 10; i++) {
                    System.out.println();
                }
                main(null);
            }
        }
        switch (choice) {
            case 1:
                technique = new SimpleRandomSampling(sampleFrame);
                break;
            case 2:
                technique = new SystematicSampling(sampleFrame);
                break;
            case 3:
                technique = new StratifiedSampling(sampleFrame);
                break;
            default:
                return;
        }

        gather.printFrame(sampleFrame, "THE SAMPLING FRAME:");

        if (technique instanceof NeedsSampleSize) {
            String n1 = null;
            error = false;
            do {
                sc.nextLine();
                if (error) {
                    for(int i = 0; i < 5; i++) {
                        System.out.println();
                    }
                    System.out.println("Invalid input.");
                }
                System.out.print("Please enter the desired sample size (n).\n" +
                        "If you want the default value to be used, please press [ENTER]: ");
                try {
                    n1 = sc.nextLine();
                    n = Integer.parseInt(n1);
                } catch (InputMismatchException | NumberFormatException e) {
                    if (n1 != null && n1.length() > 0) {
                        error = true;
                    }
                }
            } while (n1 != null && n1.length() > 0 && error && (n >= N || n <= 0));
            if (n1 == null || n1.length() == 0) {
                sample = technique.getSample();
            } else {
                sample = technique.getSample(n);
            }
            gather.printFrame(sample, technique.getHeader());
        } else {
            technique.getSample();
        }
        System.out.println("Press [ENTER] to continue.");
        sc.nextLine();
        for(int i = 0; i < 10; i++) {
            System.out.println("\n");
        }
        main(null);
    }
}
