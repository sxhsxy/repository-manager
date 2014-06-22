package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Xiaohu on 14-6-9.
 */
@Component
public class MainFrameController implements Initializable {
    @FXML private BorderPane borderPane;
    @FXML private Pane centerPane;

    public void loadSupplierView(ActionEvent actionEvent) {
        SupplierView supplierView = new SupplierView();
        Pane pane = (Pane) supplierView.getRoot();
        centerPane.getChildren().clear();
        centerPane.getChildren().add(pane);
        pane.maxWidthProperty().bind(centerPane.widthProperty());
        pane.maxHeightProperty().bind(centerPane.heightProperty());
        pane.minWidthProperty().bind(centerPane.widthProperty());
        pane.minHeightProperty().bind(centerPane.heightProperty());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadSupplierView(null);
    }

    public void loadPurchaseView(ActionEvent actionEvent) {
        PurchaseView purchaseView = new PurchaseView();
        Pane pane = (Pane) purchaseView.getRoot();
        centerPane.getChildren().clear();
        centerPane.getChildren().add(pane);
        pane.maxWidthProperty().bind(centerPane.widthProperty());
        pane.maxHeightProperty().bind(centerPane.heightProperty());
        pane.minWidthProperty().bind(centerPane.widthProperty());
        pane.minHeightProperty().bind(centerPane.heightProperty());
    }
}
