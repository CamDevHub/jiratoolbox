package com.camdevhub.jiratoolbox;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import com.camdevhub.jiratoolbox.controller.HolidaysController;
import com.camdevhub.jiratoolbox.controller.LoginController;
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

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlPrimaryLoader = JFXUtils.getFXMLLoader("primary");

		Map<MenuEnum, Parent> parentViews = this.loadViews();
		PrimaryController primaryController = new PrimaryController(parentViews);
		fxmlPrimaryLoader.setController(primaryController);

		Scene scene = new Scene(fxmlPrimaryLoader.load(), 800, 600);
		stage.setScene(scene);
		stage.show();

	}

	private EnumMap<MenuEnum, Parent> loadViews() throws IOException {
		EnumMap<MenuEnum, Parent> parents = new EnumMap<>(MenuEnum.class);

		FXMLLoader fxmlLoginLoader = JFXUtils.getFXMLLoader("login");
		LoginController loginController = new LoginController(new CDHJiraClient());
		fxmlLoginLoader.setController(loginController);
		parents.put(MenuEnum.LOGIN, fxmlLoginLoader.load());

		FXMLLoader fxmlHolidaysLoader = JFXUtils.getFXMLLoader("holidays");
		HolidaysController holidaysController = new HolidaysController(new CDHJiraClient());
		fxmlHolidaysLoader.setController(holidaysController);
		parents.put(MenuEnum.HOLIDAYS, fxmlHolidaysLoader.load());

		return parents;
	}

	public static void main(String[] args) {
		launch();
	}

}