package com.skynet.javafx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.skynet.javafx.jfxsupport.FXMLController;
import com.skynet.javafx.model.Customer;
import com.skynet.javafx.model.SimpleEntity;
import com.skynet.javafx.service.CustomerService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

@FXMLController
public class CustomerController implements CrudController {

  private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  @FXML
  private ButtonBarController buttonbarController;

  @FXML
  private TextField textFirstname;
  @FXML
  private TextField textLastname;
  @FXML
  private TextField textAddress;
  @FXML
  private TextField textEmail;

  @Autowired
  private CustomerService customerService;

  private Customer customer;

  @FXML
  private void initialize() {
    logger.debug("initialize CustomerController");

    buttonbarController.setTarget(this);
  }

  @Override
  public void add() {
    // TODO Auto-generated method stub
  }

  @Override
  public void render(SimpleEntity entity) {
    customer = (Customer) entity;
    textFirstname.setText(customer.getFirstname());
    textLastname.setText(customer.getLastname());
    textAddress.setText(customer.getAddress());
    textEmail.setText(customer.getEmail());
  }

  @Override
  public void save() {
    if (customer == null) {
      customer = new Customer();
    }
    customer.setFirstname(textFirstname.getText());
    customer.setLastname(textLastname.getText());
    customer.setAddress(textAddress.getText());
    customer.setEmail(textEmail.getText());
    customerService.save(customer);
  }
}
