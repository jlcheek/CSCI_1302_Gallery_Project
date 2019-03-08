package cs1302.gallery;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class About extends Stage{
    
    public About(){
	
	StackPane pane = new StackPane();
	pane.setAlignment(Pos.TOP_CENTER);
	
	Scene newScene = new Scene(pane, 400, 450);
	
	Label picture = new Label();
	picture.setPrefSize(150, 150);
	picture.setGraphic(new ImageView(new Image("file:res/me.png")));
	picture.setTranslateY(150);
	
	Label backDrop = new Label();
	backDrop.setPrefSize(400, 200);
	backDrop.setGraphic(new ImageView(new Image("file:res/me2.png")));
	
	Label name = new Label("Jared Cheek");
	name.setPrefSize(130, 10);
	name.setFont(new Font("Arial Bold", 20));
	name.setMinWidth(Region.USE_PREF_SIZE);
	name.setMaxWidth(Region.USE_PREF_SIZE);
	name.setTranslateY(320);
	
	Label email = new Label("jlcheek@uga.edu");
	email.setPrefSize(135, 10);
	email.setFont(new Font("Arial Bold", 15));
	email.setMinWidth(Region.USE_PREF_SIZE);
	email.setMaxWidth(Region.USE_PREF_SIZE);
	email.setTranslateY(355);
	
	Label version = new Label("Version: 1.0");
	version.setPrefSize(95, 10);
	version.setFont(new Font("Arial Bold", 15));
	version.setMinWidth(Region.USE_PREF_SIZE);
	version.setMaxWidth(Region.USE_PREF_SIZE);
	version.setTranslateY(385);
	
	pane.getChildren().addAll(backDrop, picture, name, email, version);
	
	initModality(Modality.APPLICATION_MODAL);
	setTitle("About Jared");
	setScene(newScene);
        setMaxHeight(450);
        sizeToScene();
	setResizable(false);
	
    }//About
    
}//About