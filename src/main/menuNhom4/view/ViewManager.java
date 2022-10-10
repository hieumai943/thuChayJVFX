package view;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Instruction;
import model.MenuButton;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 650;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private static final int MENU_START_X = 150;
    private static final int MENU_START_Y = 200;
    private Instruction hdscreen;
    private Instruction highscreen;
    private Instruction playscreen;
    private List<MenuButton> menuButtons = new ArrayList<>();
    private GridPane gridPane1;
    private GridPane gridPane2;
    private AnimationTimer bgrTimer;

    private Instruction sceneToHide;
    private MediaPlayer mediaPlayer;

    public void music() throws Exception {

        String path = getClass().getResource("music.mp3").getPath();
        Media h = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public ViewManager() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        try {
      music();
        } catch (Exception e) {
        }
        try {
            createBackGround();

        } catch (Exception e) {
        }
        createbuttons();
        try {
            createSound();
        } catch (Exception e) {
        }
        try {
            creatImgBgr();
        } catch (Exception e) {
        }
        try {
            creatWordBgr();
        } catch (Exception e) {
        }
        createScreen();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void showScene(Instruction subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveScene();
        }
        subScene.moveScene();
        sceneToHide = subScene;

    }

    private void createScreen() {
        try {
            hdscreen = new Instruction();
            highscreen = new Instruction();
            playscreen = new Instruction();

        } catch (Exception e) {
        }
        mainPane.getChildren().add(hdscreen);
        mainPane.getChildren().add(highscreen);
        mainPane.getChildren().add(playscreen);
    }

    protected void createbuttons() {
        createdStartButton();
        createdScoresButton();
        createdInsButton();
        createdExitButton();

    }

    private void addMenuButton(MenuButton button) {
        button.setLayoutX(MENU_START_X);
        button.setLayoutY(MENU_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    protected void createdStartButton() {
        MenuButton startButton = new MenuButton("PLAY");
        addMenuButton(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showScene(playscreen);
            }
        });
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GameView gameManager = new GameView();
                gameManager.createNewGame(mainStage);
            }
        });
    }

    protected void createdScoresButton() {
        MenuButton scoreButton = new MenuButton("HIGH SCORES");
        addMenuButton(scoreButton);
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showScene(highscreen);
            }
        });

    }

    protected void createdInsButton() {
        MenuButton InsButton = new MenuButton("INSTRUCTION");
        addMenuButton(InsButton);
        InsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showScene(hdscreen);
            }
        });

    }

    protected void createdExitButton() {
        MenuButton ExitButton = new MenuButton("EXIT");
        addMenuButton(ExitButton);
        ExitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }

    private void createBackGround() throws Exception {
        Image bgrImg = new Image(new FileInputStream("src\\main\\resources\\bgr.gif"), 1000, 650, false, true);
        BackgroundImage bg = new BackgroundImage(bgrImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(1000, 650, false, false, false, false));
        mainPane.setBackground(new Background(bg));
    }


    private void moveBackground() {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 2);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 2);
        if (gridPane1.getLayoutY() >= 650) {
            gridPane1.setLayoutY(-650);
        }
        if (gridPane2.getLayoutY() >= 650) {
            gridPane2.setLayoutY(-600);
        }
    }

    private void createSound() throws Exception {
        Image logo = new Image(new FileInputStream("src\\main\\resources\\music (1).png"));
        ImageView img = new ImageView();
        img.setImage(logo);

        img.setLayoutX(150);
        img.setLayoutY(40);
        img.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img.setEffect(new DropShadow());
            }
        });
        img.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img.setEffect(null);
            }
        });

        mainPane.getChildren().add(img);
    }

    private void creatImgBgr() throws Exception {
        Image logo = new Image(new FileInputStream("src\\main\\resources\\menuImg.png"));
        ImageView img = new ImageView();
        img.setImage(logo);

        img.setLayoutX(600);
        img.setLayoutY(200);


        mainPane.getChildren().add(img);
    }

    private void creatWordBgr() throws Exception {
        Image logo = new Image(new FileInputStream("src\\main\\resources\\bomber (2).png"));
        ImageView img = new ImageView();
        img.setImage(logo);

        img.setLayoutX(300);
        img.setLayoutY(10);
        img.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img.setEffect(new DropShadow());
            }
        });
        img.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img.setEffect(null);
            }
        });

        mainPane.getChildren().add(img);
    }

}
