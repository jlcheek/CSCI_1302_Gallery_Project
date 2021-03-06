package cs1302.gallery;

import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Snow extends Pane{
	
    Circle[] circle = new Circle[20];
    double[] x = new double[20];
    double[] y = new double[20];
    
    Circle[] circle2 = new Circle[20];
    double[] x2 = new double[20];
    double[] y2 = new double[20];
    
    
    public Snow(){
	setPrefSize(500, 400);
	
	for(int x = 0; x < 20; x++){
	    Random r = new Random();
	    this.x[x] = (double)r.nextInt(500);
	    this.y[x] = (double)r.nextInt(400);
	    circle[x] = new Circle(this.x[x], this.y[x], 4.0, Color.WHITE);
	    circle[x].setOpacity(.65);
	    getChildren().add(circle[x]);
	}//for
	
	for(int x = 0; x < 20; x++){
	    Random r = new Random();
	    this.x2[x] = (double)r.nextInt(500);
	    this.y2[x] = (double)r.nextInt(400);
	    circle2[x] = new Circle(this.x2[x], this.y2[x], 3.0, Color.WHITE);
	    getChildren().add(circle2[x]);
	}//for
	
    }//Animation
    
    public void move(){
	for(int x = 0; x < 20; x++){
	    if(this.y[x] >= 400){
		this.y[x] = 0;
	    }//if
	    if(x % 2 == 0){
		this.y[x] += 1;
	    }//if
	    else{
		this.y[x] += 2;
	    }//else
	    
	    circle[x].setCenterY(this.y[x]);
	    
	}//for
	
	for(int x = 0; x < 20; x++){
	    if(this.y2[x] >= 398){
		this.y2[x] = 0;
	    }//if
	    if(x % 2 == 0){
		this.y2[x] += 1;
	    }//if
	    else{
		this.y2[x] += 2;
	    }//else
	    circle2[x].setCenterY(this.y2[x]);
	    
	}//for
	
    }//for
    
}//Animation
