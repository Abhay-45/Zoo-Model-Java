import animals.Lion;
import animals.Seal;
import areas.Aquarium;
import areas.Cage;
import areas.Enclosure;
import areas.PicnicArea;
import zoo.Zoo;

import java.util.ArrayList;
import java.util.Set;
public class TestUnreachable {
    public static void main(String[] args) {
        Zoo aZoo = new Zoo();
        aZoo.addArea(new Aquarium(4));
        aZoo.addArea(new Enclosure(5));
        aZoo.addArea(new Cage(3));
        aZoo.addArea(new PicnicArea());
        aZoo.addArea(new Aquarium(6));

        aZoo.connectAreas(0,1);
        aZoo.connectAreas(1,2);
        aZoo.connectAreas(1,3);
        aZoo.connectAreas(3,5);
        aZoo.connectAreas(2,1);
        aZoo.connectAreas(1,0);

        Set<Integer> unr = aZoo.findUnreachableAreas();
        System.out.println(unr);

    }
}