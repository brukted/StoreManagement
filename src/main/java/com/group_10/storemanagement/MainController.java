package com.group_10.storemanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

public class MainController {
    private Connection connection = null;

    @FXML
    TextField sellCustomerID;
    @FXML
    TextField sellEmployeeID;
    @FXML
    Label sellTotal;
    @FXML
    TableView sellTable;
    @FXML
    ComboBox sellProductCombo;
    @FXML
    ComboBox sellStoreCombo;
    @FXML
    TextField sellQuantityField;

    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Sale> sales = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Store> stores = new ArrayList<>();
    ArrayList<Stock> stocks = new ArrayList<>();

    @FXML
    TableColumn sellProdCol;
    @FXML
    TableColumn sellQuantityCol;
    @FXML
    TableColumn sellPriceCol;
    @FXML
    TableColumn sellTotalCol;


    public void setConnection(Connection connection) {
        this.connection = connection;
        refreshDatabase();
    }

    void refreshDatabase() {
        updateProducts();
        updateStores();
        updateCustomers();
        updateEmployees();
        updateStocks();
        updateSales();
        updateTables();
    }

    ArrayList<SaleItemView> saleItemViewItems = new ArrayList<>();
    ObservableList<SaleItemView> observableList = FXCollections.observableList(saleItemViewItems);
    @FXML
    void sellAddProduct() {
        int i = sellProductCombo.getSelectionModel().selectedIndexProperty().get();
        Product p = products.get(i);
//        for(var it : observableList){
//            if (it.product.id == p.id){
//                observableList.removeIf((SaleItemView v) -> {return v.product.id == it.product.id;});
//            }
//        }
        SaleItemView item = new SaleItemView(Integer.valueOf(sellQuantityField.getText()),p);
        saleItemViewItems.add(item);
        sellProdCol.setCellFactory(new PropertyValueFactory<SaleItemView,String>("productName"));
        sellQuantityCol.setCellFactory(new PropertyValueFactory<SaleItemView,Integer>("quantity"));
        sellTotalCol.setCellFactory(new PropertyValueFactory<SaleItemView,Double>("total"));
        sellPriceCol.setCellFactory(new PropertyValueFactory<SaleItemView,Integer>("price"));
        sellTable.setItems(observableList);
    }

    void updateTables() {
    }

    @FXML
    void sell() {
        try {
            int storeId = 0;
            int employeeId = Integer.valueOf(sellEmployeeID.getText());
            for (var emp : employees) {
                if (emp.id == employeeId)
                    storeId = emp.store_id;
            }
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Sale(date_of_sale,employee_id,customer_id,store_id) VALUES(?, ?, ?,?)");
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            statement.setTimestamp(1, date);
            statement.setInt(2, employeeId);
            statement.setInt(3, Integer.valueOf(sellCustomerID.getText()));
            statement.setInt(4, storeId);
            statement.execute();
            statement.close();
            updateSales();
        } catch (SQLException exception) {
            var dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText(exception.getLocalizedMessage());
            dialog.setContentText(exception.toString());
            dialog.show();
        }
    }

    void updateProducts() {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("USE store_management;");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Product");
            products.clear();
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("product_id"), resultSet.getString("product_name"),
                        resultSet.getDouble("unit_price"), resultSet.getString("category")));
            }
            sellProductCombo.setItems(FXCollections.observableList(products));
            statement.close();
        } catch (SQLException exception) {
            var dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText(exception.getLocalizedMessage());
            dialog.setContentText(exception.toString());
            dialog.show();
        }
    }

    void updateCustomers() {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("USE store_management;");
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Customer");
            customers.clear();
            while (rs.next()) {
                var customer = new Customer(rs.getInt("customer_id"), rs.getString("first_name"),
                        rs.getString("last_name"), rs.getString("email"), rs.getString("phone_number"), rs.getInt("age"), rs.getString("sex"),
                        rs.getString("customer_city"), rs.getString("customer_street"), rs.getInt("customer_house_number"));
            }
            statement.close();
        } catch (SQLException exception) {
            var dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText(exception.getLocalizedMessage());
            dialog.setContentText(exception.toString());
            dialog.show();
        }
    }

    void updateSales() {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("USE store_management;");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Sale");
            sales.clear();
            while (resultSet.next()) {
                var sale = new Sale(((java.util.Date) resultSet.getDate("date_of_sale")), 0.f,
                        resultSet.getInt("employee_id"), resultSet.getInt("customer_id"), new ArrayList<SaleItem>());
                sales.add(sale);
            }
            statement.close();
        } catch (SQLException exception) {
            var dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText(exception.getLocalizedMessage());
            dialog.setContentText(exception.toString());
            dialog.show();
        }

    }

    void updateEmployees() {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("USE store_management;");
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Employee");
            employees.clear();
            while (rs.next()) {
                var employee = new Employee(rs.getInt("employee_id"),rs.getString("first_name"),rs.getString("last_name"),
                        rs.getString("email"),rs.getString("phone_number"),rs.getInt("age"),rs.getString("sex"),
                        rs.getString("city"),rs.getString("street"),rs.getInt("house_number"),rs.getInt("branch_id"));
                employees.add(employee);
            }
            statement.close();
        } catch (SQLException exception) {
            var dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText(exception.getLocalizedMessage());
            dialog.setContentText(exception.toString());
            dialog.show();
        }
    }

    void updateStocks() {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("USE store_management;");
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Stock JOIN Store ON store_id = stock_store_id JOIN Product ON product_id = stock_product_id");
            stocks.clear();
            while (rs.next()) {
                var stock = new Stock(rs.getString("store_name"), rs.getString("product_name"), rs.getInt("quantity"));
                stocks.add(stock);
            }
            statement.close();
        } catch (SQLException exception) {
            var dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText(exception.getLocalizedMessage());
            dialog.setContentText(exception.toString());
            dialog.show();
        }
    }

    void updateStores() {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("USE store_management;");
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Store");
            stores.clear();
            while (rs.next()) {
                var store = new Store(rs.getString("store_name"), rs.getString("store_city"), rs.getString("store_street"), rs.getString("store_house_number"), rs.getInt("store_id"));
                stores.add(store);
            }
            sellStoreCombo.setItems(FXCollections.observableList(stores));
            statement.close();
        } catch (SQLException exception) {
            var dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText(exception.getLocalizedMessage());
            dialog.setContentText(exception.toString());
            dialog.show();
        }
    }

}
