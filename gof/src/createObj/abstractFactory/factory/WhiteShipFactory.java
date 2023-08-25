package createObj.abstractFactory.factory;

import createObj.abstractFactory.model.Ship;
import createObj.abstractFactory.model.WhiteShip;
import createObj.abstractFactory.parts.PartsFactory;

public class WhiteShipFactory implements ShipFactory {

    private PartsFactory factory;

    public WhiteShipFactory(PartsFactory factory) {
        this.factory = factory;
    }

    @Override
    public Ship createShip() {
        WhiteShip whiteShip = new WhiteShip();
        whiteShip.setAnchor(factory.createAnchor());
        whiteShip.setWheel(factory.createWheel());
        return whiteShip;
    }
}
