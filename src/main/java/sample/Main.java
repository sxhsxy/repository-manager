package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SampleView sampleView = new SampleView();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(sampleView.getRoot(), 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
       launch(args);
    }


}
