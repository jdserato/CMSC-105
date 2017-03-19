package Lab02;

/**
 * Created by Semora on March 14, 2017
 */
public class SeratoAndAmora {
    public static void main(String[] args) {
        InputForm in = new InputForm();
        return;/*
        boolean error = false;
        boolean numeric;
        Scanner sc = new Scanner(System.in);
        int inputSource = 0;

        // This loop will ask the user for the nature of the input.
        do {
            if (error) {
                sc.nextLine();
                for(int i = 0; i < 7; i++) {
                    System.out.println();
                }
                System.out.println("Invalid input.\n");
                error = false;
            }

            System.out.print("Summarizing and Presenting Data\n" +
                    "1. Categorical\n" +
                    "2. Numerical\n" +
                    "3. Quit\n" +
                    "Choice: ");

            try {
                inputSource = sc.nextInt();
            } catch (InputMismatchException e) { // If the user did not input a numeric variable, it will flag an error.
                error = true;
            }
        } while (error || inputSource <= 0 || inputSource >= 4);

        InputOutput io = new InputOutput();
        switch (inputSource) {
            case 1:
                numeric = false;
                break;
            case 2:
                numeric = true;
                break;
            default:
                return;
        }
        ArrayList<String> list = io.gatherInput(numeric);
        if (list == null) {
            main(null);
        }
    }*/
    }
}
