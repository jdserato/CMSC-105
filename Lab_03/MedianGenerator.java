package Lab03;

import java.util.ArrayList;

/**
 * Created by Serato and Amora on 4/21/17.
 */
public class MedianGenerator {

    public MedianGenerator() {
        //constructor
    }

    public Double getMedian(ArrayList<Double> data){
        ArrayList<Double> sorted = new ArrayList<>();
        boolean added = false;

        //sorting
        for (Double d: data){
            added = false;

            for (int i = 0; i < sorted.size(); i++){
                if (sorted.size() == 0){
                    sorted.add(d);
                    added = true;
                    break;
                }
                if (d < sorted.get(i)){
                    if(i == 0){
                        sorted.add(0,d);
                        added = true;
                        break;
                    }else{
                        sorted.add(i, d);
                        added = true;
                        break;
                    }
                }
            }
            if (added == false){
                sorted.add(d);
                added = true;
            }
        }

        int middle;

        if (sorted.size() % 2 != 0){
            middle = (sorted.size()/2) + 1;
            return sorted.get(middle-1);
        }else{
            middle = sorted.size()/2;
            return (sorted.get(middle-1)+sorted.get(middle))/2;
        }

    }



}
