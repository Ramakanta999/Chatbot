package ChatBot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*................................................................................................................................
 . Copyright (c)
 .
 . The App	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 04/05/17 22:52
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
        
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ChatView.fxml"));
        primaryStage.setTitle("ChatBot");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.setResizable(false);
        primaryStage.setX((screen.getWidth() - 400) / 2);
        primaryStage.setY((screen.getHeight() - 400) / 2);
    
        Stage stage2 = new Stage();
        Parent root2 = FXMLLoader.load(getClass().getClassLoader().getResource("LearningView.fxml"));
        stage2.setTitle("Learning");
        stage2.setScene(new Scene(root2, 300, 200));
        stage2.setResizable(false);
        stage2.setX(primaryStage.getX() + 400);
        stage2.setY(primaryStage.getY());
    
        stage2.show();
        primaryStage.show();
    }
}
