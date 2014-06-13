package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.entity.PurchaseRecordDetail;
import sample.repository.CommodityRepository;
import sample.repository.PurchaseRecordDetailRepository;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by Xiaohu on 14-6-10.
 */
@Component
public class PurchaseController implements Initializable {

    @FXML private TableView editTable;
    @FXML private TableColumn commodityNameColumn;
    @Autowired private CommodityRepository commodityRepository;
    @Autowired private PurchaseRecordDetailRepository purchaseRecordDetailRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commodityNameColumn.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(commodityRepository.findAll())));
        commodityNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                // TODO 
            }
        });
        ObservableList<PurchaseRecordDetail> data = FXCollections.observableArrayList(purchaseRecordDetailRepository.findAll());
        editTable.setItems(data);
    }

    public void addCommodity(ActionEvent actionEvent) {

    }
}
