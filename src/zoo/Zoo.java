package zoo;

import animals.Aerial;
import animals.Animal;
import animals.Aquatic;
import animals.Terrestrial;
import areas.*;
import dataStructures.CashCount;
import dataStructures.ICashCount;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

/**
 * This is the Zoo class which inherits the IZoo interface
 */
public class Zoo implements IZoo{

    // This HashMap stores the areaId as a key and the area associated with the id as a value
    private HashMap<Integer,IArea> id = new HashMap<>();

    // This hashMap stores the areaId as a key and the arraylist containing all the animals in that area as a value
    private HashMap<Integer,ArrayList<Animal>> animalsInArea = new HashMap<>();

    // This Set stores all the reachable areas in the one-way system starting from the Entrance
    private Set<Integer> reachableAreas = new HashSet<>();

    // This static variable stores the id assigned to the last year
    private static int IdCount;

    // This variable stores the total pounds in the total entry fees
    private int poundsTotal;

    // This variable stores the total pence in the total entry fees
    private int penceTotal;

    // This is the instance of ICashCount to store the current amount of cash present
    private ICashCount currentCashCount;

    // This variable stores the total pounds in the entrance fee
    private int entranceFeeTotalPounds = poundsTotal + penceTotal / 100;

    // This variable stores the total pence in the entrance fee
    private int entranceFeeTotalPence = penceTotal % 100;

    // The constructor creates Entrance and sets the id of entrance with 0
    public Zoo(){
        Entrance entrance = new Entrance();
        id.put(0,entrance);
        animalsInArea.put(0,new ArrayList<>());
    }

    // This method creates a new and unique id
    private static int generateId(){
        IdCount++;
        return IdCount;
    }

    @Override
    public int addArea(IArea area) {
        int key;
        // Checks if the area is entrance or the area is already created. If that's the case then returns -1.
        if (id.containsValue(area) || (area instanceof Entrance && id.containsKey(0))){
            key = -1;
        }
        else{
            key = generateId();
            id.put(key,area);
            animalsInArea.put(key, new ArrayList<>());
        }
        return key;
    }

    @Override
    public boolean removeArea(int areaId) {
        boolean result;
        // Checks if the area to be removed is not entrance
        if (areaId == 0){
            result = false;
        }
        else{
            result = id.remove(areaId, id.get(areaId)) && animalsInArea.remove(areaId, animalsInArea.get(areaId));
        }
        return result;
    }

    @Override
    public IArea getArea(int areaId) {
        IArea result = id.get(areaId);
        return result;
    }

    /**
     * Checks if the animal to be added in the area is compatible with the other animals already present in the area
     * @param areaId
     * @param animal
     * @return Returns the byte ANIMAL-ADDED from Codes class if it is compatible with other animals or else returns -1
     */
    public byte compatibilityCheck(int areaId, Animal animal){
        for (Animal name : animalsInArea.get(areaId)) {
            if (!animal.isCompatibleWith(name)){
                return -1;
            }
        }
        return Codes.ANIMAL_ADDED;
    }

    /**
     * Checks if the area where the animals to be added is habitable or not
     * @param areaId
     * @return Returns the final byte NOT-A-HABITAT from Codes class if it is not a habitat else returns -1
     */
    public byte nonHabitatCheck(int areaId){
        if (id.get(areaId) instanceof Habitat){
            return -1;
        }
        return Codes.NOT_A_HABITAT;
    }

    /**
     * Checks if the area where the animal to be added is habitat for that animal
     * @param areaId
     * @param animal
     * @return Returns the final byte WRONG-HABITAT from Codes class if it is wrong habitat else returns -1
     */
    public byte wrongHabitatCheck(int areaId, Animal animal){
        boolean aquaticHabitat = id.get(areaId) instanceof Aquarium && (animal instanceof Aquatic);
        boolean aerialHabitat = id.get(areaId) instanceof Cage && (animal instanceof Aerial);
        boolean terrestrialHabitat = id.get(areaId) instanceof Enclosure && (animal instanceof Terrestrial);
        if(aquaticHabitat || aerialHabitat || terrestrialHabitat){
            return -1;
        }
        return Codes.WRONG_HABITAT;
    }

    /**
     * Checks if the habitat is full or not with the capacity it has been initialised during creation
     * @param areaId
     * @return Return the final byte HABITAT-FULL from Codes class if the habitat is full else returns -1
     */
    public byte habitatCapacity(int areaId){
        if(id.get(areaId) instanceof Habitat) {
            Habitat habitat = (Habitat) id.get(areaId);
            if (animalsInArea.get(areaId).size() < habitat.getMaxNumberOfAnimals()) {
                return -1;
            }
        }
        return Codes.HABITAT_FULL;
    }

    @Override
    public byte addAnimal(int areaId, Animal animal) {
        byte result ;

        if (habitatCapacity(areaId) == -1 && compatibilityCheck(areaId,animal) == Codes.ANIMAL_ADDED && nonHabitatCheck(areaId) == -1 && wrongHabitatCheck(areaId,animal) ==-1) {
            result = Codes.ANIMAL_ADDED;
            animalsInArea.get(areaId).add(animal);
        }

        else{
            result = nonHabitatCheck(areaId);
            if (result == -1) {
                result = wrongHabitatCheck(areaId, animal);
                if (result == -1) {
                    result = habitatCapacity(areaId);
                    if (result == -1) {
                        result = Codes.INCOMPATIBLE_INHABITANTS;
                    }
                }
            }
        }

        return result;
    }

    @Override
    public void connectAreas(int fromAreaId, int toAreaId) {
        if (fromAreaId != toAreaId){
            getArea(fromAreaId).addAdjacentArea(toAreaId);
        }
        else{
            System.out.println("Cannot connect to the same area!");
        }
    }

    @Override
    public boolean isPathAllowed(ArrayList<Integer> areaIds) {
        boolean result = true;
        for (int i = 0; i < areaIds.size() -1; i++){
            int currentId = areaIds.get(i);
            int nextId = areaIds.get(i+1);
            // Checks if the area id in areaIds is created in zoo or not
            boolean isAreaIdValid = id.containsKey(currentId) && id.containsKey(nextId);
            // Checks if the next area to visit from the current area is an adjacent area to it or not
            boolean isNextAreaConnected = getArea(currentId).getAdjacentAreas().contains(nextId);
            if (!(isAreaIdValid && isNextAreaConnected)){
                result = false;
                break;
            }
        }

        return result;
    }

    @Override
    public ArrayList<String> visit(ArrayList<Integer> areaIdsVisited) {
        ArrayList<String> animalNames = new ArrayList<>();
        // Checks if the path to be visited is allowed
        if (isPathAllowed(areaIdsVisited)){
            for (int i = 0; i < areaIdsVisited.size(); i++) {
                int currentId = areaIdsVisited.get(i);
                ArrayList<Animal> animals = animalsInArea.get(currentId);
                for (Animal animal : animals) {
                    String nickName = animal.getNickname();
                    animalNames.add(nickName);
                }
            }
        }
        else{
            return null;
        }

        return animalNames;
    }

    /**
     * Using recursion adds all the reachable areas to the set reachableAreas.
     * @param adjacentAreas
     */
    public void findReachableAreas(ArrayList<Integer> adjacentAreas){
        for (Integer areas : adjacentAreas ){
            reachableAreas.add(areas);
            ArrayList<Integer> newAreas = getArea(areas).getAdjacentAreas();
            if (!reachableAreas.containsAll(newAreas)){
                findReachableAreas(newAreas);
            }
        }
    }

    @Override
    public Set<Integer> findUnreachableAreas() {
        ArrayList<Integer> adjAreasOfEntrance =  id.get(0).getAdjacentAreas();
        findReachableAreas(adjAreasOfEntrance);
        // Add all the areas to the set unReachableAreas which are not present in the set reachableAreas and is not the entrance
        Set<Integer> unReachableAreas = new HashSet<>();
        for (Integer areaId : id.keySet()){
            if(!reachableAreas.contains(areaId) && !(areaId == 0)){
                unReachableAreas.add(areaId);
            }
        }

        return unReachableAreas;
    }

    @Override
    public void setEntranceFee(int pounds, int pence) {
        poundsTotal = pounds;
        penceTotal = pence;
    }

    @Override
    public void setCashSupply(ICashCount coins) {
        currentCashCount = coins;
    }

    @Override
    public ICashCount getCashSupply() {
        return currentCashCount;
    }

    @Override
    public ICashCount payEntranceFee(ICashCount cashInserted) {
        // Checks when the cashInserted is less than the entrance fee
        if (remainingCashCount(cashInserted).get(0) < entranceFeeTotalPounds && remainingCashCount(cashInserted).get(1) < entranceFeeTotalPence){
            return cashInserted;
        }
        // Checks if the cashInserted is equal to the entrance fee
        else if(remainingCashCount(cashInserted).get(0) == 0 && remainingCashCount(cashInserted).get(1) == 0){
            addCashSupply((CashCount) currentCashCount, (CashCount) cashInserted);
            CashCount result = new CashCount();
            return result;
        }
        // Checks if the remaining money from cashInserted can be returned or not
        else if (returnPoundCashSupply(currentCashCount,remainingCashCount(cashInserted).get(0)).equals(null) || returnPenceCashSupply(currentCashCount,remainingCashCount(currentCashCount).get(1)).equals(null)){
            return cashInserted;
        }
        else{
            CashCount poundsToReturn = returnPoundCashSupply(currentCashCount,remainingCashCount(cashInserted).get(0));
            CashCount penceToReturn = returnPenceCashSupply(currentCashCount,remainingCashCount(cashInserted).get(1));
            CashCount result = addCashSupply( poundsToReturn,  penceToReturn);
            return result;
        }
    }

    /**
     * This method calculates the difference between the cashInserted and the entrance fee
     * @param cashInserted
     * @return Returns an arraylist containing the remaining pounds and pence
     */
    private ArrayList<Integer> remainingCashCount(ICashCount cashInserted){
        ArrayList<Integer> result = new ArrayList<>();

         int cashInsertedTotalPounds = ((CashCount)cashInserted).sumAllPounds() + ((CashCount)cashInserted).sumAllPence() / 100;
         int cashInsertedTotalPence = ((CashCount)cashInserted).sumAllPence() % 100;

         int returnPound = cashInsertedTotalPounds - entranceFeeTotalPounds;
         int returnPence = cashInsertedTotalPence - entranceFeeTotalPence;

         result.add(returnPound);
         result.add(returnPence);

         return result;
    }

    /**
     * This method adds each currency note/coin of each other and returns the resulting
     * @param toCashCount
     * @param fromCashCount
     * @return Retuens the added CashCount
     */
    private CashCount addCashSupply(CashCount toCashCount, CashCount fromCashCount){

        toCashCount.setNrNotes_20pounds(toCashCount.getNrNotes_20pounds() + fromCashCount.getNrNotes_20pounds());
        toCashCount.setNrNotes_10pounds(toCashCount.getNrNotes_10pounds() + fromCashCount.getNrNotes_10pounds());
        toCashCount.setNrNotes_5pounds(toCashCount.getNrNotes_5pounds() + fromCashCount.getNrNotes_5pounds());
        toCashCount.setNrCoins_2pounds(toCashCount.getNrCoins_2pounds() + fromCashCount.getNrCoins_2pounds());
        toCashCount.setNrCoins_1pound(toCashCount.getNrCoins_1pound() + fromCashCount.getNrCoins_1pound());
        toCashCount.setNrCoins_50p(toCashCount.getNrCoins_50p() + fromCashCount.getNrCoins_50p());
        toCashCount.setNrCoins_20p(toCashCount.getNrCoins_20p() + fromCashCount.getNrCoins_20p());
        toCashCount.setNrCoins_10p(toCashCount.getNrCoins_10p() + fromCashCount.getNrCoins_10p());

        return toCashCount;
    }

    /**
     * @param currentCashCount
     * @param poundsTotal
     * @return Returns the CashCount containing pound notes/coins equal to the input integer from the input ICashCount
     */
    private CashCount returnPoundCashSupply(ICashCount currentCashCount, int poundsTotal){
        CashCount result = new CashCount();
        int pounds = poundsTotal;

        if (pounds/20 > 0 && (currentCashCount.getNrNotes_20pounds() - pounds/20) >= 0){
            int maxNotes = Math.min(currentCashCount.getNrNotes_20pounds(),pounds/20);
            pounds -= maxNotes*20;
            result.setNrNotes_20pounds(maxNotes);
        }
        if (pounds/10 > 0 && (currentCashCount.getNrNotes_10pounds() - pounds/10) >= 0){
            int maxNotes = Math.min(currentCashCount.getNrNotes_10pounds(),pounds/10);
            pounds -= maxNotes*10;
            result.setNrNotes_10pounds(maxNotes);
        }
        if (pounds/5 > 0 && (currentCashCount.getNrNotes_5pounds() - pounds/5) >= 0){
            int maxNotes = Math.min(currentCashCount.getNrNotes_5pounds(),pounds/5);
            pounds -= maxNotes*5;
            result.setNrNotes_5pounds(maxNotes);
        }
        if (pounds/2 > 0 && (currentCashCount.getNrCoins_2pounds() - pounds/2) >= 0){
            int maxNotes = Math.min(currentCashCount.getNrCoins_2pounds(),pounds/2);
            pounds -= maxNotes*2;
            result.setNrCoins_2pounds(maxNotes);
        }
        if (pounds > 0 && (currentCashCount.getNrCoins_1pound() - pounds) >= 0){
            int maxCoins = Math.min(currentCashCount.getNrCoins_1pound(),pounds);
            pounds -= maxCoins;
            result.setNrCoins_1pound(maxCoins);
        }
        if (pounds != 0){
            return null;
        }

        return result;
    }

    /**
     * @param currentCashCount
     * @param penceTotal
     * @return Returns the CashCount containing pence coins equal to the integer input from the input ICashCount
     */
    private CashCount returnPenceCashSupply(ICashCount currentCashCount,int penceTotal){
        CashCount result = new CashCount();
        int pence = penceTotal;

        if (pence/50 > 0 && (currentCashCount.getNrCoins_50p() - pence/50) >= 0){
            int maxCoins = Math.min(currentCashCount.getNrCoins_50p(),pence/50);
            pence -= maxCoins;
            result.setNrCoins_50p(maxCoins);
        }
        if (pence/20 > 0 && (currentCashCount.getNrCoins_20p() - pence/20) >= 0){
            int maxCoins = Math.min(currentCashCount.getNrCoins_20p(),pence/20);
            pence -= maxCoins;
            result.setNrCoins_20p(maxCoins);
        }
        if (pence/10 > 0 && (currentCashCount.getNrCoins_10p() - pence/10) >= 0){
            int maxCoins = Math.min(currentCashCount.getNrCoins_10p(),pence/10);
            pence -= maxCoins;
            result.setNrCoins_10p(maxCoins);
        }
        if (pence != 0){
            return null;
        }

        return result;
    }


}
