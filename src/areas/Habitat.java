package areas;

import java.util.ArrayList;

/**
 * - This is Habitat class which inherits the IArea interface
 * - This class helps to separate the area where animals can live
 */
public abstract class Habitat implements IArea{

    // This arrayList stores the adjacent areas of a habitat
    protected ArrayList<Integer> adjacentAreas = new ArrayList<>();

    // This variable stores the maximum numbers of Animal that can be kept in the habitat
    protected int maxNumberOfAnimals;

    /**
     * @return Returns the maximum number of Animals in that habitat
     */
    public abstract int getMaxNumberOfAnimals();

    @Override
    public void addAdjacentArea(int areaId){
        adjacentAreas.add(areaId);
    }

    @Override
    public ArrayList<Integer> getAdjacentAreas(){
        return adjacentAreas;
    }

    @Override
    public void removeAdjacentArea(int areaId){
        adjacentAreas.remove(areaId);

    }

}
