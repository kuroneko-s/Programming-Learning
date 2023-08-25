package structor.bridge;

public class DefaultChampion implements Champion {
    private Skin skin; // 여기가 bridge pattern의 핵심

    private String name;

    public DefaultChampion(Skin skin, String name) {
        this.skin = skin;
        this.name = name;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    @Override
    public void move() {
        System.out.printf("%s, %s, move\n", skin.getName(), this.name);
    }

    @Override
    public void skill_Q() {
        System.out.printf("%s, %s, skill_Q\n", skin.getName(), this.name);
    }

    @Override
    public void skill_W() {
        System.out.printf("%s, %s, skill_W\n", skin.getName(), this.name);
    }

    @Override
    public void skill_E() {
        System.out.printf("%s, %s, skill_E\n", skin.getName(), this.name);
    }

    @Override
    public void skill_R() {
        System.out.printf("%s, %s, skill_R\n", skin.getName(), this.name);
    }
}
