package esamegiugnoprog2;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class Casella extends StackPane{
    boolean isFilled;
    public Rectangle rect;
    public Ellipse circle;
    
    public Casella(boolean fill) {
        isFilled = fill;
        //dichiaro le dimensioni
        this.setHeight(50);
        this.setWidth(50);
        //rettangolo
        rect = new Rectangle(50.,50.);
        rect.setFill(Color.WHITE);  //colore interno
        rect.setStroke(Color.BLACK);//colore bordo
        this.getChildren().add(rect);

        //cerchio
        circle = new Ellipse(rect.getWidth()/2,rect.getHeight()/2);
        circle.setFill(Color.RED);
        if(isFilled)
            circle.setOpacity(1.);
        else
            circle.setOpacity(0.);
        this.getChildren().add(circle);
        
        ListenerCircle listener = new ListenerCircle();
        this.setOnMouseClicked(listener);
    }
    
    public Casella() {
        this(false);
    }
    
    public void fillCircle() {
        isFilled = true;
        circle.setOpacity(1.);
    }
    
    public void unfillCircle() {
        isFilled = false;
        circle.setOpacity(0.);
    }
    
    
    class ListenerCircle implements EventHandler {

        @Override
        public void handle(Event t) {
            if(isFilled)
                unfillCircle();
            else
                fillCircle();
        }
    }
    
}
