package ChatBot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static ChatBot.service.Const.*;

/*................................................................................................................................
 . Copyright (c)
 .
 . The App	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 05/05/17 22:29
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class App extends Application
{
    public static void main (String[] args)
    {
        launch(args);
    }
    
    @SuppressWarnings("ConstantConditions")
    @Override
    public void start (Stage primaryStage) throws Exception
    {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
    
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(CHAT_VIEW_PATH));
        primaryStage.setTitle("ChatBot");
        primaryStage.setScene(new Scene(root, CHAT_VIEW_WIDTH, CHAT_VIEW_HEIGHT));
        primaryStage.setResizable(false);
        primaryStage.setX((screen.getWidth() - CHAT_VIEW_WIDTH) / 2);
        primaryStage.setY((screen.getHeight() - CHAT_VIEW_HEIGHT) / 2);
    
        Stage stage2 = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource(LEARNING_VIEW_PATH));
        stage2.setTitle("Learning");
        stage2.setScene(new Scene(root2, LEARNING_VIEW_WIDTH, LEARNING_VIEW_HEIGHT));
        stage2.setResizable(false);
        stage2.setX(primaryStage.getX() + CHAT_VIEW_WIDTH);
        stage2.setY(primaryStage.getY());
    
        Stage stage3 = new Stage();
        Parent root3 = FXMLLoader.load(getClass().getClassLoader().getResource(LINKS_VIEW_PATH));
        stage3.setTitle("Learning");
        stage3.setScene(new Scene(root3, LINKS_VIEW_WIDTH, LINKS_VIEW_HEIGHT));
        stage3.setResizable(false);
        stage3.setX(primaryStage.getX() - LINKS_VIEW_WIDTH);
        stage3.setY(primaryStage.getY());
    
        stage2.show();
        stage3.show();
        primaryStage.show();
    }
}
