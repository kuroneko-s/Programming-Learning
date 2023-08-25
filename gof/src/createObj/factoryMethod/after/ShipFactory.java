package createObj.factoryMethod.after;

public interface ShipFactory {
    default Ship orderShip(String name, String color) {
        validate(name, color);
        prepareFor(name);
        Ship ship = createChip();
        senEmailTo(name, color);
        return ship;
    }

    void senEmailTo(String name, String color);

    Ship createChip();

    private void validate(String name, String color) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름이 이상해요.");
        }

        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("색상이 이상해요");
        }
    }

    private void prepareFor(String name) {
        System.out.println("배 만드는 중 " + name);
    }
}
