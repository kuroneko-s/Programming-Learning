package createObj.factoryMethod.after;

public class WhiteShipFactory extends ShipAbstractFactory {
    @Override
    public Ship createChip() {
        return new WhiteShip();
    }
}
