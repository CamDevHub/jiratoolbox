package com.camdevhub.jiratoolbox.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class JFXUtils {
	private static final String RESOURCE_PATH = "/com/camdevhub/jiratoolbox/";
	
	private JFXUtils() {
		throw new AssertionError();
	}
	
	public static FXMLLoader getFXMLLoader(String fxml) {
        return new FXMLLoader(JFXUtils.class.getResource(RESOURCE_PATH + fxml + ".fxml"));
    }
	
	public static void showErrorPopup(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
	
	public static void showInformationPopup(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
