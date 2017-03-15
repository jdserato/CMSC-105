package Lab02;

import java.io.*;
import java.util.*;

/**
 * Created by Semora on March 14, 2017
 */

class InputOutput {
    private Scanner sc = new Scanner(System.in);

    ArrayList<String> gatherInput() {
        int inputSource = 0;
        String str;
        ArrayList<String> list = new ArrayList<>();
        boolean numeric = true;
        boolean error = false;

        // This loop will ask the user for the source of input.
        do {
            if (error) {
                sc.nextLine();
                for(int i = 0; i < 7; i++) {
                    System.out.println();
                }
                System.out.println("Invalid input.\n");
                error = false;
            }

            System.out.print("Choose input source:\n" +
                    "1. Manual Encode\n" +
                    "2. File Source\n" +
                    "Choice: ");

            try {
                inputSource = sc.nextInt();
            } catch (InputMismatchException e) { // If the user did not input a numeric variable, it will flag an error.
                error = true;
            }
        } while (error || inputSource <= 0 || inputSource >= 3);

        // If manual encoding...
        if (inputSource == 1) {
            System.out.println("Please enter data.\n" +
                    "If finished, please press [ENTER] twice.");
            sc.nextLine();

            int i = 1;
            // This loop will get the first datum and decide whether the data set is numerical or categorical.
            do {
                if (error) {
                    error = false;
                    i--;
                    sc.nextLine();
                    System.out.println("Please have at least one data.");
                }
                System.out.print(i++ + ". ");
                str = sc.nextLine();
                if (str.length() == 0) {
                    error = true;
                } else {
                    for (int j = 0; j < str.length(); j++) {
                        // The if-statement is an indication that the variable str is non-numeric.
                        if (str.charAt(j) < '0' || str.charAt(j) > '9') {
                            numeric = false;
                            break;
                        }

                        if (j + 1 == str.length()) {
                            numeric = true;
                        }
                    }
                    list.add(str);
                }
            } while (error);

            // This loop will collect the rest of the data.
            while (true) {
                if (error) {
                    error = false;
                    i--;
                    System.out.println("Please enter a numeric value only.");
                }

                System.out.print(i++ + ". ");
                str = sc.nextLine();

                // This snippet will terminate the loop when the variable str has no contents, meaning the user pressed [ENTER] twice.
                if (str.length() == 0) {
                    break;
                }

                if (numeric) {
                    for (int j = 0; j < str.length(); j++) {
                        // The if-statement is an indication that the variable str is non-numeric and will thus be an error.
                        if (str.charAt(j) < '0' || str.charAt(j) > '9') {
                            error = true;
                        }
                    }
                }
                if (!error) {
                    list.add(str);
                }
            }
        }

        // If file source...
        else {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("input.in"));

                // The next 17 lines will get the first datum and decide whether the data set is numerical or categorical.
                str = br.readLine();
                if (str == null) {
                    System.err.println("Error! Please re-check the file and try again.");
                    return null;
                } else {
                    for (int i = 0; i < str.length(); i++) {
                        // The if-statement is an indication that the variable str is non-numeric.
                        if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                            numeric = false;
                            break;
                        }

                        if (i + 1 == str.length()) {
                            numeric = true;
                        }
                    }
                    list.add(str);
                }

                // This loop will collect the rest of the data embedded in the file.
                while ((str = br.readLine()) != null) {
                    if (numeric) {
                        for (int i = 0; i < str.length(); i++) {
                            // The if-statement is an indication that the variable str is non-numeric.
                            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                                System.err.println("Error! Please re-check the file and try again.");
                                return null;
                            }
                        }
                    }
                    list.add(str);
                }
            } catch (IOException e) {
                System.err.println("Error! Please rename the file to \"input.in\" and place it inside the source (\"src\") folder.");
                return null;
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    // do nothing
                }
            }
        }

        return list;
    }
}