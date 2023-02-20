package areas;

/**
 * This class is Aquarium area which inherits the Habitat class
 */
public class Aquarium extends Habitat{

    /**
     * Sets the maximum number of animals of this area equal to the parameter
     * @param maxNumberOfAnimals
     */
    public Aquarium(int maxNumberOfAnimals){
        this.maxNumberOfAnimals = maxNumberOfAnimals;
    }

    @Override
    public int getMaxNumberOfAnimals() {
        return maxNumberOfAnimals;
    }



}
