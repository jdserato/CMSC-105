package Lab03;

import java.util.ArrayList;

/**
 * Created by Mary Danielle C. Amora on 4/23/2017.
 */
public class Try {

    public static void main(String[] args) {
        ArrayList<Double> newList = new ArrayList<>();

        newList.add(24.0);
        newList.add(16.0);
        newList.add(14.0);
        newList.add(10.0);
        newList.add(24.0);
        newList.add(16.0);
        newList.add(24.0);
        newList.add(15.0);
        newList.add(25.0);
        newList.add(16.0);
        newList.add(24.0);
        newList.add(16.0);
        //newList.add(25.0);

        System.out.println("DATA: \n");
        for (Double d: newList){
            System.out.println(d+", ");
        }

        MeanGenerator mean = new MeanGenerator();
        ModeGenerator mode = new ModeGenerator();
        MedianGenerator median = new MedianGenerator();
        System.out.println("\n\nMean: "+mean.getMean(newList));
        ArrayList<Data> thisMode = mode.getMode(newList);
        System.out.println("Mode: ");
        for (Data d: thisMode){
            System.out.print(d.getName()+" = "+d.getCount()+", ");
        }
        System.out.println();
        System.out.println("Median: "+median.getMedian(newList));

    }

}
