package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.w3c.dom.NodeList;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private String urlString;
    public Controller(String url) {this.urlString = url;};

    private Model model;
    private List<String> titles;
    private NodeList newsNodeList;

    @FXML
    private ListView<String> listView;
    @FXML
    private TextArea linkText;
    @FXML
    private TextArea descriptionText;
    @FXML
    private ImageView imageView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = new Model();
        newsNodeList = model.getNewsItems(model.getInputStream(this.urlString));
        titles = model.getTitles(newsNodeList);
        listView.getItems().addAll(titles);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String currentTitle = listView.getSelectionModel().getSelectedItems().toString();
                currentTitle = currentTitle.replaceAll("\\[(.*?)\\]", "$1");

                String imageURL = model.getImage(currentTitle, newsNodeList);
                String link = model.getLink(currentTitle, newsNodeList);
                String description = model.getDescription(currentTitle, newsNodeList);
                if (imageURL.length() > 1) imageView.setImage(new Image(imageURL)); else imageView.setImage(new Image("https://www.ncenet.com/wp-content/uploads/2020/04/no-image-png-2.png"));
                if (link.length() > 1) linkText.setText(link); else linkText.setText("No link available.");
                if (description.length() > 1) descriptionText.setText(description); else descriptionText.setText("No description available.");
            }
        });

    }

}
