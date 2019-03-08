package cs1302.gallery;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EasterBunny extends Pane{

    public EasterBunny(){
	setPrefSize(235.0, 234.0);
	ImageView imgView = new ImageView(new Image("file:res/easter-bunny.gif"));
	imgView.setOpacity(.25);
	imgView.setX(500.0 - 235.0);
	imgView.setY(400.0 - 234.0);
	getChildren().add(imgView);
    }//EasterBunny
    
}//EasterBunny
