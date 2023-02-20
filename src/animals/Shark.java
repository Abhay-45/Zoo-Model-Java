package animals;

/**
 * This is the Shark class which inherits the Aquatic class
 */
public class Shark extends Aquatic{

    public Shark(String nickName){
        this.nickName = nickName;
    }

    @Override
    public String getNickname() {
        return nickName;
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        boolean result = false;
        if (animal instanceof Shark || animal instanceof Starfish){
            result = true;
        }
        return result;
    }
}
