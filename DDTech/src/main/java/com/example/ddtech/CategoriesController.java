package com.example.ddtech;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CategoriesController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane scrollAnchorPane;
    @FXML
    private MediaView mediaView;
    @FXML
    private MediaPlayer mediaPlayer;
    @FXML
    private Button categories;
    @FXML
    private AnchorPane categoriesMenu;
    @FXML
    private Button home;

    private Parent root;
    private Stage stage;
    private Scene scene;

    public int maxNumber = 0;


    public void initialize() {

        categories.setOnMouseEntered(event -> {
            categoriesMenu.setVisible(true);
        });
        categoriesMenu.setOnMouseExited(event -> {
            categoriesMenu.setVisible(false);
        });

    }

    public void loadArticles(int nNumbers, int minNumber, List<Articulo> articles){
        Tienda tienda = new Tienda();
        Set<Integer> numbers = generateRandomNumbers(nNumbers, minNumber, articles.size()-1);
        int numberOfArticlesToShow = 3;
        double x = 320;
        double y = 135;
        final double initialX = x;
        for(Integer number : numbers){
            System.out.println(number);
            if (numberOfArticlesToShow == 0){
                y += 500;
                numberOfArticlesToShow = 3;
                x = initialX;
            }
            tienda.reusableAlert(articles.get(number), scrollAnchorPane, x, y);
            numberOfArticlesToShow--;
            x+=500;
        }
    }
    public void categories(){
        if (!categoriesMenu.isVisible()){
            categoriesMenu.setVisible(true);
        }else {
            categoriesMenu.setVisible(false);
        }
    }
    public void home(ActionEvent event) throws IOException {
        Platform.runLater(() -> {
            Robot robot = new Robot();
            robot.mouseMove(960, 540);
        });

        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        root = loader.load();

        HelloController categoriesController = loader.getController();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public static Set<Integer> generateRandomNumbers(int nNumbers, int min, int max) {
        Random random = new Random();
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < nNumbers) {
            int numeroAleatorio = random.nextInt(max - min + 1) + min;
            numbers.add(numeroAleatorio);
        }
        return numbers;
    }
}