package createObj.factoryMethod.after;

public abstract class ShipAbstractFactory implements ShipFactory {
    @Override
    public void senEmailTo(String name, String color) {
        System.out.println(name + " 다 만들었어요~");
    };
}
