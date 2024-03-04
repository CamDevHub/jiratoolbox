package com.camdevhub.jiratoolbox.controller;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import com.camdevhub.jiratoolbox.utils.JFXUtils;
import com.camdevhub.jiratoolbox.utils.ToolboxMenuItem;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PrimaryController {
	private Map<ToolboxMenuItem, Parent> menuItemParent = new EnumMap<>(ToolboxMenuItem.class);

	@FXML
	private VBox rootPane;
	
	@FXML
	private HBox menuPane;
	
	@FXML
	private Pane contentPane;
	
	@FXML
	public void initialize() {
		menuPane.prefWidthProperty().bind(rootPane.widthProperty().multiply(0.10));
		contentPane.prefWidthProperty().bind(rootPane.widthProperty().multiply(0.90));
		
		try {
			menuItemParent.put(ToolboxMenuItem.HOLIDAYS, JFXUtils.loadFXML("holidays"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    @FXML
    private void displayHolidaysPane() throws IOException {
    	loadContentPaneByToolboxMenuItem(ToolboxMenuItem.HOLIDAYS);
    }
    
    private void loadContentPaneByToolboxMenuItem(ToolboxMenuItem menuItem) {
    	Parent parent = menuItemParent.get(menuItem);
        contentPane.getChildren().clear();
        contentPane.getChildren().add(parent);
    }
}
