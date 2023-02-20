package animals;

/**
 * This is the Buzzard class which inherits the Aerial class
 */
public class Buzzard extends Aerial{

    public Buzzard(String nickName){
        this.nickName = nickName;
    }

    @Override
    public String getNickname() {
        return nickName;
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        boolean result = false;
        if (animal instanceof Buzzard){
            result = true;
        }
        return result;
    }
}
