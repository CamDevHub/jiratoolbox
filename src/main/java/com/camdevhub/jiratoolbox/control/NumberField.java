package com.camdevhub.jiratoolbox.control;

import javafx.scene.control.TextField;

public class NumberField extends TextField {
	public NumberField() {
        super();
        initialize();
    }

    private void initialize() {
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public int getValue() {
        try {
            return Integer.parseInt(getText());
        } catch (NumberFormatException e) {
            return 0; 
        }
    }

    public void setValue(int value) {
        setText(Integer.toString(value));
    }
}
