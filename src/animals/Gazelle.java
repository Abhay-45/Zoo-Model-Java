package animals;

/**
 * This is the Gazelle class which inherits the Terrestrial class
 */
public class Gazelle extends Terrestrial{

    public Gazelle(String nickName){
        this.nickName = nickName;
    }

    @Override
    public String getNickname() {
        return nickName;
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        boolean result = false;
        if (animal instanceof Gazelle || animal instanceof Zebra){
            result = true;
        }

        return result;
    }
}
