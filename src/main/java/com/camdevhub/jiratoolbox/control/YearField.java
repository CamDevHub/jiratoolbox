package com.camdevhub.jiratoolbox.control;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class YearField extends TextField {
	public YearField() {
        super();
        initialize();
    }

    private void initialize() {
        setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^\\d{0,4}$")) {
                return change;
            }
            return null;
        }));
    }

    public int getValue() {
        try {
            int year = Integer.parseInt(getText());
            if (year < 0 || year > 9999) {
                throw new NumberFormatException("Year must be between 0 and 9999");
            }
            return year;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setValue(int value) {
        setText(Integer.toString(value));
    }
}
