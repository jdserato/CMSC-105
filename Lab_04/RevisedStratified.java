import java.util.ArrayList;

/**
 * Created by Semora on May 14, 2017.
 */
public class RevisedStratified extends Sampling {

    
    private ArrayList<String> sample = new ArrayList<>();
    
    private ArrayList<String> indices = new ArrayList<>();
    
    private ArrayList<String> unique = new ArrayList<>();

    
    public ArrayList<ArrayList<String>> getStrata() {
        return strata;
    }

    
    private ArrayList<String> population = new ArrayList<>();
    
    private ArrayList<ArrayList<String>> strata = new ArrayList<>();
    
    private ArrayList<ArrayList<String>> answer = new ArrayList<>();

    RevisedStratified (ArrayList<String> sampleFrame) {
        super(sampleFrame, "STRATIFIED SAMPLE");
    }

    
    public ArrayList<ArrayList<String>> getAnswer() {
        return answer;
    }

    
    public ArrayList<ArrayList<String>> getSample(double percentage) {
        population.addAll(sampleFrame);
        sampleFrame.clear();
        for (int i = 0; i < population.size(); i++) {
            sampleFrame.add(population.get(i));
            sampleFrame.add(i + "");
        }

        for (String s: population){
            if (!unique.contains(s)){
                unique.add(s);
            }
        }

        for (int j = 0; j < unique.size(); j++){
            String s = unique.get(j);
            strata.add(new ArrayList<>());
            answer.add(new ArrayList<>());
            int i = 0;
            for (String str: population){
                if (s.equals(str)){
                    indices.add(i+"");
                    strata.get(j).add(i + "");
                }
                i++;
            }

            SimpleRandomSampling sampleThis = new SimpleRandomSampling(indices);
            ArrayList<String> curr = sampleThis.getSample((int)(Math.ceil(indices.size()*(percentage/100))), indices);
            answer.get(j).addAll(curr);

            sample.addAll(curr);
            indices.clear();
        }
        indices.clear();
        indices.addAll(sample);
        sample.clear();
        //printing the sample
        for (String st: indices){
            int index = Integer.parseInt(st);
            
            sample.add(population.get(index));
        }

        return strata;
    }

    @Override
    public ArrayList<String> getSample(int sampleSize) {
        return null;
    }

    @Override
    public String toString() {
        return "Stratified Sampling";
    }
}
