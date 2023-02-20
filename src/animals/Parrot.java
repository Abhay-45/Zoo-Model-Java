package animals;

/**
 * This is the Parrot class which inherits the Aerial class
 */
public class Parrot extends Aerial{

    public Parrot(String nickName){
        this.nickName = nickName;
    }

    @Override
    public String getNickname() {
        return nickName;
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        boolean result = false;
        if (animal instanceof Parrot){
            result = true;
        }
        return result;
    }
}
