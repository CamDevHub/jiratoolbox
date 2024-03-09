package com.camdevhub.jiratoolbox.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.camdevhub.jiratoolbox.control.MonthField;
import com.camdevhub.jiratoolbox.control.YearField;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	private HBox controlPane;

	YearField yearField;
	MonthField monthField;

	private Set<LocalDate> selectedDates;

	private int dayCellWidth;
	private int dayCellHeight;

	private Color colorSelection;
	private Color colorDisabled;

	public CDHCalendar() {
		super();

		this.dayCellHeight = 40;
		this.dayCellWidth = 40;
		this.colorSelection = Color.rgb(0, 0, 0, 0.5);
		this.colorDisabled = Color.rgb(0, 0, 0, 0.7);

		this.selectedDates = new HashSet<>();

		this.controlPane = new HBox();
		yearField = new YearField();
		monthField = new MonthField();
		this.setupControlPane();

		this.calendarTilePane = new TilePane();
		this.calendarTilePane.setPrefColumns(DayOfWeek.values().length);

		this.refreshCalendar(LocalDate.now().withDayOfMonth(1));

		this.getChildren().add(controlPane);
		this.getChildren().add(calendarTilePane);

		this.calendarTilePane.setAlignment(Pos.CENTER);
		this.setAlignment(Pos.CENTER);
	}

	private void setupControlPane() {
		LocalDate date = LocalDate.now();

		yearField.setPrefWidth(100);
		yearField.setText(Integer.toString(date.getYear()));

		monthField.setPrefWidth(100);
		monthField.setText(Integer.toString(date.getMonthValue()));

		Button increaseYearButton = new Button("+");
		Button decreaseYearButton = new Button("-");
		increaseYearButton.setOnAction(event -> {
			increaseNumber(yearField);
			refreshCalendar(this.getDateFromTextFields());
		});
		decreaseYearButton.setOnAction(event -> {
			decreaseNumber(yearField);
			refreshCalendar(this.getDateFromTextFields());
		});

		Button increaseMonthButton = new Button("+");
		Button decreaseMonthButton = new Button("-");
		increaseMonthButton.setOnAction(event -> {
			increaseNumber(monthField);
			refreshCalendar(this.getDateFromTextFields());
		});
		decreaseMonthButton.setOnAction(event -> {
			decreaseNumber(monthField);
			refreshCalendar(this.getDateFromTextFields());
		});

		Button clearButton = new Button("Clear");
		clearButton.setOnAction(event -> {
			selectedDates.clear();
			refreshCalendar(this.getDateFromTextFields());
		});

		setPadding(new Insets(10));
		controlPane.getChildren().addAll(decreaseYearButton, yearField, increaseYearButton, decreaseMonthButton,
				monthField, increaseMonthButton, clearButton);

		controlPane.setAlignment(Pos.CENTER);
	}

	private LocalDate getDateFromTextFields() {
		int year = Integer.parseInt(yearField.getText());
		int month = Integer.parseInt(monthField.getText());

		return LocalDate.of(year, month, 1);
	}

	private void increaseNumber(TextField numberField) {
		int currentValue = Integer.parseInt(numberField.getText());
		numberField.setText(String.valueOf(currentValue + 1));
	}

	private void decreaseNumber(TextField numberField) {
		int currentValue = Integer.parseInt(numberField.getText());
		numberField.setText(String.valueOf(currentValue - 1));
	}

	private void refreshCalendar(LocalDate date) {
		this.calendarTilePane.getChildren().clear();
		this.calendarTilePane.getChildren().addAll(generateHeaderTiles());
		while (!DayOfWeek.MONDAY.equals(date.getDayOfWeek())) {
			date = date.minusDays(1);
		}
		for (int i = 1; i <= 35; ++i) {
			CDHCalendarDay cellDay = generateDayTile(date);
			if (selectedDates.contains(date)) {
				cellDay.addOverlay(colorSelection);
			}
			calendarTilePane.getChildren().add(cellDay);
			date = date.plusDays(1);
		}
	}

	private CDHCalendarDay generateDayTile(LocalDate date) {
		CDHCalendarDay dayCell = new CDHCalendarDay(date, dayCellWidth, dayCellHeight);

		if (isWorkingDay(date)) {
			dayCell.setOnMouseClicked(e -> updateSelection(dayCell, dayCell.getDate()));
		} else {
			dayCell.addOverlay(colorDisabled);
		}

		return dayCell;
	}

	private void updateSelection(CDHCalendarDay dayCell, LocalDate date) {
		if (selectedDates.contains(date)) {
			dayCell.removeOverlay();
			selectedDates.remove(date);
		} else {
			dayCell.addOverlay(colorSelection);
			selectedDates.add(date);
		}
	}

	private boolean isWorkingDay(LocalDate date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
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

	public Set<LocalDate> getSelectedDates() {
		return selectedDates;
	}

	public void setColorSelection(Color colorSelection) {
		this.colorSelection = colorSelection;
		this.refreshCalendar(getDateFromTextFields());
	}

	public void setColorDisabled(Color colorDisabled) {
		this.colorDisabled = colorDisabled;
		this.refreshCalendar(getDateFromTextFields());
	}

	public int getDayCellWidth() {
		return dayCellWidth;
	}

	public void setDayCellWidth(int dayCellWidth) {
		this.dayCellWidth = dayCellWidth;
		this.refreshCalendar(getDateFromTextFields());
	}

	public int getDayCellHeight() {
		return dayCellHeight;
	}

	public void setDayCellHeight(int dayCellHeight) {
		this.dayCellHeight = dayCellHeight;
		this.refreshCalendar(getDateFromTextFields());
	}

	
}
