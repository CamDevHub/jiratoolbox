package com.camdevhub.jiratoolbox.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.camdevhub.jiratoolbox.MenuEnum;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class PrimaryController {
	private static final Logger logger = LoggerFactory.getLogger(PrimaryController.class);

	private Map<MenuEnum, Parent> parents;

	@FXML
	private BorderPane rootPane;

	@FXML
	private HBox menuPane;
	
	public PrimaryController(Map<MenuEnum, Parent> parents) {
		this.parents = parents;
	}

	@FXML
	public void initialize() throws IOException {
		this.generateMenuButtons();
		logger.info("Menu initialized");
		this.loadViewFromMenuEnum(MenuEnum.LOGIN);
		logger.info("PrimaryController initialized");
	}
	
	private void generateMenuButtons() {
		this.parents.forEach((menuItem, parent) -> {
			Button button = new Button(menuItem.toString());
			button.setOnAction(e -> {
				this.loadViewFromMenuEnum(menuItem);
			});
			this.menuPane.getChildren().add(button);
		});
	}
	
	private void loadViewFromMenuEnum(MenuEnum menu) {
		this.rootPane.setCenter(this.parents.get(menu));
		logger.info("Display {} view", menu.toString());
	}
	
}
