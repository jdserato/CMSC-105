package Lab03;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Serato and Amora on 4/21/17.
 */
public class MeanGenerator {

    public MeanGenerator() {
        //constructor
    }

    public Double getMean(ArrayList<Double> data){
        Double sum = 0.0;

        for (Double d: data){
            sum += d;
        }

        return sum/data.size();
    }

    public Double getMean(ArrayList<Double> midpoint, ArrayList<Integer> frequency){
        Double sum = 0.0;

        for (int i = 0; i < midpoint.size(); i++){
            sum = sum + (midpoint.get(i) * ((double)frequency.get(i)));
        }
        return sum/midpoint.size();
    }

}
