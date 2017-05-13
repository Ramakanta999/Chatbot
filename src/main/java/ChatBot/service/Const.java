package ChatBot.service;

import ChatBot.model.DataBase;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Const	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 13/05/17 14:48
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

/**
 This class is used as a centralised holder of all the String variables that are used it this project.<br>
 This way, we can easily change the value over all the project, and also find quickly any usage of it.
 <br><br>
 This class also holds the 4 methods used for reading and writting in the .json save files.<br>
 This way, they can be accessed and used from anywher in the project.
 */
@SuppressWarnings({"WeakerAccess", "unchecked"})
public class Const
{
    //region --> Specifications for accessing MainView.fxml
    public static final String CHAT_VIEW_PATH   = "ChatView.fxml";
    public static final int    CHAT_VIEW_WIDTH  = 400;
    public static final int    CHAT_VIEW_HEIGHT = 430;
    //endregion
    
    //region --> Specifications for accessing LearningView.fxml
    public static final String LEARNING_VIEW_PATH   = "LearningView.fxml";
    public static final int    LEARNING_VIEW_WIDTH  = 600;
    public static final int    LEARNING_VIEW_HEIGHT = 400;
    //endregion
    
    //region --> Specifications for accessing LinksView.fxml
    public static final String LINKS_VIEW_PATH   = "LinksView.fxml";
    public static final int    LINKS_VIEW_WIDTH  = 460;
    public static final int    LINKS_VIEW_HEIGHT = 400;
    //endregion
    
    //region --> Specifications for accessing the .json serialized files
    public static final String SERIALIZATION_PATH = "./src/main/resources/serialization/";
    public static final String DB_PATH            = SERIALIZATION_PATH + "db.json";
    //endregion
    
    public static final int NOT_FOUND   = -1;
    public static final int GREETINGS   = 1;
    public static final int HOW_ARE_YOU = 2;
    public static final int MY_NAME_IS  = 7;
    public static final int EXIT_INDEX  = 6;
    
    /**
     This method will Serialize [db] at SERIALIZATION_PATH + DB_PATH <br>
     Warning : it will overwrite over what was previously written in the file.
     
     @param db The DataBase to serialize.
     */
    public static void writeDb (DataBase db)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(DB_PATH), db);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     This method will Deserialize [db] at SERIALIZATION_PATH + PLAYERS_PATH.<br>
 
     @return The DataBase that was deserialized.
     */
    public static DataBase readDb ()
    {
        DataBase db = new DataBase();
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(DB_PATH);
            if(file.exists() && !file.isDirectory())
            {
                db = mapper.readValue(file, DataBase.class);
            }
            else
            {
                db = new DataBase();
                writeDb(db);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        return db;
    }
    
    public static String getHelpMessage ()
    {
        StringBuilder helpMessage = new StringBuilder();
        
        helpMessage.append("Command Lines you can use : ").append("\n").append("/ChangeBotName:[newName]").append("\n").append(
                "/ChangeUserName:[newName]").append("\n").append("/Exit").append("\n");
        
        return helpMessage.toString();
    }
    
    public enum ReplacementCode
    {
        TIME_HOUR(new SimpleDateFormat("HH:mm").format(new Date())),
        TIME_DAY(new SimpleDateFormat("EEEE d MMM yyyy").format(new Date())),
        TIME_DATE(new SimpleDateFormat("dd/MM/yyyy").format(new Date())),
        CHATBOT_NAME("ChatBot"),
        USER_NAME("User");
        
        private String value;
    
        ReplacementCode (String value)
        {
            this.value = value;
        }
    
        public String getValue ()
        {
            return value;
        }
    
        public void setValue (String value)
        {
            this.value = value;
        }
    }
    
    public enum CommandLine
    {
        ChangeBotName,
        ChangeUserName,
        Exit,
        Help
    }
}
