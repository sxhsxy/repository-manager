package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.entity.Supplier;
import sample.repository.SupplierRepository;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Xiaohu on 14-6-6.
 */
@Component
public class SupplierController implements Initializable {
    @Autowired private SupplierRepository supplierRepository;

    @FXML private TableView tableView;
    @FXML private Button add;
    @FXML private Button edit;
    @FXML private Button delete;
    @FXML private TextField name;
    @FXML private TextField linkman;
    @FXML private TextField tel;
    @FXML private TextField fax;
    @FXML private TextField email;
    @FXML private TextField address;
    @FXML private Button cancel;
    @FXML private Button save;

    private Supplier selected = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void initializeTable() {

        final ObservableList<Supplier> data = FXCollections.observableArrayList(supplierRepository.findAll());
        tableView.setItems(data);
    }

    public void selectRow(Event event) {
        selected = (Supplier)tableView.getSelectionModel().getSelectedItem();
        name.setText(selected.getName());
        linkman.setText(selected.getLinkman());
        tel.setText(selected.getTel());
        fax.setText(selected.getFax());
        email.setText(selected.getEmail());
        address.setText(selected.getAddress());
    }

    public void addSupplier(ActionEvent actionEvent) {
    }

    public void editSelectedSupplier(ActionEvent actionEvent) {
    }

    public void deleteSelectedSupplier(ActionEvent actionEvent) {
        tableView.getSelectionModel().selectNext();
        supplierRepository.delete(selected);
        tableView.getItems().remove(selected);
        selectRow(null);
    }


}
