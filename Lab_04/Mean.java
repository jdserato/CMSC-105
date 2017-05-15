import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by Semora on May 12, 2017.
 */

public class Mean implements Average{
    private ArrayList<Double> data;

    public Mean() {
        //constructor
    }

    @NotNull
    public ArrayList<Data> getAverage(@NotNull ArrayList<Double> data){
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

    public double getVariance(double sumLeft, double sumRight, int n) {
        return ((n * sumRight) - Math.pow(sumLeft, 2)) / (n * (n - 1));
    }

    public double getStandardDev(double variance) {
        return Math.sqrt(variance);
    }

    @NotNull
    public ArrayList<Data> getAverage(@NotNull ArrayList<Double> midpoint, @NotNull ArrayList<Integer> frequency){
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

    @NotNull
    public String toString() {
        return "Mean";
    }
}