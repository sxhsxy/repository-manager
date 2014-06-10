package sample.util;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.util.Callback;

/**
 * Created by Xiaohu on 14-6-10.
 */
public class ComboBoxTableCellFactory<S,T> implements Callback<TableColumn<S,T>,TableCell<S,T>> {

    @Override
    public TableCell<S, T> call(TableColumn<S, T> stTableColumn) {
        ComboBoxTableCell<S,T> comboBoxTableCell = new ComboBoxTableCell<S,T>();
        // TODO 自定义ComboBox
        return comboBoxTableCell;
    }
}
