package Lab01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Semora on February 18, 2017
 */

class InputAndOutput {
    private int N;
    private int tabSize, maxLength;
    private ArrayList<String> sampleFrame;
    private Scanner sc = new Scanner(System.in);
    private boolean numeric;

    ArrayList<String> gather() {
        do {
            System.out.print("Please enter the population size (N): ");
            N = sc.nextInt();
        } while (N < 0);

        System.out.println("Please enter the members of the sampling frame: ");
        sampleFrame = new ArrayList<>();
        sc.nextLine(); // acts as precaution
        BufferedReader br = null;
        //try {
            //br = new BufferedReader(new FileReader("input.in"));
            for (int i = 1; i <= N; i++) {
                System.out.print(i + ". ");
                String newItem = sc.nextLine();
                if (i == 1) {
                    numeric = !((newItem.charAt(0) >= 'A' && newItem.charAt(0) <= 'Z') || (newItem.charAt(0) >= 'a' && newItem.charAt(0) <= 'z'));
                }
                boolean error = false;
                for (int j = 0; j < newItem.length(); j++) {
                    if (numeric) {
                        if (newItem.charAt(j) >= '0' && newItem.charAt(j) <= '9') {
                            //sampleFrame.add(newItem);
                        } else if (!error){
                            System.out.println("ERROR! Input must be numeric only!");
                            i--;
                            error = true;
                        }
                    } else {
                        if ((newItem.charAt(j) >= 'A' && newItem.charAt(j) <= 'Z') || (newItem.charAt(j) >= 'a' && newItem.charAt(j) <= 'z') || newItem.charAt(j) == ' ') {
                            //sampleFrame.add(newItem);
                        } else if (!error){
                            System.out.println("ERROR! Input must be character data only!");
                            i--;
                            error = true;
                        }
                    }
                }
                if (!error) {
                    sampleFrame.add(newItem);
                }
                //sampleFrame.add(br.readLine());
            }
        /*} catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        return sampleFrame;
    }

    void printFrame(ArrayList<String> frame, String header) {
        initialize(frame, header);
        printIndex(frame);
    }

    private void initialize(ArrayList<String> frame, String header) {
        maxLength = 0;
        tabSize = 0;
        System.out.println(header);
        for (String aFrame : frame) {
            if (maxLength < aFrame.length()) {
                maxLength = aFrame.length();
            }
        }

        do {
            maxLength++;
        } while (maxLength % 4 != 0);

        for(int k = maxLength - 7; k >= 4; k -= 4) {
            tabSize++;
        }
    }

    /*private void printIndex(ArrayList<String> frame) {
        int i = 0;
        for(; i < frame.size(); i++) {
            if (i >= 9) {
                System.out.print("Index " + (i + 1));
            } else {
                System.out.print("Index " + 0 + (i + 1));
            }
            for(int j = 0; j < tabSize; j++) {
                System.out.print("\t");
            }
            if (i % 10 == 9) {
                printItems(frame, i);
            }
        }
        if (i % 10 != 9) {
            printItems(frame, i);
        }
    }*/

    private void printItems(ArrayList<String> frame, int endPoint) {
        int startPoint;
        if (endPoint % 10 == 9) {
            startPoint = endPoint - 9;
        } else {
            startPoint = endPoint - (endPoint % 10);
        }
        if (endPoint == frame.size()) {
            endPoint--;
        }
        System.out.println();
        for (int i = startPoint; i <= endPoint; i++) {
            System.out.print(frame.get(i)/* + "\t"*/);
            if (frame.get(i).length() % 4 != 0){
                System.out.print("\t");
            }
            for (int j = maxLength - frame.get(i).length(); j >= 4; j -= 4) {
                System.out.print("\t");
            }
            if (frame.get(i).length() < 4) {
                System.out.print("\t\t");
            }
        }
        System.out.println("\n");
    }

    private void printIndex(ArrayList<String> sample) {
        int i = 0;
        for (String contestant : sample) {
            for (int j = 0; j < sampleFrame.size(); j++) {
                if (contestant.equals(sampleFrame.get(j))) {
                    if (j >= 9) {
                        System.out.print("Index " + (j + 1));
                    } else {
                        System.out.print("Index " + 0 + (j + 1));
                    }
                    for (int k = 0; k < tabSize; k++) {
                        System.out.print("\t");
                    }
                    if (tabSize == 0) {
                        System.out.print("\t");
                    }
                    if (i++ % 10 == 9) {
                        printItems(sample, i - 1);
                    }
                    break;
                }
            }
        }
        if (i % 10 != 9) {
            printItems(sample, i);
        }
    }

    int getPopulation() {
        return N;
    }
}
