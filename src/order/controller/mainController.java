package order.controller;

import com.jfoenix.controls.JFXDatePicker;
import de.jensd.fx.glyphs.control.GlyphCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    private GlyphCheckBox checkFilter;

    @FXML
    private JFXDatePicker dataBegin;

    @FXML
    private JFXDatePicker dataEnd;

    @FXML
    private TableView<?> orderTable;

    @FXML
    private TableColumn<?, ?> bayerCell;

    @FXML
    private TableColumn<?, ?> DescriptionCell;

    @FXML
    private TableColumn<?, ?> stCell;

    @FXML
    private TableColumn<?, ?> priceCell;

    @FXML
    private TableColumn<?, ?> priceTotallCell;

    @FXML
    private TableColumn<?, ?> priceKPICell;

    @FXML
    private TableColumn<?, ?> priceRepearCell;

    @FXML
    private TableColumn<?, ?> priceAllCell;

    @FXML
    private TableColumn<?, ?> noteCell;

    @FXML
    private TableColumn<?, ?> priceMainCell;

    @FXML
    private TableColumn<?, ?> priceMargCell;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataBegin.setValue(LocalDate.now());
        dataEnd.setValue(LocalDate.now());
    }

    @FXML
    private void checkFilterAction(ActionEvent actionEvent) {
        System.out.println(checkFilter.isSelected());
        if (checkFilter.isSelected()==true){
            dataBegin.setDisable(false);
            dataEnd.setDisable(false);
        } else {
            dataBegin.setDisable(true);
            dataEnd.setDisable(true);
        }
    }
}
