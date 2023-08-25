package createObj.factoryMethod.before;

public class Ship {
    private String name;

    private String color;

    public Ship() {}

    public Ship(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "name - " + this.name + ", color - " + this.color;
    }
}
