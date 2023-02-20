package areas;

/**
 * This is the Enclosure class which inherits the Habitat class
 */
public class Enclosure extends Habitat{

    /**
     * The constructor sets the value of maximum numbers of animals to the value of the parameter
     * @param maxNumberOfAnimals
     */
    public Enclosure(int maxNumberOfAnimals){
        this.maxNumberOfAnimals = maxNumberOfAnimals;
    }

    @Override
    public int getMaxNumberOfAnimals() {
        return maxNumberOfAnimals;
    }

}
