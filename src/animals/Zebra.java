package animals;

/**
 * This is the Zebra class which inherits the Terrestrial class
 */
public class Zebra extends Terrestrial{

    public Zebra(String nickName){
        this.nickName = nickName;
    }


    @Override
    public String getNickname() {
        return nickName;
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        boolean result = false;
        if (animal instanceof Zebra || animal instanceof Gazelle){
            result = true;
        }
        return result;
    }
}
