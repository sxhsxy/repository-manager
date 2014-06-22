package sample;

import impl.org.controlsfx.i18n.Localization;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.entity.Supplier;

import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //SampleView sampleView = new SampleView();
        MainFrameView mainFrameView = new MainFrameView();
        //controlsfx dialog's properties file do not have correct character for Chinese.
        Localization.setLocale(new Locale("en"));
        PurchaseView purchaseView = new PurchaseView();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(mainFrameView.getRoot(), 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
       launch(args);
    }


}
