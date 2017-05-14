package Lab01;

import java.util.Random;
import java.util.ArrayList;
import java.lang.*;
import java.util.LinkedList;

/**
 * Created by Serato & Amora on 2/26/2017
 */
public class StratifiedSampling{

    private double percentage;
    private ArrayList<String> unique = new ArrayList<>();
    private ArrayList<StratifiedSampleData> sample = new ArrayList<>();
    private ArrayList<String> sampleFrame = new ArrayList<>();
    private LinkedList<ArrayList<Integer>> indicesFinal = new LinkedList<>();

    StratifiedSampling(ArrayList<String> sampleFrame) {
        this.sampleFrame = sampleFrame;
    }

    public Integer generateRandomNumber(int min, int max){
        Random rand = new Random();
        return rand.nextInt(max) + min;
    }

    public void getUnique(){
        ArrayList<String> uniqueVariables = new ArrayList<>();
            for (String var: sampleFrame){
                if (!uniqueVariables.contains(var)){
                    uniqueVariables.add(var);
                }
            }
        unique = uniqueVariables;
    }

    public void getIndices(){

        LinkedList<ArrayList<Integer>> indices = new LinkedList<>();

        for (String uniqueVar: unique){
            ArrayList<Integer> forEach = new ArrayList<>();
            for (int i = 0; i < sampleFrame.size(); i++){
                if (sampleFrame.get(i).equals(uniqueVar)){
                    forEach.add(i);
                }
            }
            indices.add(forEach);
        }
        indicesFinal = indices;
    }

    public void processSampling(){
        int uniqueCounter = 0;

        for (ArrayList<Integer> indices: indicesFinal){
            ArrayList<Integer> temporaryIndices = new ArrayList<>();
            int sampleSizeForEach = (int)Math.ceil(percentage * indices.size());
            while (temporaryIndices.size() < sampleSizeForEach){
                int randomNum = generateRandomNumber(0,indices.size()-1);
                if (temporaryIndices.contains(randomNum)){
                    temporaryIndices.add(randomNum);
                }
            }
            StratifiedSampleData toAdd = new StratifiedSampleData(unique.get(uniqueCounter),temporaryIndices);
            sample.add(toAdd);
            uniqueCounter++;
        }
    }


    public ArrayList<StratifiedSampleData> getSample(double percentage) {
        this.percentage = percentage;
        ArrayList<String> unique = new ArrayList<>();
        getUnique();
        getIndices();
        processSampling();
        return sample;
    }




    /*
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
    */
}
