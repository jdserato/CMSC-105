package Lab03.AverageGenerator;

import Lab03.Data;

import java.util.ArrayList;

/**
 * Created by Serato and Amora on 4/21/17.
 */
public class Mean implements Average{
    private ArrayList<Double> data;

    public Mean() {
        //constructor
    }

    public ArrayList<Data> getAverage(ArrayList<Double> data){
        this.data = data;
        Double sum = 0.0;

        for (Double d: data){
            sum += d;
        }

        ArrayList<Data> ret = new ArrayList<>();
        ret.add(new Data(sum/data.size() + "", 0));
        return ret;
    }

    public double getVariance(double mean) {
        double sum = 0;
        for (double d : data) {
            sum = sum + Math.pow(d - mean, 2);
        }

        return sum / data.size();
    }

    public double getStandardDev(double variance) {
        return Math.sqrt(variance);
    }

    public ArrayList<Data> getAverage(ArrayList<Double> midpoint, ArrayList<Integer> frequency){
        Double sum = 0.0;
        Double total = 0.0;

        for (int i = 0; i < midpoint.size(); i++){
            sum = sum + (midpoint.get(i) * ((double)frequency.get(i)));
        }

        for (int freq: frequency){
            total += freq;
        }

        ArrayList<Data> ret = new ArrayList<>();
        ret.add(new Data("mean", sum/total));
        return ret;
    }

    public String toString() {
        return "Mean";
    }
}