package animals;

/**
 * This is the Seal class which inherits the Aquatic class
 */
public class Seal extends Aquatic{

    public Seal(String nickName){
        this.nickName = nickName;
    }

    @Override
    public String getNickname() {
        return nickName;
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        boolean result = false;
        if (animal instanceof Seal || animal instanceof Starfish){
            result = true;
        }
        return result;
    }

}
