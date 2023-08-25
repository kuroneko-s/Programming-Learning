package createObj.factoryMethod.after;

public class Client {
    public void run() {
        Ship ship = new WhiteShipFactory().orderShip("whiteship", "white");
        System.out.println(ship);
        Ship ship2 = new BlackShipFactory().orderShip("blackship", "black");
        System.out.println(ship2);
    }
}
