package areas;

import java.util.ArrayList;

/**
 * - This is a NonHabitat class which inherits the IArea Interface
 * - This class helps separate the areas where animals cannot live
 */
public abstract class NonHabitat implements IArea {

    // This arrayList stores the adjacent areas of this non habitat area
    ArrayList<Integer> adjacentAreas = new ArrayList<>();

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
