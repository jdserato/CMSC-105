package Lab01;

import java.util.ArrayList;
import java.lang.*;
import java.util.Scanner;

/**
 * Created by Serato & Amora on 2/26/2017.
 */
public class StratifiedSampling extends Sampling{

    private ArrayList<String> sample = new ArrayList<>();
    private ArrayList<String> indices = new ArrayList<>();
    private ArrayList<String> unique = new ArrayList<>();

    StratifiedSampling(ArrayList<String> sampleFrame) {
        super(sampleFrame, "STRATIFIED SAMPLE");
    }


    @Override
    public ArrayList<String> getSample(int sampleSize) {
        double percentage;
        Scanner scan = new Scanner(System.in);
        System.out.print("Percentage of elements you want from each stratum: ");
        percentage = scan.nextDouble();
        for (String s: sampleFrame){
            if (!unique.contains(s)){
                unique.add(s);
            }
        }

       int num = 1;
        for (String s: unique){
            int i = 0;
            for (String str: sampleFrame){
                if (s.equals(str)){
                    indices.add(i+"");
                }
                i++;
            }

            System.out.println("Strata "+num+"\n");
            int z = 1;

            for (String index: indices){
                if ((Integer.parseInt(index) + 1) < 10){
                    System.out.print("INDEX 0"+(Integer.parseInt(index)+1)+"      ");
                }else{
                    System.out.print("INDEX "+(Integer.parseInt(index)+1)+"      ");

                }
                z++;
            }
            System.out.println();
            for (int p = 1; p < z; p++){
                System.out.print(s+"             ");

            }
            System.out.println("\n");

            SimpleRandomSampling sampleThis = new SimpleRandomSampling(indices);
            sample.addAll(sampleThis.getSample((int)(Math.ceil(indices.size()*(percentage/100)))));
            num++;
            indices.clear();

        }

        indices.addAll(sample);
        sample.clear();

        //printing the sample

        int i = 0;
        System.out.println("\nSAMPLE: ");
        for (String st: indices){
            int index = Integer.parseInt(st);
            if (index < 10){
                System.out.print("INDEX 0"+ (index + 1)+"      ");
            }else{
                System.out.print("INDEX "+ (index + 1)+"      ");
            }
            sample.add(sampleFrame.get(index));
            i++;
        }
        System.out.println();
        for (String string: sample){
            System.out.print(string+"             ");
        }
        System.out.println("\n");

        return sample;
    }
}
