package cs1302.gallery;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Georgia extends Pane{

	public Georgia(){
		setPrefSize(352.0, 222.0);
		ImageView imgView = new ImageView(new Image("file:res/Georgia.png"));
		imgView.setOpacity(.25);
		imgView.setX(75.0);
		imgView.setY(75.0);
		getChildren().add(imgView);
	}//Georgia
	
}//Georgia