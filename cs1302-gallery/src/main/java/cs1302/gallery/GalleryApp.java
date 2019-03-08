package cs1302.gallery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GalleryApp extends Application {

    String playPause = "play";
    Label[] label = new Label[20];
    String[] imagesURL = new String[20];
    double progress = 1.0;
    ProgressBar progressBar = new ProgressBar();
    ArrayList<String> urlList = new ArrayList<String>();
    int urlListCount = 0;
    Timeline timeline;
    ToolBar toolBar = new ToolBar();
    MenuBar menuBar = new MenuBar();
    TextField textField = new TextField();
    Button updateImages = new Button("Update Images");
    Button removeGIF = new Button("Remove Images");
    VBox root = new VBox();
    StackPane stack = new StackPane();
    Pane animePane = new Pane();
    Snow snow = new Snow();
    EasterBunny easterBunny = new EasterBunny();
    Pumpkin pumpkin = new Pumpkin();
    Georgia georgia = new Georgia();
    
    TilePane galleryPane = new TilePane();
    Scene scene = new Scene(root);
    
    Menu file = new Menu("File");
    Menu help = new Menu("Help");
    Menu theme = new Menu("Theme");
    
    MenuItem defaultBlank = new MenuItem("Default");
    MenuItem halloween = new MenuItem("Halloween");
    MenuItem christmas = new MenuItem("Christmas");
    MenuItem easter	= new MenuItem("Easter");
    MenuItem ugaTheme = new MenuItem("Bulldogs");
    MenuItem about = new MenuItem("About Jared");
    Button play = new Button(playPause);

    MenuItem exit = new MenuItem("Exit");
	
    /**
     * The start method of javafx that handles the
     * GUI in GalleryApp.java
     *
     * @param stage  
     */
    @Override
    public void start(Stage stage) {
    	
    	progressBar.setProgress(progress);
    	
    	new Thread(){
	    public void run(){
		Platform.runLater(() -> progressBar.setProgress(0.0));
		
		engine("all");
		
		double progressInc = (1.0 - progress) / 20;
		
		if(urlList.size() > 0){
		    for(int x = 0; x < label.length; x++){
			final int e = x;
			progress += progressInc;
			Platform.runLater(() -> progressBar.setProgress(progress));
			Platform.runLater(() -> label[e].setBackground(new Background(new BackgroundFill(Color.WHITE, null, null))));
			Platform.runLater(() -> label[e].setGraphic(new ImageView(urlList.get(++urlListCount))));
		    }//for
		    
		}//if
		
	    }//run
	    
	}.start();
	
	//intializes the labels for images
    	for(int x = 0; x < label.length; x++){
	    label[x] = new Label();
	    label[x].setPrefSize(100, 100);
    	}//for
    	
    	//exit button to exit program safely
        exit.setOnAction(new EventHandler<ActionEvent>(){
		
        	public void handle(ActionEvent e){
		    Platform.exit();
		    System.exit(0);
        	}//handle
        	
	    });
        
        //button that opens the about page
        about.setOnAction(new EventHandler<ActionEvent>(){
		
        	public void handle(ActionEvent e){
		    About about = new About();
		    about.show();
        	}//handle
        	
	    });
        
        file.getItems().add(exit);
        theme.getItems().addAll(defaultBlank, halloween, christmas, easter, ugaTheme);
        help.getItems().add(about);
        
        //button that plays/pauses the sequences of images changing
        play.setOnAction(new EventHandler<ActionEvent>(){
		
        	public void handle(ActionEvent e){
		    if(playPause.equals("play")){
			playPause = "pause";
			play.setText(playPause);
			timeline.pause();
		    }//if
		    else{
			playPause = "play";
			play.setText(playPause);
			timeline.play();
		    }//else
        	}//handle
        	
	    });
        
        //button that upadtes the images based on the user search
        updateImages.setOnAction(new EventHandler<ActionEvent>(){
	
		public void handle(ActionEvent e){
		    urlList = new ArrayList<String>();
		    urlListCount = 0;
		    new Thread(){
			public void run(){
			    Platform.runLater(() -> progressBar.setProgress(0.0));
        		    
			    engine(textField.getText());
			    
			    double progressInc = (1.0 - progress) / 20;
			    
			    if(urlList.size() > 19){
				for(int x = 0; x < label.length; x++){
				    final int e = x;
				    progress += progressInc;
				    Platform.runLater(() -> progressBar.setProgress(progress));
				    Platform.runLater(() -> label[e].setGraphic(new ImageView(urlList.get(++urlListCount))));
				}//for
        			
			    }//if
			    else{
				urlList = new ArrayList<String>();
				progress = 1.0;
				progressBar.setProgress(1.0);
			    }//else
			    
			}//run
        		
		    }.start();
		    
        	}//handle
        	
	    });//setOnAction
        
        toolBar.getItems().addAll(play, textField, updateImages);
        menuBar.getMenus().addAll(file, theme, help);
        
	//button that removes the gifs/image from the page
        removeGIF.setOnAction(new EventHandler<ActionEvent>(){
		
        	public void handle(ActionEvent e){
		    stack.getChildren().removeAll(pumpkin, easterBunny, georgia, snow);
		    stage.setScene(scene);
		    stage.show();
		    toolBar.getItems().remove(removeGIF);
        	}//handle
        	
	    });
        
        //handles the Christmas theme
        christmas.setOnAction(new EventHandler<ActionEvent>(){
		
        	public void handle(ActionEvent e){
		    toolBar.getItems().remove(removeGIF);
		    toolBar.getItems().add(removeGIF);
		    stack.getChildren().removeAll(pumpkin, easterBunny, georgia);
		    stack.getChildren().add(snow);
		    scene.getStylesheets().clear();
		    scene.getStylesheets().removeAll("res/Bulldog.css", "res/Easter.css", "res/Default.css", "res/Christmas.css");
		    scene.getStylesheets().add("res/Christmas.css");
		    stage.setScene(scene);
		    stage.show();
        	}//handle
        	
	    });
        
        //handles the Halloween theme
        halloween.setOnAction(new EventHandler<ActionEvent>(){
		
        	public void handle(ActionEvent e){
		    toolBar.getItems().remove(removeGIF);
		    toolBar.getItems().add(removeGIF);
		    stack.getChildren().removeAll(snow, easterBunny, georgia);
		    stack.getChildren().add(pumpkin);
		    scene.getStylesheets().clear();
		    scene.getStylesheets().removeAll("res/Bulldog.css", "res/Easter.css", "res/Default.css", "res/Christmas.css");
		    scene.getStylesheets().add("res/Halloween.css");
		    stage.setScene(scene);
		    stage.show();
        	}//handle
        	
	    });
        
        //handles the Easter theme
        easter.setOnAction(new EventHandler<ActionEvent>(){
		
        	public void handle(ActionEvent e){
		    toolBar.getItems().remove(removeGIF);
		    toolBar.getItems().add(removeGIF);
		    stack.getChildren().removeAll(snow, pumpkin, georgia);
		    stack.getChildren().add(easterBunny);
		    scene.getStylesheets().clear();
		    scene.getStylesheets().removeAll("res/Halloween.css", "res/Bulldog.css", "res/Default.css", "res/Christmas.css");
		    scene.getStylesheets().add("res/Easter.css");
		    stage.setScene(scene);
		    stage.show();
        	}//handle
        	
	    });
        
        //handles the default look theme
        defaultBlank.setOnAction(new EventHandler<ActionEvent>(){
		
        	public void handle(ActionEvent e){
		    toolBar.getItems().remove(removeGIF);
		    stack.getChildren().removeAll(easterBunny, snow, pumpkin, georgia);
		    scene.getStylesheets().clear();
        	}//handle
        	
	    });
        
        //handles the UGA bulldogs theme
        ugaTheme.setOnAction(new EventHandler<ActionEvent>(){
		
        	public void handle(ActionEvent e){
		    toolBar.getItems().remove(removeGIF);
		    toolBar.getItems().add(removeGIF);
		    stack.getChildren().removeAll(easterBunny, snow, pumpkin);
		    stack.getChildren().add(georgia);
		    scene.getStylesheets().removeAll("res/Halloween.css", "res/Easter.css", "res/Default.css", "res/Christmas.css");
		    scene.getStylesheets().add("res/Bulldog.css");
		    stage.setScene(scene);
		    stage.show();
        	}//handle
        	
	    });
        
        galleryPane.setPrefColumns(5);
        galleryPane.setPrefRows(4);
        
	//initally adds labels to gallerypane
        for(int x = 0; x < 20; x++){
	    galleryPane.getChildren().add(label[x]);
        }//for
        
        stack.setAlignment(Pos.TOP_CENTER);
        stack.getChildren().add(galleryPane);
        
        root.getChildren().addAll(menuBar, toolBar, stack, progressBar);
        
        stage.setTitle("Gallery!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
        
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), new EventHandler<ActionEvent>(){
		
        	public void handle(ActionEvent e){
		    if(urlList.size() > 0){
			Random r = new Random();
			label[r.nextInt(20)].setGraphic(new ImageView(urlList.get(urlListCount)));
			urlListCount++;
        		
			if(urlListCount == urlList.size()){
			    urlListCount = 0;
			}//if
        		
		    }//if
		    
        	}//handle
        	
	    });//handler);
        
        KeyFrame snowTimer = new KeyFrame(Duration.seconds(.125), new EventHandler<ActionEvent>(){
        	
        	public void handle(ActionEvent e){
		    snow.move();        		
        	}//handle
        	
	    });//handler);
	
        Timeline timeline2 = new Timeline();
        timeline2.setCycleCount(Timeline.INDEFINITE);
        timeline2.getKeyFrames().addAll(snowTimer);
        timeline2.play();
        
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.play();
        
    } // start
    
    /**
     * main method of GalleryApp.java
     * 
     * @param args
     */
    public static void main(String[] args) {
    	try {
	    Application.launch(args);
	} catch (UnsupportedOperationException e) {
	    System.out.println(e);
	    System.err.println("If this is a DISPLAY problem, then your X server connection");
	    System.err.println("has likely timed out. This can generally be fixed by logging");
	    System.err.println("out and logging back in.");
	    System.exit(1);
	} // try
    	
    } // main
    
    /**
     * The engine method handles the URL and grabs the images needed and 
     * stores them into an ArrayList
     * 
     * @param s user input string
     */
    public void engine(String s){
	if(s != null){
	    try{
		String searchString;
		String urlString;
		String tempString = "";
		searchString = s;
		String[] stringArray = searchString.split("\\b");
		
		tempString += stringArray[0];
		
		for(int x = 1; x < stringArray.length; x++){
		    if(!stringArray[x].equals(" ")){
			tempString += "+" + stringArray[x];
		    }//if
		    
		}//for
		
		urlString = "https://itunes.apple.com/search?term=" + tempString.trim();
		
		URL url = new URL(urlString);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));				
		String strLine = "";
		String tempLine = "";
		double count = 0.0;
		double progressCounter = 0.0;
		
		while((strLine = br.readLine()) != null){
		    progressCounter += 1.0;
		}//while
		
		strLine = "";
		br = new BufferedReader(new InputStreamReader(url.openStream()));
		
		while((strLine = br.readLine()) != null){
		    final double e = count;
		    final double m = progressCounter;
		    progress = e / m;
		    Platform.runLater(() -> progressBar.setProgress(progress));
		    count++;
		    
		    for(int x = 0; x < strLine.length(); x++){
			if(x < (strLine.length() - 16) && strLine.substring(x, x + 16).equalsIgnoreCase("artworkUrl100\":\"")){
			    for(int y = x + 16; y < strLine.length(); y++){
				if(strLine.charAt(y) == 34){
				    if(!searchList(tempLine.trim())){
					tempLine.trim();
					urlList.add(tempLine);
				    }//if
				    tempLine = "";
				    break;
				}//if
				else{
				    tempLine += strLine.charAt(y) + "";
				}//else
				
			    }//for
			    
			}//if
			
		    }//for
		    
		}//while	
		
		br.close();
		
	    }//try
	    catch(IOException e){
		//left open
	    }//catch
	    
	}//if
	
    }//Engine
    
    /**
     * checks to see if the image url already exists in the ArrayList
     *
     * @param s string to be checked to see if it already exists
     *
     * @return boolean returns true if the url exists
     */
    public boolean searchList(String s){
	for(int x = 0; x < urlList.size(); x++){
	    if(s.equalsIgnoreCase(urlList.get(x))){
		return true;
	    }//if
	    
	}//for
	
	return false;
	
    }//searchList
    
} // GalleryApp
