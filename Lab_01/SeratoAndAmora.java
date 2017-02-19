package Lab01;

import java.util.*;

/**
 * Created by Semora on February 18, 2017
 */
public class SeratoAndAmora {
    static InputAndOutput gather = new InputAndOutput();

    public static void main(String[] args) {
        ArrayList<String> sampleFrame = new ArrayList<>();
        ArrayList<String> sample;
        Sampling technique;
        int N = 0, n;
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
            sampleFrame = gather.gather();
            N = gather.getPopulation();
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
            System.out.print("Please enter the desired sample size (n): ");
            n = sc.nextInt();
            if (n >= N || n <= 0) {
                System.out.println("Sorry but the sample size must be a positive integer or less than the population size." +
                        " The default value will be used.\n");
                sample = technique.getSample();
            } else {
                sample = technique.getSample(n);
            }
            gather.printFrame(sample, technique.getHeader());
            main(null);
        } else {
            technique.getSample();
        }

        /*switch (choice) {
            case 1:
                SimpleRandomSampling srs = new SimpleRandomSampling(sampleFrame);
                System.out.print("Please enter the desired sample size (n): ");
                n = sc.nextInt();
                if (n >= N || n <= 0) {
                    System.out.println("Sorry but the sample size must be a positive integer or less than the population size." +
                            " The default value will be used.\n");
                    sample = srs.getSample();
                } else {
                    sample = srs.getSample(n);
                }
                gather.printFrame(sample, "RANDOM SAMPLE");
                break;

            case 2:
                SystematicSampling ss = new SystematicSampling(sampleFrame);
                System.out.print("Please enter the desired sample size (n): ");
                n = sc.nextInt();
        }*/
    }
}
