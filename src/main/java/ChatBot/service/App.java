package ChatBot.service;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static ChatBot.service.Const.*;

/*................................................................................................................................
 . Copyright (c)
 .
 . The App	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 12/05/17 13:58
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 This class is the entry point of the applications.<br>
 It starts the JavaFX Application and displays the first panel views.
 **/
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(CHAT_VIEW_PATH));
        primaryStage.setTitle("ChatBot");
        primaryStage.setScene(new Scene(root, CHAT_VIEW_WIDTH, CHAT_VIEW_HEIGHT));
        primaryStage.setResizable(false);
        
        primaryStage.show();
    }
}
