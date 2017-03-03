package Lab01;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Semora on February 19, 2017.
 */
public abstract class Sampling {
    private String header;
    private Random r;
    ArrayList<String> sampleFrame;
    boolean numeric;

    Sampling(ArrayList<String> sampleFrame, String header) {
        this.sampleFrame = sampleFrame;
        this.header = header;
        //numeric = !(sampleFrame.get(0).charAt(0) >= 'A' && sampleFrame.get(0).charAt(0) <= 'z');
        r = new Random();
    }

    public abstract ArrayList<String> getSample(int sampleSize);

    ArrayList<String> getSample(){
        double sampleSize = sampleFrame.size() * .2;
        return getSample((int) Math.ceil(sampleSize));
    }

    int generateRandomNum(int max) {
        return r.nextInt((max) + 1);

    }

    public String getHeader() {
        return header;
    }

}
