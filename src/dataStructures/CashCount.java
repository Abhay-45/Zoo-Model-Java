package dataStructures;

import java.util.HashMap;

public class CashCount implements ICashCount{
    // This Hashmap stores the number of each pound notes/coins present in the total money
    HashMap<Integer,Integer> poundsDistribution = new HashMap<>();

    // This Hashmap stores the number of each pence coin present in the total money
    HashMap<Integer,Integer> penceDistribution = new HashMap<>();

    // Sets the initial number of notes/coins for each type to zero
    public CashCount(){
        setNrNotes_20pounds(0);
        setNrNotes_10pounds(0);
        setNrNotes_5pounds(0);
        setNrCoins_2pounds(0);
        setNrCoins_1pound(0);
        setNrCoins_50p(0);
        setNrCoins_20p(0);
        setNrCoins_10p(0);
    }

    /**
     * @return Returns the sum of total pounds present
     */
    public int sumAllPounds(){
        int result = 0;
        for (Integer key : poundsDistribution.keySet()){
            int currentMoney = key * poundsDistribution.get(key);
            result += currentMoney;
        }
        return result;
    }

    /**
     * @return Returns the sum of total pence present
     */
    public int sumAllPence(){
        int result = 0;
        for (Integer key : penceDistribution.keySet()){
            int currentMoney = key * penceDistribution.get(key);
            result += currentMoney;
        }
        return result;
    }

    @Override
    public void setNrNotes_20pounds(int noteCount) {
        poundsDistribution.put(20,noteCount);
    }

    @Override
    public void setNrNotes_10pounds(int noteCount) {
        poundsDistribution.put(10,noteCount);
    }

    @Override
    public void setNrNotes_5pounds(int noteCount) {
        poundsDistribution.put(5,noteCount);
    }

    @Override
    public void setNrCoins_2pounds(int coinCount) {
        poundsDistribution.put(2,coinCount);
    }

    @Override
    public void setNrCoins_1pound(int coinCount) {
        poundsDistribution.put(1,coinCount);
    }

    @Override
    public void setNrCoins_50p(int coinCount) {
        penceDistribution.put(50,coinCount);
    }

    @Override
    public void setNrCoins_20p(int coinCount) {
        penceDistribution.put(20,coinCount);
    }

    @Override
    public void setNrCoins_10p(int coinCount) {
        penceDistribution.put(10,coinCount);
    }

    @Override
    public int getNrNotes_20pounds() {
        return poundsDistribution.get(20);
    }

    @Override
    public int getNrNotes_10pounds() {
        return poundsDistribution.get(10);
    }

    @Override
    public int getNrNotes_5pounds() {
        return poundsDistribution.get(5);
    }

    @Override
    public int getNrCoins_2pounds() {
        return poundsDistribution.get(2);
    }

    @Override
    public int getNrCoins_1pound() {
        return poundsDistribution.get(1);
    }

    @Override
    public int getNrCoins_50p() {
        return penceDistribution.get(50);
    }

    @Override
    public int getNrCoins_20p() {
        return penceDistribution.get(20);
    }

    @Override
    public int getNrCoins_10p() {
        return penceDistribution.get(10);
    }
}
