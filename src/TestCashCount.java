import dataStructures.CashCount;
import zoo.Zoo;

public class TestCashCount {

    public static void main(String[] args) {
        Zoo newZoo = new Zoo();
        CashCount myCashCount = new CashCount();
        myCashCount.setNrNotes_20pounds(2);

        CashCount cashInserted = new CashCount();
        cashInserted.setNrNotes_20pounds(2);
        cashInserted.setNrNotes_10pounds(1);


        newZoo.setCashSupply(myCashCount);
        newZoo.setEntranceFee(50,0);

        System.out.println(newZoo.payEntranceFee(cashInserted).getNrNotes_20pounds());


    }
}
