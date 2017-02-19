package Lab01;

import java.util.ArrayList;

/**
 * Created by Semora on February 19, 2017
 */
public class SystematicSampling extends Sampling implements NeedsSampleSize {

    SystematicSampling(ArrayList<String> sampleFrame) {
        super(sampleFrame, "RANDOM SAMPLE");
    }


    @Override
    public ArrayList<String> getSample(int sampleSize) {
        ArrayList<String> sample = new ArrayList<>();
        int k = sampleFrame.size() / sampleSize;
        System.out.println(sampleFrame.size() + "/" + sampleSize);
        int randomIndex = generateRandomNum(k - 1);
        for (int i = 0, j = randomIndex; i < sampleSize; i++, j += k) {
            sample.add(sampleFrame.get(j));
        }
        return sample;
    }
}
