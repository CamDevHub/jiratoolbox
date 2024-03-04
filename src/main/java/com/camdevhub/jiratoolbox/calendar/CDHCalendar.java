package com.camdevhub.jiratoolbox.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CDHCalendar extends VBox {

	private TilePane calendarTilePane;
	private HBox headerPane;
	
	private List<LocalDate> selectedDates;

	private int dayCellWidth;
	private int dayCellHeight;

	public CDHCalendar() {
		super();

		this.dayCellHeight = 40;
		this.dayCellWidth = 80;
		
		this.selectedDates = new LinkedList<>();

		this.headerPane = new HBox();

		this.calendarTilePane = new TilePane();
		this.calendarTilePane.setPrefColumns(DayOfWeek.values().length);

		this.refreshCalendar(LocalDate.now());

		this.getChildren().add(headerPane);
		this.getChildren().add(calendarTilePane);
	}

	private void refreshCalendar(LocalDate date) {
		int month;
		int year;
		month = date.getMonthValue();
		year = date.getYear();

		this.calendarTilePane.getChildren().addAll(this.generateHeaderTiles());
		for (int i = 1; i <= date.lengthOfMonth(); ++i) {
			this.calendarTilePane.getChildren().add(generateDayTile(LocalDate.of(year, month, i)));
		}

	}

	private Pane generateDayTile(LocalDate date) {
		StackPane dayCell = new StackPane();
		dayCell.setPrefSize(dayCellWidth, dayCellHeight);
		dayCell.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		Label dayLabel = new Label(date.format(DateTimeFormatter.ofPattern("d")));
		dayLabel.setFont(new Font(24));

		dayCell.getChildren().add(dayLabel);
		dayCell.setStyle("-fx-background-color: white");

		dayCell.setUserData(date);
		dayCell.setOnMouseClicked(e -> this.selectedDates.add((LocalDate)dayCell.getUserData()));

		return dayCell;
	}

	private List<Pane> generateHeaderTiles() {
		Locale locale = Locale.getDefault();

		LinkedList<Pane> days = new LinkedList<>();
		for (DayOfWeek day : DayOfWeek.values()) {
			StackPane dayCell = new StackPane();
			dayCell.setPrefSize(dayCellWidth, dayCellHeight);
			dayCell.setBorder(new Border(
					new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

			Label dayLabel = new Label(day.getDisplayName(TextStyle.SHORT, locale));
			dayLabel.setFont(new Font(18));
			dayLabel.setTextFill(Color.WHITE);

			dayCell.getChildren().add(dayLabel);
			dayCell.setStyle("-fx-background-color: black");

			days.add(dayCell);
		}

		return days;
	}
	
	public List<LocalDate> getSelectedDates() {
		return selectedDates;
	}

	public int getDayCellWidth() {
		return dayCellWidth;
	}

	public void setDayCellWidth(int dayCellWidth) {
		this.dayCellWidth = dayCellWidth;
	}

	public int getDayCellHeight() {
		return dayCellHeight;
	}

	public void setDayCellHeight(int dayCellHeight) {
		this.dayCellHeight = dayCellHeight;
	}
}
