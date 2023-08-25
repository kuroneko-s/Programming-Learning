package createObj.abstractFactory.parts;

import createObj.abstractFactory.model.Anchor;
import createObj.abstractFactory.model.Wheel;

public interface PartsFactory {
    Anchor createAnchor();

    Wheel createWheel();
}
