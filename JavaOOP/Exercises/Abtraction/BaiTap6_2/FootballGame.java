package Exercises.Abtraction.BaiTap6_2;

public class FootballGame extends Game {

    public FootballGame(String name, String company, double capacity) {
        super(name, company, capacity);
    }

    @Override
    public void start() {
        System.out.println("The FootballGame is ready to start!");
    }

    @Override
    public void play() {
        System.out.println("Let's play FootballGame");
    }

    @Override
    public void end() {
        System.out.println("This Footballgame is end now");
    }

}
