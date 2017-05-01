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
 . Last Modified : 01/05/17 15:25
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
        primaryStage.show();
    }
}
