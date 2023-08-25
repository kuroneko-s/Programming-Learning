package createObj.factoryMethod.after;

public class BlackShipFactory extends ShipAbstractFactory {
    @Override
    public Ship createChip() {
        return new WhiteShip();
    }
}
