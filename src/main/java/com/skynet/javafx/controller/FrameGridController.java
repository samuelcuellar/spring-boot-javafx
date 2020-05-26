package com.skynet.javafx.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import com.skynet.javafx.jfxsupport.AbstractFxmlView;
import com.skynet.javafx.jfxsupport.FXMLController;
import com.skynet.javafx.jfxsupport.PrototypeController;
import com.skynet.javafx.model.SimpleEntity;
import com.skynet.javafx.service.FrameService;
import com.skynet.javafx.views.def.FrameGridDef;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@FXMLController
@Scope("prototype")
public class FrameGridController implements PrototypeController {

	private static final Logger logger = LoggerFactory.getLogger(FrameGridController.class);	
	
	@Autowired
	private ApplicationContext context;
	@FXML
	private Button addButton;
	@FXML
	private Button editButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button printButton;
	@FXML
	private TableView<SimpleEntity> frameGrid;
	private FrameService frameService;
	private FrameGridDef gridDef;
	private Scene scene;
	
	@FXML
	private void initialize() {
		editButton.setDisable(true);
		deleteButton.setDisable(true);
		printButton.setDisable(true);
		addButton.setOnAction((event) -> { addButtonHandleAction(); });
		editButton.setOnAction((event) -> { editButtonHandleAction(); });
		deleteButton.setOnAction((event) -> { deleteButtonHandleAction(); });
		printButton.setOnAction((event) -> { printButtonHandleAction(); });
	}
	
	private void addButtonHandleAction() {
		AbstractFxmlView fxmlView = showDialog();
		CrudController controller = (CrudController) fxmlView.getFxmlLoader().getController();
		controller.add();
	}

	private void editButtonHandleAction() {
		AbstractFxmlView fxmlView = showDialog();
		CrudController controller = (CrudController) fxmlView.getFxmlLoader().getController();
		SimpleEntity entity = frameGrid.getSelectionModel().getSelectedItem();
		controller.render(entity);
	}
	
	private void deleteButtonHandleAction() {
		SimpleEntity entity = frameGrid.getSelectionModel().getSelectedItem();
		frameService.delete(entity.getId());
		loadData();
	}
	
	private void printButtonHandleAction() {
		logger.debug("clicked printButton");
	}
	
	public void initializeGrid(FrameService frameService, FrameGridDef gridDef) {
		this.frameService = frameService;
		this.gridDef = gridDef;
		setupGrid();
		loadData();
	}
	
	private void setupGrid() {
		List<String> columnNames = gridDef.getColumnNames();
		List<String> columnDataNames = gridDef.getColumnDataName();
		List<Integer> columnSizes = gridDef.getColumnSizes();		
		for (int i = 0; i < gridDef.getColumnNames().size(); i++) {
	        TableColumn<SimpleEntity, String> column = new TableColumn<>(columnNames.get(i));
	        column.setCellValueFactory(
	            new PropertyValueFactory<SimpleEntity, String>(columnDataNames.get(i))
	        );
			column.setMinWidth(columnSizes.get(i));
	        frameGrid.getColumns().add(column);
		}
		frameGrid.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				editButton.setDisable(false);
				deleteButton.setDisable(false);
			} else {
				editButton.setDisable(true);
				deleteButton.setDisable(true);
			}
		});
		frameGrid.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) { editButtonHandleAction(); }
		});		
		frameGrid.setOnMousePressed((event) -> {
			if (event.isPrimaryButtonDown() && event.getClickCount() == 2) { editButtonHandleAction(); }
		});
	}

	private void loadData() {
	    ObservableList<SimpleEntity> data = FXCollections.observableArrayList(frameService.getData());
	    if (data != null) {
			logger.debug("loadData, data size: {}", data.size());
		    frameGrid.setItems(data);
	    }
	}

	private AbstractFxmlView showDialog() {
		AbstractFxmlView fxmlView = (AbstractFxmlView) context.getBean(gridDef.getCreateView());
		Stage stage = new Stage();
		scene = new Scene(fxmlView.getView());
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.setTitle(gridDef.getTitlePopups());        
        stage.setOnHidden((event) -> { 
        	stage.close(); 
    		SimpleEntity oldSelected = frameGrid.getSelectionModel().getSelectedItem();
        	loadData();
        	if (oldSelected != null) {
        		frameGrid.getSelectionModel().select(oldSelected);
        	} else {
        		frameGrid.getSelectionModel().select(0);
        	}
        });
        stage.show();
		return fxmlView;
	}
}
