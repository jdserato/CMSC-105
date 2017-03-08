package Lab01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Semora on February 18, 2017
 */

class InputAndOutput {
    private int N;
    private int tabSize, maxLength;
    private Scanner sc = new Scanner(System.in);

    ArrayList<String> gather(boolean manual) {
        boolean numeric = false, error = false;
        do {
            if (error) {
                sc.nextLine();
                for (int i = 0; i < 5; i++) {
                    System.out.println();
                }
                System.out.println("Invalid input.");
            }
            System.out.print("Please enter the population size (N): ");
            try {
                N = sc.nextInt();
            } catch (InputMismatchException e) {
                N = 0;
                error = true;
            }
        } while (N <= 0);

        ArrayList<String> sampleFrame = new ArrayList<>();
        sc.nextLine(); // acts as precaution
        BufferedReader br = null;
        error = false;
        if (manual) {
            System.out.println("Please enter the members of the sampling frame: ");
        }
        try {
            br = new BufferedReader(new FileReader("input.in"));
            for (int i = 1; i <= N; i++) {
                String newItem;
                if (manual) {
                    error = false;
                    System.out.print(i + ". ");
                    newItem = sc.next();
                } else {
                    newItem = br.readLine();
                }
                if (newItem.length() == 0) {
                    if (manual) {
                        System.out.println("Please re-enter your entry.");
                    }
                    i--;
                    error = true;
                } else if (i == 1) {
                    numeric = !((newItem.charAt(0) >= 'A' && newItem.charAt(0) <= 'Z') || (newItem.charAt(0) >= 'a' && newItem.charAt(0) <= 'z'));
                }
                for (int j = 0; j < newItem.length(); j++) {
                    if (numeric) {
                        if (newItem.charAt(j) >= '0' && newItem.charAt(j) <= '9') {
                            if (j + 1 == newItem.length()) {
                                sampleFrame.add(newItem);
                            }
                        } else if (!error) {
                            if (manual) {
                                System.out.println("ERROR! Input must be numeric only!");
                            }
                            i--;
                            error = true;
                        }
                    } else {
                        if ((newItem.charAt(j) >= 'A' && newItem.charAt(j) <= 'Z') || (newItem.charAt(j) >= 'a' && newItem.charAt(j) <= 'z')) {
                            if (j + 1 == newItem.length()) {
                                sampleFrame.add(newItem);
                            }
                        } else if (!error) {
                            if (manual) {
                                System.out.println("ERROR! Input must be character data only!");
                            }
                            i--;
                            error = true;
                        }
                    }
                }
                if (!manual && error) {
                    System.out.println("An error has been found in your file. Please re-check the file and try again.");
                    return null;
                }
                if (!error) {
                    sampleFrame.add(i + "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    private void printItems(ArrayList<String> frame, int endPoint) {
        int startPoint;

            if (endPoint % 20 >= 18) {
                startPoint = endPoint - 18;
            } else {
                startPoint = endPoint - (endPoint % 20);
            }
            if (endPoint == frame.size()) {
                endPoint--;
            }
            if (startPoint < 0) {
                startPoint = 0;
            }
        System.out.println();
        for (int i = startPoint; i <= endPoint;) {
            System.out.print(frame.get(i)/* + "\t"*/);
            if (frame.get(i).length() % 4 != 0) {
                System.out.print("\t");
            }
            for (int j = maxLength - frame.get(i).length(); j >= 4; j -= 4) {
                System.out.print("\t");
            }
            if (frame.get(i).length() < 4) {
                System.out.print("\t\t");
            }
            i += 2;
        }
        System.out.println("\n");
    }

    private void printIndex(ArrayList<String> sample) {
        int i = 1;
        for(; i < sample.size(); i += 2) {
            try {
                if (Integer.parseInt(sample.get(i)) > 9) {
                    System.out.print("Index " + sample.get(i));
                } else {
                    System.out.print("Index " + 0 + sample.get(i));
                }
            } catch (NumberFormatException e) {
                System.out.print("Index " + 0 + sample.get(i));
            }

            for (int k = 0; k < tabSize; k++) {
                System.out.print("\t");
            }
            if (tabSize == 0) {
                System.out.print("\t");
            }
            if (i % 20 >= 18) {
                printItems(sample, i - 1);
            }

        }
        if (i % 20 <= 19 && i % 20 > 1) {
            printItems(sample, i - 1);
        }
    }

    int getPopulation() {
        return N;
    }
}
