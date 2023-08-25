package createObj.abstractFactory.parts;

import createObj.abstractFactory.model.Anchor;
import createObj.abstractFactory.model.Wheel;
import createObj.abstractFactory.model.WhiteProAnchor;
import createObj.abstractFactory.model.WhiteProWheel;

public class WhiteShipProPartsFactory implements PartsFactory {
    @Override
    public Anchor createAnchor() {
        return new WhiteProAnchor();
    }

    @Override
    public Wheel createWheel() {
        return new WhiteProWheel();
    }
}
