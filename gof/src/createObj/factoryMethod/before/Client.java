package createObj.factoryMethod.before;

public class Client {
    public void run() {
        Ship whiteShip = ShipFactory.orderShip("dong", "white");
        System.out.println(whiteShip);

        Ship blackShip = ShipFactory.orderShip("hyuk", "black");
        System.out.println(blackShip);
    }
}
