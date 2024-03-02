module com.camdevhub.jiratoolbox {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.camdevhub.jiratoolbox to javafx.fxml;
    exports com.camdevhub.jiratoolbox;
}
