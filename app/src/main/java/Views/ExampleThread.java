package Views;

public class ExampleThread extends Thread {
    @Override
    public void run() {
        super.run();
        for(int i=0;i<10;i++){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }}
}
