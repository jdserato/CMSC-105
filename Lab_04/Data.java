/**
 * Created by Mary Danielle C. Amora on 4/23/2017.
 */
public class Data {
    private String name;
    private double count;

    public Data(String name, double count) {
        this.name = name;
        this.count = count;
    }

    public Data(){
        //constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCount() {
        return count;
    }

    public void addCount() {
        count++;
    }
}