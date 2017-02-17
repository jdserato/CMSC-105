import java.util.*;
/**
 * Created by Serato & Amora on 2/17/17.
 */
public class InputGathering {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        int N;
        ArrayList<String> sampleFrame;
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
            do {
                System.out.print("Please enter the population size (N): ");
                N = sc.nextInt();
            } while (N < 0);

            System.out.println("Please enter the members of the sampling frame: ");
            sampleFrame = new ArrayList<>();
            sc.nextLine(); // acts as precaution
            for(int i = 1; i <= N; i++) {
                System.out.print(i + ". ");
                String newItem = sc.nextLine();
                sampleFrame.add(newItem);
            }
        }

    }
}
