import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by Semora on February 19, 2017
 */
public class SystematicSampling extends Sampling {

    SystematicSampling(ArrayList<String> sampleFrame) {
        super(sampleFrame, "RANDOM SAMPLE");
    }


    @NotNull
    @Override
    public ArrayList<String> getSample(int sampleSize) {
        ArrayList<String> sample = new ArrayList<>();
        int k = sampleFrame.size() / sampleSize;
        int randomIndex = generateRandomNum(k - 1);
        for (int i = 0, j = randomIndex; i < sampleSize; i++, j += k) {
            sample.add(sampleFrame.get(j));
            sample.add("" + (j + 1));
        }
        return sample;
    }

    @NotNull
    public String toString() {
        return "Systematic Sampling";
    }
}
