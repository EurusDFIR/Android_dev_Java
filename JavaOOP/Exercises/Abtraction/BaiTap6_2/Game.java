package Exercises.Abtraction.BaiTap6_2;

public abstract class Game {
    protected String name;
    protected String company;
    protected double capacity;

    public Game(String name, String company, double capacity) {
        this.name = name;
        this.company = company;
        this.capacity = capacity;
    }

    public abstract void start();

    public abstract void play();

    public abstract void end();

}
