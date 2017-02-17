import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Amora & Serato on 2/17/17.
 */
public class SimpleRandomSampling {

    private Random r;
    private ArrayList<String> sampleFrame;
    private int randomNumber;

    public SimpleRandomSampling(){
        r = new Random();

        //default constructor
    }
    public SimpleRandomSampling(ArrayList<String> sampleFrame){
        this.sampleFrame = sampleFrame;
        r = new Random();

    }

    public ArrayList<String> getSample(int sampleSize){
        ArrayList<String> sample = new ArrayList<String>();
        int randomIndex;
        for (int i = 0; i < sampleSize;i++){

            do{
                randomIndex = generateRandomNum(0,sampleFrame.size());
            }while (sample.contains(sampleFrame.get(randomIndex)));

            sample.add(sampleFrame.get(randomIndex));

        }
        return sample;
    }

    public ArrayList<String> getSample(){
        int sampleSize;
        sampleSize = (int)(sampleFrame.size() * .2);
        return getSample(sampleSize);
    }

    private int generateRandomNum(int min, int max){
        return r.nextInt((max - min) + 1) + min;

    }


}
