package createObj.singleton;

import java.io.Serializable;

public class Singleton implements Serializable {

    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
        if (INSTANCE != null) {
            throw new RuntimeException("접근 불가");
        }
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    protected Object readResolve() {
        return getInstance();
    }
}
