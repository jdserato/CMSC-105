import java.util.ArrayList;

/**
 * Created by Semora on February 18, 2017
 */
class SimpleRandomSampling extends Sampling {

    SimpleRandomSampling(ArrayList<String> sampleFrame) {
        super(sampleFrame, "RANDOM SAMPLE");
    }

    
    public ArrayList<String> getSample(int sampleSize) {
        System.out.println(sampleFrame + "is sample");
        ArrayList<String> sample = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        int randomIndex;
        for (int i = 0; i < sampleSize; i++) {
            System.out.println("eottoke");
            do {
                System.out.println("finding @ " + i + " / " + sampleFrame.size());
                randomIndex = generateRandomNum(sampleFrame.size() - 1);
            } while (indices.contains(randomIndex));

            sample.add(sampleFrame.get(randomIndex));
            sample.add((randomIndex + 1) + "");
            indices.add(randomIndex);
        }
        System.out.println(sample);
        return sample;
    }

     ArrayList<String> getSample(int sampleSize,  ArrayList<String> sampleFrame){

        ArrayList<String> sample = new ArrayList<>();

        int randomIndex;
        for (int i = 0; i < sampleSize; i++) {
            do {
                randomIndex = generateRandomNum((sampleFrame.size() - 1));
            } while (sample.contains(sampleFrame.get(randomIndex) + ""));

            sample.add(sampleFrame.get(randomIndex));
        }
        return sample;
    }

    
    public String toString() {
        return "Simple Random Sampling";
    }
}
