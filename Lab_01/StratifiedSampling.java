package Lab01;

import java.util.ArrayList;
import java.lang.*;
import java.util.Scanner;

/**
 * Created by Serato & Amora on 2/26/2017
 */
public class StratifiedSampling extends Sampling{

    private ArrayList<String> sample = new ArrayList<>();
    private ArrayList<String> indices = new ArrayList<>();
    private ArrayList<String> unique = new ArrayList<>();
    private ArrayList<String> population = new ArrayList<>();

    StratifiedSampling(ArrayList<String> sampleFrame) {
        super(sampleFrame, "STRATIFIED SAMPLE");
    }


    @Override
    public ArrayList<String> getSample(int sampleSize) {

        for(int i = 0; i < sampleFrame.size(); i += 2) {
            population.add(sampleFrame.get(i));
        }
        double percentage;
        Scanner scan = new Scanner(System.in);
        System.out.print("Percentage of elements you want from each stratum: ");
        percentage = scan.nextDouble();
        for (String s: population){
            if (!unique.contains(s)){
                unique.add(s);
            }
        }

        int num = 1;
        for (String s: unique){
            int i = 0;
            for (String str: population){
                if (s.equals(str)){
                    indices.add(i+"");
                }
                i++;
            }

            System.out.println("STRATA "+ num);
            int z = 1;

            for (String index: indices){
                if ((Integer.parseInt(index) + 1) < 10){
                    System.out.print("Index 0"+(Integer.parseInt(index)+1)+"      ");
                }else{
                    System.out.print("Index "+(Integer.parseInt(index)+1)+"      ");

                }
                z++;
            }
            System.out.println();
            for (int p = 1; p < z; p++){
                System.out.print(s+"             ");

            }
            System.out.println("\n");

            SimpleRandomSampling sampleThis = new SimpleRandomSampling(indices);
            sample.addAll(sampleThis.getSample(((int)(Math.ceil(indices.size()*(percentage/100)))),indices));
            num++;
            indices.clear();

        }
        indices.clear();
        indices.addAll(sample);
        sample.clear();
        //printing the sample
        System.out.println("\nSAMPLE: ");
        for (String st: indices){
            int index = Integer.parseInt(st);
            if (index < 9){
                System.out.print("INDEX 0"+ (index + 1)+"      ");
            }else{
                System.out.print("INDEX "+ (index + 1)+"      ");
            }
            sample.add(population.get(index));
        }
        System.out.println();
        for (String string: sample){
            System.out.print(string+"             ");
        }
        System.out.println("\n");

        return sample;
    }
}