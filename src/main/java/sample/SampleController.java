package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.entity.Supplier;
import sample.repository.SupplierRepository;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SampleController implements Initializable {
    @FXML private TableView tableView1;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn phoneColumn;
    @FXML private TableColumn addressColumn;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeTable();
        tableView1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void initializeTable() {
        supplierRepository.save(new Supplier("xxx", "123", "hz"));
        final ObservableList<Supplier> data = FXCollections.observableArrayList(
                new Supplier("Jacob", "13909876543", "jacob.smith@example.com"),
                new Supplier("Isabella", "057112345678", "isabella.johnson@example.com"),
                new Supplier("Ethan", "Williams", "ethan.williams@example.com"),
                new Supplier("Emma", "Jones", "emma.jones@example.com"),
                new Supplier("Michael", "Brown", "michael.brown@example.com")
        );
        tableView1.setItems(data);
    }

    @FXML
    private void editSelectedSupplier() {
        ObservableList<Supplier> selected = tableView1.getSelectionModel().getSelectedItems();
        for(Supplier supplier : selected) {
            supplier.setTel("12388889999");
        }
    }
}
