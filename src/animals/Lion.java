package animals;

/**
 * This is the Lion class which inherits the Terrestrial class
 */
public class Lion extends Terrestrial{

    public Lion(String nickName) {
    this.nickName = nickName;
    }

    @Override
    public String getNickname() {
        return nickName;
    }

    @Override
    public boolean isCompatibleWith(Animal animal) {
        boolean result = false;
        if (animal instanceof Lion) {
            result = true;
        }
        return result;
    }
}
