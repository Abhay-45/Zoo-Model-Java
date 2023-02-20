package areas;

/**
 * This class is Cage area which inherits the Habitat class
 */
public class Cage extends Habitat{

    /**
     * The constructor sets the maximum number of animals of the area to the value of the parameter
     * @param maxNumberOfAnimals
     */
    public Cage(int maxNumberOfAnimals){
        this.maxNumberOfAnimals = maxNumberOfAnimals;
    }

    @Override
    public int getMaxNumberOfAnimals() {
        return maxNumberOfAnimals;
    }

}
