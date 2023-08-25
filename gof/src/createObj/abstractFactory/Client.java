package createObj.abstractFactory;

import createObj.abstractFactory.factory.WhiteShipFactory;
import createObj.abstractFactory.model.Ship;
import createObj.abstractFactory.parts.WhiteShipPartsFactory;
import createObj.abstractFactory.parts.WhiteShipProPartsFactory;

public class Client {
    public static void run() {
        WhiteShipFactory whiteShipFactory = new WhiteShipFactory(new WhiteShipPartsFactory());
        Ship ship_1 = whiteShipFactory.createShip();
        System.out.println(ship_1.getAnchor().getClass());
        System.out.println(ship_1.getWheel().getClass());

        WhiteShipFactory shipFactory = new WhiteShipFactory(new WhiteShipProPartsFactory());
        Ship ship_2 = shipFactory.createShip();
        System.out.println(ship_2.getAnchor().getClass());
        System.out.println(ship_2.getWheel().getClass());
    }
}
