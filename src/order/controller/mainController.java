package order.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import de.jensd.fx.glyphs.control.GlyphCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import order.common.openDialog;
import order.data.Order;
import order.data.dbo.connection;
import order.main;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML
    private GlyphCheckBox checkFilter;

    @FXML
    private JFXDatePicker dataBegin;

    @FXML
    private JFXDatePicker dataEnd;

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, String> bayerCell;

    @FXML
    private TableColumn<Order, String> DescriptionCell;

    @FXML
    private TableColumn<Order, Integer> stCell;

    @FXML
    private TableColumn<Order, Integer> priceCell;

    @FXML
    private TableColumn<Order, Integer> priceTotallCell;

    @FXML
    private TableColumn<Order, Integer> priceKPICell;

    @FXML
    private TableColumn<Order, Integer> priceRepearCell;

    @FXML
    private TableColumn<Order, Integer> priceAllCell;

    @FXML
    private TableColumn<Order, String> noteCell;

    @FXML
    private TableColumn<Order, Double> priceMainCell;

    @FXML
    private TableColumn<Order, Double> priceMargCell;
    @FXML
    private JFXButton addOrder;
    @FXML
    private JFXButton editOrder;
    @FXML
    private JFXButton deleteOrder;

    private connection con = new connection();
    private MongoDatabase database = con.dbConnection();

    private Order order;
    ObservableList <Order> orderList = FXCollections.observableArrayList();
    ObservableList <Order> orderDataList = FXCollections.observableArrayList();
    public MongoCollection<Document> collection = database.getCollection("work");
    public MongoCursor<Document> cursor = collection.find().iterator();

    @FXML
    private TableView<Order> dateTable;
    @FXML
    private TableColumn<Order, Date> dateColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataBegin.setValue(LocalDate.now());
        dataEnd.setValue(LocalDate.now());
 //       database = con.dbConnection();
       getData();
       getOrderDate();
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

    @FXML
    private void addOrderAction(ActionEvent actionEvent) throws IOException {
        openDialog(orderList);

    }

    @FXML
    private void editOrderAction(ActionEvent actionEvent) {
    }

    @FXML
    private void deleteOrderAction(ActionEvent actionEvent) {
    }

    public void openDialog(ObservableList <Order> orderList) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(main.class.getResource("/order/view/editview.fxml"));
        VBox page = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("ADD NEW ITEM");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setResizable(false);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.setIconified(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        editController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setOrder(orderList);
        dialogStage.showAndWait();


    }

    private void getOrderDate(){
        MongoCursor<Date> cursor = collection.distinct("order_date",Date.class).iterator();
        List<Order> l= new ArrayList<>();
        l.clear();
        while (cursor.hasNext()){
            Date doc = cursor.next();
            order = new Order(doc);
            l.add(order);
        }
        if(!orderDataList.isEmpty()){
            orderDataList.clear();
        }
        l.forEach((r) -> orderDataList.add(r));
        dateTable.setItems(orderDataList);
    }

    private void getData(){
        //MongoCursor<Document> cursor = collection.find().iterator();
        List<Order> l= new ArrayList<>();
        l.clear();
        for (int i=0; i<collection.count(); i++){
            Document doc = cursor.next();
            order = new Order(doc.getDate("order_date"),
                    doc.getString("bayer"),
                    doc.getString("description"),
                    doc.getInteger("st"),
                    doc.getInteger("price"),
                    doc.getInteger("priceTotall"),
                    doc.getDouble("priceKPI"),
                    doc.getInteger("priceRepear"),
                    doc.getInteger("priceAll"),
                    doc.getString("noteCell"),
                    doc.getDouble("priceMain"),
                    doc.getDouble("priceMarg"));
            l.add(order);
        }
        if(!orderList.isEmpty()){
            orderList.clear();
        }
        l.forEach((r) -> orderList.add(r));
        orderTable.setItems(orderList);
    }



}
