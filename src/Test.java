import animals.*;
import areas.*;
import zoo.Zoo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {


        /**


        System.out.println(nParrot.getNickname());
        System.out.println(newBuzzards.getNickname());
        System.out.println(newGazellas.getNickname());

        System.out.println(nParrot.isCompatibleWith(newLion));
        System.out.println(nParrot.isCompatibleWith(newParrot));
        System.out.println(newGazellas.isCompatibleWith(newBuzzards));
        System.out.println(newStarfish.isCompatibleWith(newShark));
        System.out.println(newStarfish.isCompatibleWith(newSeal));
         **/

        /*
        System.out.println(cage.getMaxNumberOfAnimals());
        System.out.println(aquarium.getMaxNumberOfAnimals());
        System.out.println(enclosure.getMaxNumberOfAnimals());

         */

        /*
        System.out.println(zooArea.removeArea(1));
        System.out.println(zooArea.removeArea(4));
        System.out.println(zooArea.getArea(2));
        System.out.println(zooArea.getArea(3));

         */
        /*

        Zoo zooArea = new Zoo();

        Parrot nParrot = new Parrot("kjfh");
        Animal newLion = new Lion("hguy") ;
        Shark newShark = new Shark("shark");
        Animal nShark = new Shark("shark2");
        Animal newSeal = new Seal("seal");
        Zebra newZebra = new Zebra("zebra");
        Starfish newStarfish = new Starfish("starfish");
        Animal newGazellas = new Gazelle("gazellas");
        Animal nGazellas = new Gazelle("gaz");
        Buzzard newBuzzards = new Buzzard("buzzards");
        Animal newParrot = new Parrot("kdjhoa") ;

        Entrance entrance = new Entrance();
        Cage cage = new Cage(6);
        Cage caage = new Cage(7);
        Habitat aquarium = new Aquarium(4);
        NonHabitat picnicArea = new PicnicArea();
        Enclosure enclosure = new Enclosure(2);




        zooArea.addArea(cage);
        zooArea.addArea(caage);

        zooArea.addArea(aquarium);
        zooArea.addArea(enclosure);
        zooArea.addArea(picnicArea);



        zooArea.connectAreas(0,1);
        zooArea.connectAreas(0,2);
        zooArea.connectAreas(0,4);
        zooArea.connectAreas(2,4);
        zooArea.connectAreas(2,1);
        //zooArea.connectAreas(4,5);

        /*

        byte a = zooArea.addAnimal(3,newShark);
        System.out.println(a);
        byte b = zooArea.addAnimal(3,newLion);
        System.out.println(b);
        byte c = zooArea.addAnimal(3,nShark);
        System.out.println(c);
        byte d = zooArea.addAnimal(4,newZebra);
        System.out.println(d);
        byte e = zooArea.addAnimal(4,newGazellas);
        System.out.println(e);
        byte f = zooArea.addAnimal(4,nGazellas);
        System.out.println(f);

         */

       // System.out.println(10/3);

        Zoo zoo= new Zoo();
        zoo.addArea(new PicnicArea());
        zoo.addArea(new Enclosure(10));
        zoo.addArea(new Cage(3));
        zoo.addArea((new Cage(3)));
        zoo.addArea((new Cage(3)));
        zoo.addArea((new Cage(3)));
        zoo.addArea((new Cage(3)));
        zoo.addArea((new Cage(3)));

        zoo.connectAreas(0,1);
        zoo.connectAreas(0,2);
        zoo.connectAreas(1,3);
        zoo.connectAreas(2,3);
        zoo.connectAreas(3,4);
        zoo.connectAreas(4,2);
        zoo.connectAreas(4,1);
        zoo.connectAreas(1,5);
        zoo.connectAreas(2,5);
        zoo.connectAreas(0,6);
        zoo.connectAreas(5,7);
        zoo.connectAreas(3,7);
        zoo.connectAreas(5,6);
        System.out.println(zoo.findUnreachableAreas());











    }

}
