package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;

/**
 * Created by Xiaohu on 14-6-9.
 */
@Component
public class MainFrameController {
    @FXML private BorderPane borderPane;
    @FXML private Pane centerPane;

    public void loadSupplierView(ActionEvent actionEvent) {
        SupplierView supplierView = new SupplierView();
        centerPane.getChildren().add(supplierView.getRoot());
    }
}
