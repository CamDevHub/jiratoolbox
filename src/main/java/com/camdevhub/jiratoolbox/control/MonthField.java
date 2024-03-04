package com.camdevhub.jiratoolbox.control;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class MonthField extends TextField {
	public MonthField() {
        super();
        initialize();
    }

    private void initialize() {
        setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("^(?:1[0-2]|[1-9])$")) {
                return change;
            }
            return null;
        }));
    }

    public int getValue() {
        try {
            int month = Integer.parseInt(getText());
            if (month < 1 || month > 12) {
                throw new NumberFormatException("Month must be between 1 and 12");
            }
            return month;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setValue(int value) {
        setText(Integer.toString(value));
    }
}
