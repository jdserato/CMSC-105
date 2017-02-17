package Lab01;

import java.util.*;

/**
 * Created by Serato and Amora on 2/17/17.
 */
public class SeratoAndAmora {

    public static void main(String[] args) {
        ArrayList<String> sampleFrame = new ArrayList<>();
        int N;
        InputGathering gather = new InputGathering();
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.print("Basic Sampling Methods\n" +
                    "1. Simple Random Sampling\n" +
                    "2. Systematic Sampling\n" +
                    "3. Stratified Sampling\n" +
                    "4. Quit\n" +
                    "Please enter choice: ");
            choice = sc.nextInt();
        } while (choice < 1 || choice > 4);

        if (choice != 4) {
            N = gather.getPopulation();
            sampleFrame = gather.gather();
        }

        switch (choice) {
            case 1:
                SimpleRandomSampling srs = new SimpleRandomSampling(sampleFrame);
                ArrayList<String> sample = srs.getSample();
                for(String name : sample) {
                    System.out.println(name);
                }
                break;
        }
    }
}
