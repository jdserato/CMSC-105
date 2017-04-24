package Lab03.AverageGenerator;

import Lab03.Data;

import java.util.ArrayList;

/**
 * Created by Serato and Amora on 4/21/17.
 */
public class Mean implements Average{

    public Mean() {
        //constructor
    }

    public ArrayList<Data> getAverage(ArrayList<Double> data){
        Double sum = 0.0;

        for (Double d: data){
            sum += d;
        }

        ArrayList<Data> ret = new ArrayList<>();
        ret.add(new Data("mean", sum/data.size()));
        return ret;
    }

    public ArrayList<Data> getAverage(ArrayList<Double> midpoint, ArrayList<Integer> frequency){
        Double sum = 0.0;

        for (int i = 0; i < midpoint.size(); i++){
            sum = sum + (midpoint.get(i) * ((double)frequency.get(i)));
        }

        ArrayList<Data> ret = new ArrayList<>();
        ret.add(new Data("mean", sum/midpoint.size()));
        return ret;
    }

}