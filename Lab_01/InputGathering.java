package Lab01;

import java.util.*;
/**
 * Created by Serato & Amora on 2/17/17.
 */
public class InputGathering {
    private int N;
    private ArrayList<String> sampleFrame;
    private Scanner sc = new Scanner(System.in);

    public ArrayList<String> gather() {
        do {
            System.out.print("Please enter the population size (N): ");
            N = sc.nextInt();
        } while (N < 0);

        System.out.println("Please enter the members of the sampling frame: ");
        sampleFrame = new ArrayList<>();
        sc.nextLine(); // acts as precaution
        for (int i = 1; i <= N; i++) {
            System.out.print(i + ". ");
            String newItem = sc.nextLine();
            sampleFrame.add(newItem);
        }
        return sampleFrame;
    }

    public int getPopulation() {
        return N;
    }
}
