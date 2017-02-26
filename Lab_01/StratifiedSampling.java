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
        //unique elements
       /*
        for (String s: unique){
            System.out.print(s+">> ");
        }

        System.out.println(); */
        for (String s: unique){
            int i = 0;
            for (String str: sampleFrame){
                if (s.equals(str)){
                    indices.add(i+"");
                }
                i++;
            }
            //printing
            /*
            System.out.print(s+" >> ");
            for (String m: indices){
                System.out.print(m+", ");
            }
            */

            SimpleRandomSampling sampleThis = new SimpleRandomSampling(indices);
            sample.addAll(sampleThis.getSample((int)(Math.ceil(indices.size()*(percentage/100)))));
           /*
            System.out.println("SAMPLE: ");
            for (String a: sample){
                System.out.print(a+ ", ");
            }
            System.out.println("--end of sample--"); */
            indices.clear();
        }

        indices.addAll(sample);
        sample.clear();

        //printing the sample

        int i = 0;
        System.out.println("Sample: ");
        for (String st: indices){
            int index = Integer.parseInt(st);
            System.out.print("Index "+ (index + 1));
            sample.add(sampleFrame.get(index));
            System.out.print(" = "+ sample.get(i)+"; ");
            i++;
        }
        System.out.println();


        return sample;
    }
}
