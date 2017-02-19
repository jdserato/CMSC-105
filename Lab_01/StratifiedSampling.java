package Lab01;

import java.util.ArrayList;

import static Lab01.SeratoAndAmora.gather;

/**
 * Created by Semora on February 19, 2017
 */
public class StratifiedSampling extends Sampling implements DoesntNeedSampleSize{
    StratifiedSampling(ArrayList<String> sampleFrame) {
        super(sampleFrame, "STRATIFIED SAMPLING");
    }

    @Override
    public ArrayList<String> getSample(int sampleSize) {
        ArrayList<String> sample;
        if (numeric) { // stratify to 10 strata
            // finding the maximum
            int max = 0;
            for (String num : sampleFrame) {
                if (max < Integer.parseInt(num)) {
                    max = Integer.parseInt(num);
                }
            }
            int toDivide = (max / 10) + 1;
            int lowerLimit = 0, upperLimit = toDivide;
            for(int i = 0; i < 10; i++, lowerLimit = upperLimit + 1, upperLimit = toDivide * (i + 1)) {
                sample = new ArrayList<>();
                for (String item : sampleFrame) {
                    int val = Integer.parseInt(item);
                    if (lowerLimit <= val && val <= upperLimit) {
                        sample.add(item);
                    }
                }
                gather.printFrame(sample, "STRATA " + (i + 1) + " - " + lowerLimit + " - " + upperLimit);
                if (sample.size() == 0) {
                    System.out.println("<EMPTY>");
                }
            }
        } else {
            // sort alphabetically
            int i = 0;
            for (char c = 'A', d = (char) (c + 4); d <= 'Z';  c += 5, d = (char) (c + 4)) {
                if ('Z' - c <= 5) {
                    d = 'Z';
                }
                sample = new ArrayList<>();
                for (String item : sampleFrame) {
                    if ((item.charAt(0) >= c && item.charAt(0) <= d)|| (item.charAt(0) >= c + 32 && item.charAt(0) <= d + 32)) {
                        sample.add(item);
                    }
                }
                gather.printFrame(sample, "STRATA " + ++i + " - " + c + " - " + d);
                if (sample.size() == 0) {
                    System.out.println("<EMPTY>");
                }
            }
        }
        return  null;
    }
}
