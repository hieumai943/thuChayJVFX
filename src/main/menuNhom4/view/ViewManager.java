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
import javafx.stage.Stage;
import model.Instruction;
import model.MenuButton;

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
    private Instruction hdscreen ;
    private Instruction highscreen;
    private Instruction playscreen;
    private List<MenuButton> menuButtons = new ArrayList<>();
    private GridPane gridPane1;
    private GridPane gridPane2;
    private AnimationTimer bgrTimer;

    private Instruction sceneToHide;
    public ViewManager(){
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);

        try {
            createBackGround();
            createBgrLoop();
        } catch (Exception e){}
        createbuttons();
        try{
            createSound();
        }catch (Exception e){}
       createScreen();
    }
    public Stage getMainStage() {
        return mainStage;
    }
   private void showScene(Instruction subScene){
        if(sceneToHide != null){
            sceneToHide.moveScene();
        }
        subScene.moveScene();
        sceneToHide = subScene;

   }
    private  void createScreen(){
        try {
            hdscreen = new Instruction();
            highscreen = new Instruction();
            playscreen = new Instruction();

        }catch(Exception e){}
        mainPane.getChildren().add(hdscreen);
        mainPane.getChildren().add(highscreen);
        mainPane.getChildren().add(playscreen);
    }
    protected void createbuttons(){
        createdStartButton();
        createdScoresButton();
        createdInsButton();
        createdExitButton();

    }
    private void addMenuButton(MenuButton button){
        button.setLayoutX(MENU_START_X);
        button.setLayoutY(MENU_START_Y + menuButtons.size()*100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }
    protected void createdStartButton(){
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
    protected void createdScoresButton(){
        MenuButton scoreButton = new MenuButton("HIGH SCORES");
        addMenuButton(scoreButton);
        scoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                showScene(highscreen);
            }
        });

    }
    protected void createdInsButton(){
        MenuButton InsButton = new MenuButton("INSTRUCTION");
        addMenuButton(InsButton);
        InsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               showScene(hdscreen);
            }
        });

    }
    protected  void createdExitButton(){
        MenuButton ExitButton = new MenuButton("EXIT");
        addMenuButton(ExitButton);
        ExitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }


    protected void createBackGround() throws Exception {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();
        for(int i=0; i<21;i++){
            Image bgr1 = new Image(new FileInputStream("src\\main\\resources\\deep_blue.png"));
            Image bgr2 = new Image(new FileInputStream("src\\main\\resources\\deep_blue.png"));
            ImageView bgrImg1= new ImageView();
            ImageView bgrImg2= new ImageView();
            bgrImg1.setImage(bgr1);
            bgrImg2.setImage(bgr2);
            GridPane.setConstraints(bgrImg1, i %7 , i /7);
            GridPane.setConstraints(bgrImg2, i %7, i /7);
            gridPane1.getChildren().add(bgrImg1);
            gridPane2.getChildren().add(bgrImg2);


        }
        gridPane2.setLayoutY(-650);
        mainPane.getChildren().addAll(gridPane1,gridPane2);


    }
    private void createBgrLoop(){
        bgrTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveBackground();
            }
        };
        bgrTimer.start();

    }
    private  void moveBackground(){
        gridPane1.setLayoutY(gridPane1.getLayoutY()+ 2);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 2);
        if(gridPane1.getLayoutY() >= 650){
            gridPane1.setLayoutY(-650);
        }
        if(gridPane2.getLayoutY() >= 650 ){
            gridPane2.setLayoutY(-600);
        }
    }
    private void createSound() throws Exception {
        Image logo = new Image(new FileInputStream("src\\main\\resources\\music (1).png"));
        ImageView img= new ImageView();
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
}
