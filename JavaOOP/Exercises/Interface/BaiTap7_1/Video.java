package Exercises.Interface.BaiTap7_1;

public class Video implements Playable, Downloadable {

    @Override
    public void play() {
        System.out.println("Press the button to play!");
    }

    @Override
    public void pause() {
        System.out.println("Press the button to pause!");
    }

    @Override
    public void stop() {
        System.out.println("Press the button to stop!");
    }

    @Override
    public void download() {
        System.out.println("The system is dowloading file");
    }

    @Override
    public void getFileSize() {
        System.out.println("Capacity of file");
    }
}
