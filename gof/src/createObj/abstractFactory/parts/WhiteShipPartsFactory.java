package createObj.abstractFactory.parts;

import createObj.abstractFactory.model.Anchor;
import createObj.abstractFactory.model.Wheel;
import createObj.abstractFactory.model.WhiteAnchor;
import createObj.abstractFactory.model.WhiteWheel;

public class WhiteShipPartsFactory implements PartsFactory {
    @Override
    public Anchor createAnchor() {
        return new WhiteAnchor();
    }

    @Override
    public Wheel createWheel() {
        return new WhiteWheel();
    }
}
