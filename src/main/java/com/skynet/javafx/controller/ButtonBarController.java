package com.skynet.javafx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.skynet.javafx.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Window;

@FXMLController
public class ButtonBarController {

	private static final Logger logger = LoggerFactory.getLogger(ButtonBarController.class);	
	
	private CrudController target;
	
	@FXML
	protected Button acceptButton;

	@FXML
	protected Button cancelButton;

	@FXML
	private void initialize() {
		logger.debug("initialize ButtonBarController");
		
		acceptButton.setOnAction((event) -> { acceptButtonHandleAction(); });
		acceptButton.defaultButtonProperty().bind(acceptButton.focusedProperty());

		cancelButton.setOnAction((event) -> { cancelButtonHandleAction(); });
		cancelButton.defaultButtonProperty().bind(cancelButton.focusedProperty());
	}
	
	private void acceptButtonHandleAction() {
		Window stage = acceptButton.getScene().getWindow();
		target.save();
		stage.hide();
	}
	
	private void cancelButtonHandleAction() {
		Window stage = cancelButton.getScene().getWindow();
		stage.hide();
	}
	
	public void setTarget(CrudController target) {
		this.target = target;
	}
	
}
