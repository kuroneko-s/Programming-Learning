package structor.bridge;

public class Client {
    public static void main(String[] args) {
        Champion champion = new Ari(new KDA("KDA"));
        champion.skill_Q();
        champion.skill_R();
    }
}
