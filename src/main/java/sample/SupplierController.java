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
import javafx.scene.layout.VBox;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.DialogStyle;
import org.controlsfx.dialog.Dialogs;
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
    @FXML private VBox editBox;

    private Supplier selected = null;
    private Supplier editOne = null;

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
        if(selected != null) {
        name.setText(selected.getName());
        linkman.setText(selected.getLinkman());
        tel.setText(selected.getTel());
        fax.setText(selected.getFax());
        email.setText(selected.getEmail());
        address.setText(selected.getAddress());
        } else {
            name.setText("");
            linkman.setText("");
            tel.setText("");
            fax.setText("");
            email.setText("");
            address.setText("");

        }
    }

    public void addSupplier(ActionEvent actionEvent) {
        editOne = new Supplier();
        name.setText("");
        linkman.setText("");
        tel.setText("");
        fax.setText("");
        email.setText("");
        address.setText("");
        editBox.setDisable(false);

        name.requestFocus();
    }

    public void editSelectedSupplier(ActionEvent actionEvent) {
        editOne = selected;
        editBox.setDisable(false);
        //name.requestFocus();
    }

    public void deleteSelectedSupplier(ActionEvent actionEvent) {
        tableView.getSelectionModel().selectNext();
        if(selected != null) {
        supplierRepository.delete(selected);
        tableView.getItems().remove(selected);
        selectRow(null);
        }
    }

    public void save(ActionEvent actionEvent) {
        try {
            String input = name.getText();

            if (input == null || input.trim().equals("")) {
                throw new StringIndexOutOfBoundsException();
            } else {
                editOne.setName(name.getText());
                editOne.setTel(tel.getText());
                editOne.setFax(fax.getText());
                editOne.setLinkman(linkman.getText());
                editOne.setEmail(email.getText());
                editOne.setAddress(address.getText());

                supplierRepository.save(editOne);
                editBox.setDisable(true);
                if(!tableView.getItems().contains(editOne))
                    tableView.getItems().add(editOne);
            }
        } catch (StringIndexOutOfBoundsException e) {
            Dialogs.create().title("").message("输入名称有误，请重新输入！").showWarning();
        } catch (Exception e) {
            new Dialog(null, "该名称已存在，请重新输入！").show();
        }
    }

    public void cancel(ActionEvent actionEvent) {
        editBox.setDisable(true);
        selectRow(null);
    }

}
