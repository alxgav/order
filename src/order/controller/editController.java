package order.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import order.data.Order;

import order.data.dbo.connection;
import org.bson.Document;
import java.net.URL;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class editController implements Initializable {
    @FXML
    private JFXDatePicker dataOrder;
    @FXML
    private JFXComboBox sellerBox;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField price;
    @FXML
    private JFXTextField rep_price;
    @FXML
    private JFXTextField my_price;
    @FXML
    private Button addBtn;

    private Stage dialogStage;
    private Order order ;
    ObservableList <Order> orderList;
    private boolean okClicked = false;
    @FXML
    private Button canselBtn;
    @FXML
    private JFXTextField quontity;

    MongoDatabase database;
    connection con = new connection();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        database = con.dbConnection();
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "SKYSERVICE",
                        "TEHNOPLUS",
                        "VSEPLUS",
                        "TABPARTS"
                );
        sellerBox.setItems(options);
        sellerBox.getSelectionModel().select(1);
        dataOrder.setValue(LocalDate.now());
    }

    public void setOrder(ObservableList <Order> orderList){
         this.orderList = orderList;
    }

    @FXML
    private void addBtnAction(ActionEvent actionEvent) {
        orderList.add(getOrder());
        saveOrder(getOrder());

        dialogStage.close();


    }
    private Order getOrder(){
        LocalDate order_date = dataOrder.getValue();
        Integer quantity = Integer.valueOf(quontity.getText());
        Integer price1 = Integer.valueOf(price.getText());
        Integer repPrice1 = Integer.valueOf(rep_price.getText());
        Integer priceTotall = price1*quantity;
        Double priceKPI = price1 < 1000 ? price1 * 1.3 : price1 * 1.2;
        Integer priceAll = Math.toIntExact(Math.round(priceKPI) + repPrice1);
        Double myPrice1 = Double.valueOf(my_price.getText());
        Double marg = 0.00;
        order = new Order(Date.from(order_date.atStartOfDay(ZoneId.systemDefault()).toInstant()),
                        sellerBox.getSelectionModel().getSelectedItem().toString(),
                        description.getText(),
                        quantity,
                        price1,
                        priceTotall,
                        priceKPI,
                        repPrice1,
                        priceAll,
                        "",
                        myPrice1,
                        marg
                        );
        return order;
    }

    private void saveOrder(Order order){
      Document doc = new Document("order_date", order.getOrder_date()).
               append("bayer", order.getBayer()).
               append("description", order.getDescription()).append("st",order.getSt()).append("price", order.getPrice()).
               append("priceTotall", order.getPriceTotall()).append("priceKPI", order.getPriceKPI()).append("priceRepear", order.getPriceRepear()).
               append("priceAll", order.getPriceAll()).append("noteCell", order.getNoteCell()).append("priceMain", order.getPriceMain()).append("priceMarg", order.getPriceMarg());
      MongoCollection<Document> collection = database.getCollection("work");
      collection.insertOne(doc);

    }

    @FXML
    private void canselBtnAction(ActionEvent actionEvent) {
        dialogStage.close();
    }
}
