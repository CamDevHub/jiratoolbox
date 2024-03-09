package com.camdevhub.jiratoolbox;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiFunction;

import com.camdevhub.jiratoolbox.controller.HolidaysController;
import com.camdevhub.jiratoolbox.controller.LoginController;
import com.camdevhub.jiratoolbox.controller.MenuController;
import com.camdevhub.jiratoolbox.controller.PrimaryController;
import com.camdevhub.jiratoolbox.jira.CDHJiraClient;
import com.camdevhub.jiratoolbox.utils.JFXUtils;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
	
	private static final String PRIMARY_FXML_NAME = "primary";

	@Override
	public void start(Stage stage) throws IOException {
		AppPreferences prefs = new AppPreferences();
		
		FXMLLoader fxmlPrimaryLoader = JFXUtils.getFXMLLoader(PRIMARY_FXML_NAME);

		Map<MenuEnum, Parent> parentViews = this.loadViews(prefs);
		PrimaryController primaryController = new PrimaryController(parentViews);
		fxmlPrimaryLoader.setController(primaryController);

		Scene scene = new Scene(fxmlPrimaryLoader.load(), 600, 500);
		stage.setScene(scene);
		stage.show();

	}

	private EnumMap<MenuEnum, Parent> loadViews(AppPreferences prefs) throws IOException {
		EnumMap<MenuEnum, Parent> parents = new EnumMap<>(MenuEnum.class);
		CDHJiraClient jiraClient = new CDHJiraClient();

		parents.put(MenuEnum.LOGIN, addView(MenuEnum.LOGIN, jiraClient, prefs, LoginController::new));
		parents.put(MenuEnum.HOLIDAYS, addView(MenuEnum.HOLIDAYS, jiraClient, prefs, HolidaysController::new));

		return parents;
	}
	
	private Parent addView(MenuEnum menuEnum, CDHJiraClient jiraClient, AppPreferences prefs, BiFunction<CDHJiraClient, AppPreferences, MenuController> controllerSupplier) throws IOException {
		FXMLLoader fxmlLoader = JFXUtils.getFXMLLoader(menuEnum.toString().toLowerCase());
		MenuController menuController = controllerSupplier.apply(jiraClient, prefs);
		fxmlLoader.setController(menuController);
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch();
	}

}