package Lab03;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by localuser on 4/21/17.
 */
public class ModeGenerator {

    private LinkedList<Data> dataCount = new LinkedList<>();

    public ModeGenerator() {
        //constructor
    }

    private ArrayList<Data> countMode(){
        int maximum = 0;
        ArrayList<Data> finalMode = new ArrayList<>();
        for (Data findMax: dataCount){
            if (findMax.getCount() > maximum){
                maximum = findMax.getCount();
            }
        }
        for (Data addThis: dataCount){
            if (addThis.getCount() == maximum){
                finalMode.add(addThis);
            }
        }
        return finalMode;
    }

    public ArrayList<Data> getMode(ArrayList<Double> data){
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

    /*
    public Double getModalClass(){

    }
    */
}
