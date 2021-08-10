package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("View.fxml"));
        loader.setControllerFactory(Controller -> new Controller("http://rss.cnn.com/rss/edition.rss"));
        Parent root = loader.load();
        Image icon = new Image(getClass().getResourceAsStream("Icon.png"));
        Scene scene  = new Scene(root);

        primaryStage.setTitle("RSS News Reader");
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(windowEvent ->  {
                Platform.exit();
                System.exit(0);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
