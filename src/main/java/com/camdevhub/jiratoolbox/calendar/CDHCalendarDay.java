package com.camdevhub.jiratoolbox.calendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class CDHCalendarDay extends StackPane {
	private final LocalDate date;
    private final int width;
    private final int height;
    
    public CDHCalendarDay(LocalDate date, int dayCellWidth, int dayCellHeight) {
        super();
        this.date = date;
        this.width = dayCellWidth;
        this.height = dayCellHeight;
        initDayCell();
    }
    
    private void initDayCell() {
        setPrefSize(width, height);
        setBorder(Border.EMPTY);

        Label dayLabel = new Label(date.format(DateTimeFormatter.ofPattern("d")));
        dayLabel.setFont(new Font(24));
        getChildren().add(dayLabel);

        setStyle("-fx-background-color: white");

    }


    public void addOverlay(Color color) {
        Rectangle overlay = new Rectangle(width, height);
        overlay.setFill(color);
        getChildren().add(overlay);
    }

    public void removeOverlay() {
        if (getChildren().size() > 1) {
            getChildren().remove(1);
        }
    }

	public LocalDate getDate() {
		return date;
	}
}
