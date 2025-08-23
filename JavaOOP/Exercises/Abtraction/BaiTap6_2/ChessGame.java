package Exercises.Abtraction.BaiTap6_2;

public class ChessGame extends Game {
    public ChessGame(String name, String company, double capacity) {
        super(name, company, capacity);
    }

    @Override
    public void start() {
        System.out.println("the ChessGame is ready to start!");

    }

    @Override
    public void play() {
        System.out.println("Let's move E4 to play your pieces");
    }

    @Override
    public void end() {
        System.out.println("Did you want to end this game!");

    }

}
