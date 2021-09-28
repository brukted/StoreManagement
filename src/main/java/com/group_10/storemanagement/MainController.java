package com.group_10.storemanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class MainController {
    private Connection connection = null;


    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Sale> sales = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Store> stores = new ArrayList<>();
    ArrayList<Stock> stocks = new ArrayList<>();

    ///Sell

    @FXML
    TextField sellCustomerID;
    @FXML
    TextField sellEmployeeID;
    @FXML
    Label sellTotal;
    @FXML
    TableView<SaleItemView> sellTable;
    @FXML
    ComboBox sellProductCombo;
    @FXML
    ComboBox sellStoreCombo;

    @FXML
    TextField sellQuantityField;
    @FXML
    TableColumn<SaleItemView, String> sellProdCol;
    @FXML
    TableColumn<SaleItemView, Integer> sellQuantityCol;
    @FXML
    TableColumn<SaleItemView, Double> sellPriceCol;
    @FXML
    TableColumn<SaleItemView, Double> sellTotalCol;

    ArrayList<SaleItemView> saleItemViewItems = new ArrayList<>();
    ObservableList<SaleItemView> saleItemViewObservableList = FXCollections.observableList(saleItemViewItems);

    /// Sell Ends here

    // Products
    @FXML
    TableView<Product> productsTable;
    @FXML
    TableColumn<Product, Integer> prdID;
    @FXML
    TableColumn<Product, String> prdName;
    @FXML
    TableColumn<Product, Double> prdPrice;
    @FXML
    TableColumn<Product, String> prdCategory;
    ObservableList<Product> productObservableList = FXCollections.observableList(products);
    //Products End here

    // Customers
    @FXML
    TableView<Customer> customersTable;
    @FXML
    TableColumn<Customer, Integer> custID;
    @FXML
    TableColumn<Customer, String> custFName;
    @FXML
    TableColumn<Customer, String> custLName;
    @FXML
    TableColumn<Customer, String> custEmail;
    @FXML
    TableColumn<Customer, String> custPhNo;
    @FXML
    TableColumn<Customer, Integer> custAge;
    @FXML
    TableColumn<Customer, String> custSex;
    @FXML
    TableColumn<Customer, String> custCity;
    @FXML
    TableColumn<Customer, String> custStreet;
    @FXML
    TableColumn<Customer, String> custHouseNo;
    ObservableList<Customer> customerObservableList = FXCollections.observableList(customers);
    // Customers end here

    //Employee
    @FXML
    TableView<Employee> employeesTable;
    @FXML
    TableColumn<Employee, Integer> empID;
    @FXML
    TableColumn<Employee, String> empFName;
    @FXML
    TableColumn<Employee, String> empLName;
    @FXML
    TableColumn<Employee, String> empEmail;
    @FXML
    TableColumn<Employee, String> empPhNo;
    @FXML
    TableColumn<Employee, Integer> empAge;
    @FXML
    TableColumn<Employee, String> empSex;
    @FXML
    TableColumn<Employee, String> empCity;
    @FXML
    TableColumn<Employee, String> empStreet;
    @FXML
    TableColumn<Employee, String> empHouseNo;
    @FXML
    TableColumn<Employee, Integer> empBranchID;
    ObservableList<Employee> employeeObservableList = FXCollections.observableList(employees);
    //Employee Ends Here

    //Store
    @FXML
    TableView<Store> storesTable;
    @FXML
    TableColumn<Store, Integer> strID;
    @FXML
    TableColumn<Store, String> strName;
    @FXML
    TableColumn<Store, String> strCity;
    @FXML
    TableColumn<Store, String> strStreet;
    @FXML
    TableColumn<Store, String> strHouseNo;
    ObservableList<Store> storeObservableList = FXCollections.observableList(stores);
    //Store Ends Here

    //Stock
    @FXML
    TableView<Stock> stocksTable;
    @FXML
    TableColumn<Stock, String> stockProduct;
    @FXML
    TableColumn<Stock, String> stockStore;
    @FXML
    TableColumn<Stock, Integer> stockQuantity;
    ObservableList<Stock> stockObservableList = FXCollections.observableList(stocks);
    //Stock Ends Here

    //Sale
    @FXML
    TableView<Sale> salesTable;
    @FXML
    TableColumn<Sale, Integer> salesID;
    @FXML
    TableColumn<Sale, Date> salesDate;
    @FXML
    TableColumn<Sale, Integer> salesEmpID;
    @FXML
    TableColumn<Sale, Integer> salesStrID;
    @FXML
    TableColumn<Sale, Integer> salesCustID;
    ObservableList<Sale> saleObservableList = FXCollections.observableList(sales);
    //Sale Ends here

    public void setConnection(Connection connection) {
        this.connection = connection;
        updateProducts();
        updateStores();
        updateCustomers();
        updateEmployees();
        updateStocks();
        updateSales();
        setUpTables();
        updateTables();
    }

    @FXML
    void refreshDatabase() {
        updateProducts();
        updateStores();
        updateCustomers();
        updateEmployees();
        updateStocks();
        updateSales();
        updateTables();
    }

    @FXML
    void sellAddProduct() {
        int i = sellProductCombo.getSelectionModel().selectedIndexProperty().get();
        Product p = products.get(i);
        for (Iterator<SaleItemView> iterator = saleItemViewItems.iterator(); iterator.hasNext(); ) {
            var value = iterator.next();
            if (value.getProduct().getId() == p.getId()) {
                iterator.remove();
            }
        }
        SaleItemView item = new SaleItemView(Integer.valueOf(sellQuantityField.getText()), p);
        saleItemViewItems.add(item);
        sellProdCol.setCellValueFactory(new PropertyValueFactory<SaleItemView, String>("productName"));
        sellQuantityCol.setCellValueFactory(new PropertyValueFactory<SaleItemView, Integer>("quantity"));
        sellTotalCol.setCellValueFactory(new PropertyValueFactory<SaleItemView, Double>("total"));
        sellPriceCol.setCellValueFactory(new PropertyValueFactory<SaleItemView, Double>("price"));
        sellTable.setItems(saleItemViewObservableList);
        double totalSum = 0.;
        for (var it : saleItemViewObservableList) {
            totalSum += it.getTotal();
        }
        sellTotal.setText(String.valueOf(totalSum));
        sellTable.refresh();
    }

    void setUpTables() {
        // products
        prdID.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        prdCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
        prdName.setCellValueFactory(new PropertyValueFactory<Product, String>("product_name"));
        prdPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("unit_price"));
        productsTable.setItems(productObservableList);
        //  Customer
        custID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        custFName.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        custLName.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        custLName.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        custPhNo.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone_number"));
        custAge.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("age"));
        custSex.setCellValueFactory(new PropertyValueFactory<Customer, String>("sex"));
        custCity.setCellValueFactory(new PropertyValueFactory<Customer, String>("city"));
        custStreet.setCellValueFactory(new PropertyValueFactory<Customer, String>("street"));
        custHouseNo.setCellValueFactory(new PropertyValueFactory<Customer, String>("house_number"));
        customersTable.setItems(customerObservableList);
        //Employee
        empID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        empFName.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        empLName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        empLName.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        empPhNo.setCellValueFactory(new PropertyValueFactory<Employee, String>("phone_number"));
        empAge.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("age"));
        empSex.setCellValueFactory(new PropertyValueFactory<Employee, String>("sex"));
        empCity.setCellValueFactory(new PropertyValueFactory<Employee, String>("city"));
        empStreet.setCellValueFactory(new PropertyValueFactory<Employee, String>("street"));
        empHouseNo.setCellValueFactory(new PropertyValueFactory<Employee, String>("house_number"));
        empBranchID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("store_id"));
        employeesTable.setItems(employeeObservableList);
        // Store
        strID.setCellValueFactory(new PropertyValueFactory<Store, Integer>("id"));
        strName.setCellValueFactory(new PropertyValueFactory<Store, String>("name"));
        strCity.setCellValueFactory(new PropertyValueFactory<Store, String>("city"));
        strStreet.setCellValueFactory(new PropertyValueFactory<Store, String>("street"));
        strHouseNo.setCellValueFactory(new PropertyValueFactory<Store, String>("house_number"));
        storesTable.setItems(storeObservableList);
        //Stocks
        stockProduct.setCellValueFactory(new PropertyValueFactory<Stock, String>("product_name"));
        stockStore.setCellValueFactory(new PropertyValueFactory<Stock, String>("store_name"));
        stockQuantity.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("quantity"));
        stocksTable.setItems(stockObservableList);
        // Sales
        salesID.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("id"));
        salesDate.setCellValueFactory(new PropertyValueFactory<Sale, Date>("date"));
        salesEmpID.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("employee_id"));
        salesCustID.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("customer_id"));
        salesStrID.setCellValueFactory(new PropertyValueFactory<Sale, Integer>("store_id"));
        salesTable.setItems(saleObservableList);

    }

    void updateTables() {
        sellTable.refresh();
        productsTable.refresh();
        customersTable.refresh();
        storesTable.refresh();
        employeesTable.refresh();
        stocksTable.refresh();
        salesTable.refresh();
    }

    @FXML
    void sell() {
        try {
            int storeId = stores.get(sellStoreCombo.getSelectionModel().getSelectedIndex()).getId();
            int employeeId = Integer.valueOf(sellEmployeeID.getText());
            for (var emp : employees) {
                if (emp.getId() == employeeId)
                    storeId = emp.getStore_id();
            }
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Sale(date_of_sale,employee_id,customer_id,store_id) VALUES(?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            statement.setTimestamp(1, date);
            statement.setInt(2, employeeId);
            statement.setInt(3, Integer.valueOf(sellCustomerID.getText()));
            statement.setInt(4, storeId);
            statement.execute();
            statement = statement = connection.prepareStatement("SELECT MAX(sale_id) AS sale_id FROM Sale", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = statement.executeQuery();
            System.out.println(rs.toString());
            int saleId = 0;
            if (rs.next()) {
                saleId = rs.getInt("sale_id");
            }
            statement.close();
            for (var item :
                    saleItemViewItems) {
                PreparedStatement statement1 = connection.prepareStatement("INSERT INTO SaleItem(item_sale_id,item_product_id,quantity) VALUES(?,?,?)");
                statement1.setInt(1, saleId);
                statement1.setInt(2, item.getProduct().getId());
                statement1.setInt(3, Integer.valueOf(sellQuantityField.getText()));
                statement1.execute();
                statement1.close();
            }
            var statement3 = connection.prepareStatement("INSERT INTO Payment(customer_id,sale_id,amount) VALUES(?,?,?)");
            statement3.setInt(1, Integer.valueOf(sellCustomerID.getText()));
            statement3.setInt(2, saleId);
            statement3.setDouble(3, Double.valueOf(sellTotal.getText()));
            statement3.execute();
            statement3.close();
            saleItemViewItems.clear();
            sellTable.refresh();
            refreshDatabase();
        } catch (SQLException exception) {
            System.out.println(exception.toString());
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
                customers.add(customer);
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
                var sale = new Sale(resultSet.getInt("sale_id"), ((java.util.Date) resultSet.getDate("date_of_sale")), resultSet.getInt("store_id"),
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
                var employee = new Employee(rs.getInt("employee_id"), rs.getString("first_name"), rs.getString("last_name"),
                        rs.getString("email"), rs.getString("phone_number"), rs.getInt("age"), rs.getString("sex"),
                        rs.getString("city"), rs.getString("street"), rs.getInt("house_number"), rs.getInt("branch_id"));
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
