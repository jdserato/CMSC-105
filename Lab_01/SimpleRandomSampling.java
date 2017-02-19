package Lab01;

import java.util.*;

/**
 * Created by Semora on February 18, 2017
 */
class SimpleRandomSampling extends Sampling implements NeedsSampleSize{

    SimpleRandomSampling(ArrayList<String> sampleFrame) {
        super(sampleFrame, "RANDOM SAMPLE");
    }

    public ArrayList<String> getSample(int sampleSize) {
        ArrayList<String> sample = new ArrayList<>();
        int randomIndex;
        for (int i = 0; i < sampleSize; i++) {
            do {
                randomIndex = generateRandomNum(sampleFrame.size() - 1);
            } while (sample.contains(sampleFrame.get(randomIndex)));

            sample.add(sampleFrame.get(randomIndex));
        }
        return sample;
    }
}
