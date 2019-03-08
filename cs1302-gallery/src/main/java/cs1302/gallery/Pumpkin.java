package cs1302.gallery;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Pumpkin extends Pane{

	public Pumpkin(){
	    setPrefSize(400.0, 566.0);
	    ImageView imgView = new ImageView(new Image("file:res/giphy.gif"));
	    imgView.setOpacity(.25);
	    imgView.setX(0.0);
	    imgView.setY(0.0);
	    getChildren().add(imgView);
	}//Pumpkin
    
}//Pumpkin