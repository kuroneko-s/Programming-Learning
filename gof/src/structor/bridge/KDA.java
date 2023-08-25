package structor.bridge;

public class KDA implements Skin {
    private String name;

    public KDA(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
