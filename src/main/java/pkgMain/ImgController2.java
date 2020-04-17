package pkgMain;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.TilePane;

public class ImgController2 {

	private final boolean DEBUG = true;
	MyMovingImageView2 view;
	MyMovingImageModel2 model;
	
	
	ImgController2(MyMovingImageView2 view){
		this.view = view;
		model = new MyMovingImageModel2();
		if (DEBUG) System.out.println("ic created");
	}
	
	public void drag(MouseEvent event) {
		Node n = (Node)event.getSource();
		//if (DEBUG) System.out.println("ic mouse drag tx: " + n.getTranslateX() + ", ex: " + event.getX() );
		model.setX(model.getX() + event.getX()); //event.getX() is the amount of horiz drag
		model.setY(model.getY() + event.getY());
		view.setX( model.getX() );
		view.setY( model.getY() );
		
		//n.setTranslateX(n.getTranslateX() + event.getX()); //not MVC! what problem does this create?
		//n.setTranslateY(n.getTranslateY() + event.getY());
	}

	public EventHandler getHandlerForDrag() {
		return event -> drag((MouseEvent) event);
	}
	
	
	
	

	public void  onMouseEntered(MouseEvent event) {
		Node n = (Node)event.getSource();
		if (DEBUG) System.out.println("TilePane Rolled OVer" );
		view.getTilePane().setStyle("-fx-background-color: darkgray;");
	}
	public EventHandler getHandlerForMouseEnter() {
		return event -> onMouseEntered((MouseEvent) event);
	}
/*	public void tellModelStartingCoords(double x, double y) {
		model.setX(view.getImgStartingX());
		model.setY(view.getImgStartingY());
		if (DEBUG) System.out.println("start: " + model.getX() + " " + model.getY());
	}
*/
	public double getStartingX() {
		return model.getX();
	}
	public double getStartingY() {
		return model.getY();
	}



	public void  onMouseExited(MouseEvent event) {
		Node n = (Node)event.getSource();
		if (DEBUG) System.out.println("TilePane Rolled OVer" );
		view.getTilePane().setStyle("-fx-background-color: blue;");
	}
	
	public EventHandler getHandlerForMouseExited() {
		return event -> onMouseExited((MouseEvent) event);
	}


	public void dragDetected(MouseEvent event) {
		Node n =(Node)event.getSource();
		if (DEBUG) System.out.println("Started to Drag" );
		
		Dragboard db = n.startDragAndDrop(TransferMode.ANY);
		
		ClipboardContent content = new ClipboardContent();
        content.putImage(view.getImageView().getImage());
        db.setContent(content);
		event.consume();
		
		
	}
	public EventHandler getHandlerForDragDetected() {
		return event -> dragDetected((MouseEvent) event);
	}

	public void dragOverDetected(DragEvent event) {
		Node n =(Node)event.getSource();
		Dragboard db = event.getDragboard();
		event.acceptTransferModes(TransferMode.MOVE);
		event.consume();
	}
	public EventHandler getHandlerForsetOnDragOver() {
		return event -> dragOverDetected((DragEvent) event);

	}
	
	public void dragDropped(DragEvent event) {
		Node n =(Node)event.getSource();
		Dragboard db = event.getDragboard();
		boolean success = false;
		if(db.hasImage()){
			view.getTilePane().getChildren().add(view.createImageView(db.getImage()));
			success = true;
		}
		event.setDropCompleted(success);
		event.consume();
	}

	public EventHandler getHandlerforsetOnDragDropped() {
		return event -> dragDropped((DragEvent) event);

	}


	
	
}
