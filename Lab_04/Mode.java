import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by localuser on 4/21/17.
 */
public class Mode implements Average{

    @NotNull
    private LinkedList<Data> dataCount = new LinkedList<>();

    public Mode() {
        //constructor
    }

    @NotNull
    private ArrayList<Data> countMode(){
        int maximum = 0;
        ArrayList<Data> finalMode = new ArrayList<>();
        for (Data findMax: dataCount){
            if (findMax.getCount() > maximum){
                maximum = (int) findMax.getCount();
            }
        }
        for (Data addThis: dataCount){
            if (addThis.getCount() == maximum){
                finalMode.add(addThis);
            }
        }
        return finalMode;
    }

    @NotNull
    public ArrayList<Data> getAverage(@NotNull ArrayList<Double> data){
        boolean checking = false;
        for (Double d: data){
            for (Data check: dataCount){
                if (check.getName().equals(d+"")){
                    check.addCount();
                    checking = true;
                    break;
                }
            }
            if (checking){
                //do nothing
            }else{
                Data newData = new Data(d+"",1);
                dataCount.add(newData);
            }
            checking = false;
        }
        //countMode
        return countMode();
    }

    @NotNull
    public ArrayList<Integer> getMode(@NotNull ArrayList<Integer> freq){
        int maximum = 0;
        ArrayList<Integer> finalModalClasses = new ArrayList<>();
        //findmax
        for (int i = 0; i < freq.size(); i++){
            if (freq.get(i) > maximum){
                maximum = freq.get(i);
            }
        }

        for (int z = 0; z < freq.size();z++){
            if (freq.get(z) == maximum){
                finalModalClasses.add(z);
            }
        }
        return finalModalClasses;
    }

    /*
    public Double getModalClass(){
    }
    */

    @NotNull
    public String toString() {
        return "Mode";
    }
}