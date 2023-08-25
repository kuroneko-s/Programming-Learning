package createObj.factoryMethod.before;

public class ShipFactory {

    public static Ship orderShip(String name, String color) {
        return new Ship(name, color);
    }

}
