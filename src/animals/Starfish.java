package animals;

/**
 * This is the Starfish class which inherits the Aquatic class
 */
public class Starfish extends Aquatic{

    public Starfish(String nickName){
        this.nickName = nickName;
    }

    @Override
    public String getNickname() {
        return nickName;
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        boolean result = false;
        if (animal instanceof Aquatic){
            result = true;
        }
        return result;
    }
}
