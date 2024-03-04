package com.camdevhub.jiratoolbox.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JFXUtils {
	private static final String RESOURCE_PATH = "/com/camdevhub/jiratoolbox/";
	
	private JFXUtils() {
		throw new AssertionError();
	}
	
	public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(JFXUtils.class.getResource(RESOURCE_PATH + fxml + ".fxml"));
        return fxmlLoader.load();
    }
	
	public static void showErrorPopup(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
