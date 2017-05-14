package Lab01;

import java.util.ArrayList;

/**
 * Created by Mary Danielle C. Amora on 5/14/2017.
 */
public class StratifiedSampleData {

    private String theData;
    private ArrayList<Integer> theIndices = new ArrayList<>();

    public StratifiedSampleData() {

    }

    public StratifiedSampleData(String theData, ArrayList<Integer> theIndices){
        this.theData = theData;
        this.theIndices = theIndices;
    }


    public ArrayList<Integer> getTheIndices() {
        return theIndices;
    }

    public void setTheIndices(ArrayList<Integer> theIndices) {
        this.theIndices = theIndices;
    }
}
