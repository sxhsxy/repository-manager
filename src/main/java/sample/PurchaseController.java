package sample;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.LongStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.entity.PurchaseRecord;
import sample.entity.PurchaseRecordDetail;
import sample.entity.Supplier;
import sample.repository.CommodityRepository;
import sample.repository.PurchaseRecordDetailRepository;
import sample.repository.PurchaseRecordRepository;
import sample.repository.SupplierRepository;
import sample.service.PurchaseService;
import sample.util.SupplierStringConverter;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Xiaohu on 14-6-10.
 */
@Component
public class PurchaseController implements Initializable {

    @FXML private TableView<PurchaseRecordDetail> commodityEditTable;
    @FXML private TableColumn commodityNameColumn;
    @FXML private TableColumn commodityCostColumn;
    @FXML private TableColumn commodityQuantityColumn;
    @FXML private TableView<PurchaseRecord> purchaseTable;
    @FXML private TableView<PurchaseRecordDetail> commodityListTable;
    @FXML private TextField purchaseNumberText;
    @FXML private ComboBox<Supplier> supplierComboBox;
    @FXML private TextField datetimeText;
    @FXML private ToolBar commodityEditToolBar;
    @FXML private TitledPane purchaseRecordEditPane;
    @FXML private TitledPane purchaseRecordListPane;
    @Autowired private PurchaseRecordRepository purchaseRecordRepository;
    @Autowired private SupplierRepository supplierRepository;
    @Autowired private CommodityRepository commodityRepository;
    @Autowired private PurchaseRecordDetailRepository purchaseRecordDetailRepository;
    @Autowired private PurchaseService purchaseService;
    @Autowired private SupplierStringConverter supplierStringConverter;

    private PurchaseRecord purchaseRecordEditing;
    private List<PurchaseRecordDetail> purchaseRecordDetailsToDelete;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        purchaseTable.setItems(FXCollections.observableArrayList(purchaseRecordRepository.findAll()));
        purchaseTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PurchaseRecord>() {
            @Override
            public void changed(ObservableValue<? extends PurchaseRecord> observable, PurchaseRecord oldValue, PurchaseRecord newValue) {
//                ObservableList<PurchaseRecordDetail> detailList = FXCollections.observableArrayList(purchaseService.findPurchaseRecordEager(newValue).getPurchaseRecordDetails());
//                commodityListTable.setItems(detailList);
                refreshCommodityListTable();
            }
        });
        supplierComboBox.setCellFactory(TextFieldListCell.forListView((StringConverter<Supplier>) supplierStringConverter));
        supplierComboBox.setItems(FXCollections.observableArrayList(supplierRepository.findAll()));
        commodityNameColumn.setCellFactory(ComboBoxTableCell.forTableColumn(FXCollections.observableArrayList(commodityRepository.findAll())));
        commodityCostColumn.setCellFactory(TextFieldTableCell.forTableColumn((StringConverter) new BigDecimalStringConverter()));
        commodityQuantityColumn.setCellFactory(TextFieldTableCell.forTableColumn((StringConverter) new LongStringConverter()));
//        commodityNameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent event) {
//                // TODO
//            }
//        });
//        commodityCostColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent event) {
//                PurchaseRecordDetail editedRow = (PurchaseRecordDetail)event.getRowValue();
//                System.out.println(editedRow.getCommodity().toString() + editedRow.getCost());
//            }
//        });
//        ObservableList<PurchaseRecordDetail> data = FXCollections.observableArrayList(purchaseRecordDetailRepository.findAll());
//        commodityEditTable.setItems(data);
    }

    public void refreshCommodityListTable() {
        PurchaseRecord p = purchaseTable.getSelectionModel().getSelectedItem();
        if(p!=null) {
            ObservableList<PurchaseRecordDetail> detailList = FXCollections.observableArrayList(purchaseService.findPurchaseRecordEager(p).getPurchaseRecordDetails());
            commodityListTable.setItems(detailList);
        }

    }

    public void refreshPurchaseRecordListTable() {

    }

    public void newPurchaseRecord(ActionEvent actionEvent) {
        purchaseRecordDetailsToDelete = new ArrayList<PurchaseRecordDetail>();
        purchaseRecordEditing = new PurchaseRecord();
        purchaseRecordEditing.setDatetime(new Timestamp(System.currentTimeMillis()));
        purchaseNumberText.setText(purchaseRecordEditing.getId().toString());
        datetimeText.setText(purchaseRecordEditing.getDatetime().toString());
        supplierComboBox.getSelectionModel().select(purchaseRecordEditing.getSupplier());
        //supplierComboBox.getSelectionModel().select(purchaseRecord.getSupplier());
        purchaseRecordEditPane.setDisable(false);
        purchaseRecordListPane.setDisable(true);
    }

    public void editPurchaseRecord(ActionEvent actionEvent) {
        purchaseRecordDetailsToDelete = new ArrayList<PurchaseRecordDetail>();
        purchaseRecordEditing = purchaseTable.getSelectionModel().getSelectedItem();
        purchaseNumberText.setText(purchaseRecordEditing.getId().toString());
        datetimeText.setText(purchaseRecordEditing.getDatetime().toString());
        supplierComboBox.getSelectionModel().select(purchaseRecordEditing.getSupplier());
        commodityEditTable.setItems(FXCollections.observableArrayList(purchaseService.findPurchaseRecordEager(purchaseRecordEditing).getPurchaseRecordDetails()));
        purchaseRecordEditPane.setDisable(false);
        purchaseRecordListPane.setDisable(true);
    }

    public void deletePurchaseRecord(ActionEvent actionEvent) {
        PurchaseRecord purchaseRecord = purchaseTable.getSelectionModel().getSelectedItem();
        purchaseService.delete(purchaseRecord);
        purchaseTable.getItems().remove(purchaseRecord);
    }

    public void savePurchaseRecord(ActionEvent actionEvent) {
        purchaseRecordDetailRepository.delete(purchaseRecordDetailsToDelete);
        purchaseRecordEditing.setSupplier(supplierComboBox.getValue());

        purchaseRecordEditing = purchaseRecordRepository.save(purchaseRecordEditing);
        for(PurchaseRecordDetail p : commodityEditTable.getItems()) {
            p.setPurchaseRecord(purchaseRecordEditing);
            if(p.getCommodity() != null) purchaseRecordDetailRepository.save(p);
        }

        boolean existed = false;
        for(PurchaseRecord p : purchaseTable.getItems()) {
//            Long x = p.getId();
//            Long y = purchaseRecordEditing.getId();
            if(p.getId().longValue() == purchaseRecordEditing.getId().longValue()) {
                existed = true;
                break;
            }
        }
        if(!existed) purchaseTable.getItems().add((purchaseRecordEditing));

        purchaseRecordEditPane.setDisable(true);
        purchaseRecordListPane.setDisable(false);
        refreshCommodityListTable();
    }

    public void cancelPurchaseRecord(ActionEvent actionEvent) {

        purchaseRecordEditPane.setDisable(true);
        purchaseRecordListPane.setDisable(false);
    }

    public void addCommodity(ActionEvent actionEvent) {
        commodityEditTable.getItems().add(new PurchaseRecordDetail());
    }

    public void deleteCommodity(ActionEvent actionEvent) {
        PurchaseRecordDetail purchaseRecordDetail = (PurchaseRecordDetail) commodityEditTable.getSelectionModel().getSelectedItem();
        commodityEditTable.getItems().remove(purchaseRecordDetail);
        purchaseRecordDetailsToDelete.add(purchaseRecordDetail);
    }
}
