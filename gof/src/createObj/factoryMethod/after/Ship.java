package createObj.factoryMethod.after;

public class Ship {
    private String name;

    private String color;

    private String logo;

    private String message;

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

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogo() {
        return logo;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return "name - " + this.name + ", color - " + this.color + ", logo - " + this.logo + ", message - " + this.message;
    }
}
