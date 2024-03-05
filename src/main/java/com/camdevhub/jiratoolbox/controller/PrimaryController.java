package com.camdevhub.jiratoolbox.controller;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camdevhub.jiratoolbox.utils.JFXUtils;
import com.camdevhub.jiratoolbox.utils.ToolboxMenuItem;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PrimaryController {
	private static final Logger logger = LoggerFactory.getLogger(PrimaryController.class);

	private Map<ToolboxMenuItem, Parent> menuItemParent = new EnumMap<>(ToolboxMenuItem.class);

	@FXML
	private VBox rootPane;

	@FXML
	private HBox menuPane;

	@FXML
	public void initialize() throws IOException {
		for (ToolboxMenuItem item : ToolboxMenuItem.values()) {
			String menuName = item.toString();
			menuItemParent.put(item, JFXUtils.loadFXML(menuName.toLowerCase()));

			Button button = new Button(menuName);
			button.setFont(new Font(18));
			button.setOnAction(e -> {
				loadContentPaneByToolboxMenuItem(item);
			});
			HBox.setMargin(button, new Insets(0, 10, 0, 10));
			this.menuPane.getChildren().add(button);
		}
		logger.info("PrimaryController initialized");
	}

	private void loadContentPaneByToolboxMenuItem(ToolboxMenuItem menuItem) {
		if (this.rootPane.getChildren().size() > 1) {
			this.rootPane.getChildren().removeLast();
		}
		Parent parent = menuItemParent.get(menuItem);
		this.rootPane.getChildren().add(parent);

	}
}
