package proje;

import java.awt.*;

import java.awt.event.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import javafx.animation.*;
public class JavaUI extends Application{
	public int UIMultiplier = 1;
	public ArrayList<Button> buttonsList;
	
	//deklarasyon
	private ImageView pacmanIV;
	private ImageView ghostIV;
	private ImageView foodIV;
	private ImageView wallIV;
	
	private Image pacmanIMG;
	private Image ghostIMG;
	private Image foodIMG;
	private Image wallIMG;
	
	private int mapSizeX = 100;
	private int mapSizeY = 100;
	private ImageView[][] map;

	/*public Frame frameMainGame=new Frame();  
	public Frame frameMainGameClose = new Frame();
	public Button buttonMainGame=new Button("New Game"); 
	public Button buttonMainGameClose = new Button("Quit");*/
	
    public void start(Stage stage) throws Exception {
    	
    	//get default screensize to set initial window size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		
		//set title of app
    	stage.setTitle("Pacman");
    	
    	Region gameRegion = new Region();
    	gameRegion.getStylesheets().add("style.css");
    	gameRegion.getStyleClass().add("sa");
    	gameRegion.setMinSize(400, 400);
    	gameRegion.setMaxSize(555, 555);
        
        //load images going to be used
        Image image = new Image(new FileInputStream("D:\\kapi.jpg"));
        pacmanIMG = new Image(new FileInputStream("D:\\pacmanMan.png"));
        ghostIMG = new Image(new FileInputStream("D:\\a.png"));
        foodIMG = new Image(new FileInputStream("D:\\kapi.jpg"));
        wallIMG = new Image(new FileInputStream("D:\\kapi.jpg"));
        
        
        pacmanIV = new ImageView();
        ghostIV = new ImageView();
        
        pacmanIV.setImage(pacmanIMG);
        ghostIV.setImage(ghostIMG);
        
        //pacmanIV.fitWidthProperty().bind(gameRegion.widthProperty()); 
        pacmanIV.setPreserveRatio(true);
        pacmanIV.setFitWidth(100);
        pacmanIV.setFitHeight(100);
        pacmanIV.visibleProperty().set(true);
        //ghostIV.fitWidthProperty().bind(gameRegion.widthProperty()); 
        ghostIV.setPreserveRatio(true);
        ghostIV.setFitWidth(100);
        ghostIV.setFitHeight(100);
        ghostIV.visibleProperty().set(true);
        
        //Creating the image view
        ImageView imageView = new ImageView();
        //Setting image to the image view
        imageView.setImage(image);
        //Setting the image view parameters
        imageView.setX(10);
        imageView.setY(10);
        imageView.setFitWidth(33);
        imageView.setPreserveRatio(true);

        /*
         * 

StackPane lays out its child Nodes according just to the alignment property of the StackPane itself:
 the layoutX and layoutY of the Node are basically ignored.
  The default value of the alignment property is CENTER.
   Your best solution here is just to use a Pane instead of a StackPane.
You could also potentially solve this by setting the translateX property
 of the images to be placed in the StackPane. However, note that such transforms
  are not included in layout calculations, so you introduce the possibility of pushing
   the cards out of the visible bounds of the StackPane.

!cannot use stackpane ^^
!cannot use tilepane due to automatic sortition of images -> fill
	
         * 
         */

    	StackPane root = new StackPane();
    	StackPane mainMenuPane = new StackPane();
    	Pane gamePane = new Pane();
    	TilePane r = new TilePane();
    	//r.setVisible(false);
  
    	
    	//gamePane.setPrefWidth(mapSizeX);
    	
    	//r.setMaxWidth(screenWidth/3);
    	//r.setMinWidth(screenWidth/3);
    	
    	gamePane.setStyle("-fx-background-color: #000000");
    	
    	Pane gamePanel = new Pane();

        gamePane.getChildren().add(pacmanIV);
        
        ghostIV.setX(100);
        ghostIV.setY(300);
        ghostIV.setFitHeight((gamePane.getHeight())/(double)mapSizeY);
        ghostIV.setFitWidth((gamePane.getWidth())/(double)mapSizeX);

        gamePane.getChildren().add(ghostIV);

    	Scene scene = new Scene(root, screenWidth/2 , screenHeight/2);
    	Scene sceneMainMenu = new Scene(mainMenuPane, screenWidth/2 , screenHeight/2);
        Label label = new Label("Hello World, JavaFX !");
        
        scene.getStylesheets().add("style.css");
       
        label.getStyleClass().add("style.css");
        label.getStyleClass().add("");
        root.getStyleClass().add("style.css");
        root.getStyleClass().add("");
        
        root.getChildren().add(label);
        root.getChildren().add(imageView);
        //gamePane.getChildren().add(pacmanIV);
        //root.getChildren().add(ghostIV);
        //root.getChildren().add(gameRegion);
        root.getChildren().add(gamePane);
        root.getChildren().add(r);
    
        scene.setOnKeyPressed(event -> {
        	switch(event.getCode()) {
        	case W:
        		//System.out.println(imageView.getX() + " " + imageView.getY());
        		MovementAnimator(pacmanIV, gamePane, "PlusY");
        		//System.out.println(pacmanIV.getX() + " " + pacmanIV.getY());
        		//System.out.println(ghostIV.getX() + " " + ghostIV.getY());
        		System.out.println(pacmanIV.getLayoutX() + " " + pacmanIV.getLayoutX());

        		break;
        	case A:
        		MovementAnimator(pacmanIV, gamePane, "MinusX");
        		System.out.println(pacmanIV.getLayoutX() + " " + pacmanIV.getLayoutX());

        		break;
        	case S:
        		MovementAnimator(pacmanIV, gamePane, "MinusY");
        		System.out.println(pacmanIV.getLayoutX() + " " + pacmanIV.getLayoutX());

        		break;
        	case D:
        		MovementAnimator(pacmanIV, gamePane, "PlusX");
        		System.out.println(pacmanIV.getLayoutX() + " " + pacmanIV.getLayoutX());

        		break;
        	default:
        		break;
        			
        	}
        });

		//animation for mobs
	
        stage.setScene(scene);
        stage.show();
        
        Map map = new Map(mapSizeX,mapSizeY,gamePane,stage,ghostIMG);
        map.GenerateMap();
        
        
		stage.widthProperty().addListener(e -> {
		
			for(int i = 0; i < mapSizeX; i++) {
				for(int a = 0; a < mapSizeY; a++) {
					if(gamePane.getChildren().get(i) instanceof ImageView) {
						
						//! really important -> set fitwidth as the total amount of object on one row
						map.getMap()[i][a].setX((i*stage.getWidth())/mapSizeX);
						map.getMap()[i][a].setFitWidth(stage.getWidth()/mapSizeX);
						//gamePane.getWidth()/mapSizeX + equalizer
					}	
				}

				
			}
			
		});

		stage.heightProperty().addListener(e -> {
			
			for(int i = 0; i < mapSizeX; i++) {
				for(int a = 0; a < mapSizeY; a++) {
					if(gamePane.getChildren().get(i) instanceof ImageView) {
						
						//! really important -> set fitwidth as the total amount of object on one row
						map.getMap()[i][a].setY((a*stage.getHeight())/mapSizeY);
						map.getMap()[i][a].setFitHeight(stage.getHeight()/mapSizeY);
						//gamePane.getWidth()/mapSizeX + equalizer
					}	
				}

				
			}
			

		});
		
		
    }
    
	public JavaUI() {
		//instansasyon of main window

	}
	
    public static void main(String[] args) {

		JavaUI ui = new JavaUI();
		Application.launch(args);
		Scanner scanner = new Scanner(System.in);

    }
    
    
    
    public void MovementAnimator(Node mob, Node gamePane, String Pos2D) {
		/*Line path = new Line();
		path.setStartX(mob.getLayoutX());
		path.setStartY(mob.getLayoutY());
		path.setEndX(mob.getLayoutX() +50);
		path.setEndY(mob.getLayoutX() +50);
		*/
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(mob);
		transition.setDuration(Duration.seconds(0));
		switch(Pos2D) {
		case "PlusX":
			transition.setToX(mob.getLayoutX() + ((Pane)gamePane).getWidth()/mapSizeX);
			transition.setToY(mob.getLayoutY());
			
			transition.setOnFinished(e -> {
			mob.setLayoutX(mob.getLayoutX() + ((Pane)gamePane).getWidth()/mapSizeX);

			});
			break;
		case "PlusY":
			transition.setToX(mob.getLayoutX());
			transition.setToY(mob.getLayoutY() - ((Pane)gamePane).getWidth()/mapSizeY);
			transition.setOnFinished(e -> {
			mob.setLayoutY(mob.getLayoutY() - ((Pane)gamePane).getWidth()/mapSizeY);

			});
			break;
		case "MinusX":
			transition.setToX(mob.getLayoutX() - ((Pane)gamePane).getWidth()/mapSizeX);
			transition.setToY(mob.getLayoutY());
			transition.setOnFinished(e -> {
			mob.setLayoutX(mob.getLayoutX() - ((Pane)gamePane).getWidth()/mapSizeX);

			});
			break;
		case "MinusY":
			transition.setToX(mob.getLayoutX());
			transition.setToY(mob.getLayoutY() + ((Pane)gamePane).getWidth()/mapSizeY);
			transition.setOnFinished(e -> {
			mob.setLayoutY(mob.getLayoutY() + ((Pane)gamePane).getWidth()/mapSizeX);

			});
			break;
		default:
			//	transition.setToX(mob.getLayoutX());
			//	transition.setToY(mob.getLayoutY());
				//mob.setLayoutX(mob.getLayoutX());
			break;
		}
		
		transition.play();
		

    }
    
    public void Death(Node node) {
        FadeTransition ft = new FadeTransition();
        ft.setNode(node);
        ft.setDuration(new Duration(2000));
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(6);
        ft.setAutoReverse(true);
    }
        

	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		JFXPanel jfxPanel = new JFXPanel();
		
		JavaUI ui = new JavaUI();
		Scanner scanner = new Scanner(System.in);
		
		Label l1 = new Label();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();

		Text text = new Text("dsf");
		ui.frameMainGame.add(jfxPanel);
		
		ui.buttonMainGame.setBounds(30,50,80,30); 
		ui.buttonMainGameClose.setBounds(70, 80, 100, 50);
		ui.frameMainGame.add(l1);
	    l1.setBounds(150,55, 250,30);  
		l1.setText("Authors: Tolgay Baba, Yahya, Nour");
		
		
		ui.frameMainGame.add(ui.buttonMainGame);  
		ui.frameMainGame.setSize(300,300);  
		ui.frameMainGame.setLayout(null);  
		ui.frameMainGame.setVisible(true); 
		
		ui.frameMainGame.add(ui.buttonMainGameClose);  
		ui.frameMainGame.setSize((int)screenWidth * 1/2, (int)screenHeight * 1/2);  
		ui.frameMainGame.setLayout(null);  
		ui.frameMainGame.setVisible(true);
		//action listener for main button event

		ui.buttonMainGameClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);

			}
			
		});

		
		if(scanner.next().equals("q")) {
			System.exit(0);
		}
		if(scanner.next().equals("m")) {
			for(int i = 0; i < ui.buttonsList.size(); i++) {
				ui.buttonsList.get(i).setBounds(ui.buttonsList.get(i).getX(), ui.buttonsList.get(i).getY(), ui.buttonsList.get(i).getWidth()*2, ui.buttonsList.get(i).getWidth()*2);
			}
			ui.UIMultiplier += 1;
		}
		if(scanner.next().equals("n")) {
			ui.UIMultiplier -= 1;
		}
	
	
	
	}*/

}

class Map extends JavaUI {
	
	
	private ImageView[][] map;
	private int mapSizeX;
	private int mapSizeY;
	private Pane gamePane;
	private Stage stage;
	private Image img;
	
	
	
	public Map(int mapSizeX, int mapSizeY, Pane gamePane, Stage stage, Image img) {
		this.img = img;
		this.gamePane = gamePane;
		this.stage = stage;
		this.mapSizeX = mapSizeX;
		this.mapSizeY = mapSizeY;
		map = new ImageView[mapSizeX][mapSizeY];
	}
	
    public ImageView[][] getMap() {
		return map;
	}

	public void setMap(ImageView[][] map) {
		this.map = map;
	}

	public int getMapSizeX() {
		return mapSizeX;
	}

	public void setMapSizeX(int mapSizeX) {
		this.mapSizeX = mapSizeX;
	}

	public int getMapSizeY() {
		return mapSizeY;
	}

	public void setMapSizeY(int mapSizeY) {
		this.mapSizeY = mapSizeY;
	}

	public Pane getGamePane() {
		return gamePane;
	}

	public void setGamePane(Pane gamePane) {
		this.gamePane = gamePane;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Pane GenerateMap() {
    	for(int i = 0; i < mapSizeX; i++) {
    		for(int a = 0; a < mapSizeY; a++) {
    			
        		ImageView s = new ImageView();
        		s.setX((i*gamePane.getWidth())/mapSizeX);
        		s.setY((a*gamePane.getHeight())/mapSizeY);
        		s.setImage(img);
        		s.setFitHeight(gamePane.getHeight()/mapSizeY);
        		s.setFitWidth(gamePane.getWidth()/mapSizeX);
	
        		map[i][a] = s;
        		
        		gamePane.getChildren().add(s);

    		}
    		System.out.println(stage.getWidth());
    	}
    	
    	return gamePane;
    }
	
	
}