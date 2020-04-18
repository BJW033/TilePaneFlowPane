package pkgMain;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/*
 * 
 */

public class MyMovingImageView2 extends Application {
	
	private ImgController2 imc;
	private ImageView iv1;
	private final double WIDTH = 800;
	private final double HEIGHT = 600;
	private TilePane tP;
	private FlowPane fP;
	

	public MyMovingImageView2(){
    	iv1 = new ImageView();
		imc = new ImgController2(this);
		tP = new TilePane();
		fP = new FlowPane();
	}
		
    @Override
    public void start(Stage stage) {

    	Image im1 = new Image(getClass().getResourceAsStream("/img/commonMilkweed.png"),100,100,true,false);
    
    	iv1.setImage(im1);
    	iv1.setPreserveRatio(true);
    	iv1.setFitHeight(100);
    
    	//iv1.setOnMouseDragged(imc.getHandlerForDrag());
    	iv1.setOnDragDetected(imc.getHandlerForDragDetected());
    	
    	
    	
    
    	
    	
    	tP.setStyle("-fx-background-color: blue;");
    	tP.setMaxHeight(100);
    	tP.setMaxWidth(400);
    	
    	//tP.setOnMouseEntered(imc.getHandlerForMouseEnter());
    	//tP.setOnMouseExited(imc.getHandlerForMouseExited());
    	tP.setOnDragOver(imc.getHandlerForsetOnDragOver());
    	tP.setOnDragDropped(imc.getHandlerforsetOnDragDropped());
    	
    	fP.getChildren().add(iv1);
    	fP.setStyle("-fx-background-color: darkgray;");
    	fP.setMaxHeight(100);
    	fP.setMaxWidth(400);

    	
    	
    	StackPane stack = new StackPane(tP,fP);
    	StackPane.setAlignment(fP, Pos.TOP_LEFT);
    	StackPane.setAlignment(tP,Pos.BOTTOM_RIGHT);
    	
    	
    	
    	Scene scene = new Scene(stack, WIDTH, HEIGHT);
    	
    	
        stage.setScene(scene);
        
        
        
	
		//iv1.setTranslateX(iv1.getLayoutX() - WIDTH/2 + imc.getStartingX());
		//iv1.setTranslateY(iv1.getLayoutY() - HEIGHT/2 + imc.getStartingY());

        stage.show();
    }
    public void setX(double x) {
    	iv1.setTranslateX(iv1.getLayoutX() - WIDTH/2 + x);
    }
    public void setY(double y) {
    	iv1.setTranslateY(iv1.getLayoutY() - HEIGHT/2 + y);
    }
    public static void main(String[] args) {
        launch();
    }
    public TilePane getTilePane() {
    	return tP;
    }
    public ImageView getImageView() {
    	return iv1;
    }
    public ImageView createImageView(Image img) {
    	ImageView copy = new ImageView(img);
    	copy.setFitHeight(100);
    	copy.setPreserveRatio(true);
    	return copy;
    }

}