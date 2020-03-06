package com.skynet.javafx;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import com.skynet.javafx.controller.MainController;
import com.skynet.javafx.utils.jfxsupport.AbstractFxmlView;
import com.skynet.javafx.utils.jfxsupport.GUIState;
import com.skynet.javafx.utils.jfxsupport.PropertyReaderHelper;
import com.skynet.javafx.views.MainView;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * JavaFxApplicationSupport, an instance of this class is created by Application.main(...)
 * 
 * @author samuel.cuellar
 */

@SpringBootApplication
public class JavaFxApplicationSupport extends javafx.application.Application {

	private static Logger LOGGER = LoggerFactory.getLogger(JavaFxApplicationSupport.class);
	private static ConfigurableApplicationContext applicationContext;
	private static List<Image> icons = new ArrayList<>();

	@Override
	public void init() throws Exception {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(JavaFxApplicationSupport.class);
		builder.application().setWebApplicationType(WebApplicationType.NONE);
		applicationContext = builder.run(getParameters().getRaw().toArray(new String[0]));
		final List<String> fsImages = PropertyReaderHelper.get(applicationContext.getEnvironment(), "javafx.appicons");
		if (!fsImages.isEmpty()) {
			fsImages.forEach((s) -> icons.add(new Image(getClass().getResource(s).toExternalForm())));
		} else {
			icons.add(new Image(getClass().getResource("/images/gear_16x16.png").toExternalForm()));
			icons.add(new Image(getClass().getResource("/images/gear_24x24.png").toExternalForm()));
			icons.add(new Image(getClass().getResource("/images/gear_36x36.png").toExternalForm()));
			icons.add(new Image(getClass().getResource("/images/gear_42x42.png").toExternalForm()));
			icons.add(new Image(getClass().getResource("/images/gear_64x64.png").toExternalForm()));
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GUIState.setStage(primaryStage);
		GUIState.setHostServices(this.getHostServices());
		showInitialView();
	}

	@Override
	public void stop() throws Exception {
		applicationContext.close();
	}

	private void showInitialView() {
		final String stageStyle = applicationContext.getEnvironment().getProperty("javafx.stage.style");
		if (stageStyle != null) {
			GUIState.getStage().initStyle(StageStyle.valueOf(stageStyle.toUpperCase()));
		} else {
			GUIState.getStage().initStyle(StageStyle.DECORATED);
		}
		showView(MainView.class);
	}

	public static void showView(final Class<? extends AbstractFxmlView> newView) {
		try {
			final AbstractFxmlView view = applicationContext.getBean(newView);

			if (GUIState.getScene() == null) {
				GUIState.setScene(new Scene(view.getView()));
			} else {
				GUIState.getScene().setRoot(view.getView());
			}
			GUIState.getStage().setScene(GUIState.getScene());
			applyEnvPropsToView();
			GUIState.getStage().getIcons().addAll(icons);
			GUIState.getStage().addEventHandler(WindowEvent.WINDOW_SHOWN, e -> {
				if (view.getFxmlLoader().getController() instanceof MainController) {
					MainController mainController = (MainController) view.getFxmlLoader().getController();
					mainController.onWindowShownEvent();
				}
				LOGGER.debug("Stage view shown: {} ", view.getClass());
			});
			GUIState.getStage().show();
		} catch (Throwable t) {
			LOGGER.error("Failed to load application: ", t);
			showErrorAlert(t);
		}
	}
	
	private static void applyEnvPropsToView() {
		PropertyReaderHelper.setIfPresent(applicationContext.getEnvironment(), "javafx.title", String.class, GUIState.getStage()::setTitle);
		PropertyReaderHelper.setIfPresent(applicationContext.getEnvironment(), "javafx.stage.width", Double.class, GUIState.getStage()::setWidth);
		PropertyReaderHelper.setIfPresent(applicationContext.getEnvironment(), "javafx.stage.height", Double.class, GUIState.getStage()::setHeight);
		PropertyReaderHelper.setIfPresent(applicationContext.getEnvironment(), "javafx.stage.resizable", Boolean.class, GUIState.getStage()::setResizable);
	}	

	private static void showErrorAlert(Throwable throwable) {
		Alert alert = new Alert(AlertType.ERROR, "Oops! An unrecoverable error occurred.\n"  + "Please contact your software vendor.\n\n" + "The application will stop now.");
		alert.showAndWait().ifPresent(response -> Platform.exit());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
