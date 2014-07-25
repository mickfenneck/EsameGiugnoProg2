package esamegiugnoprog2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.NumberBinding;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EsameGiugnoProg2 extends Application {
    
    
    //genero javadoc
    
    
    ToggleButton alg1 = new ToggleButton("ALG1");
    ToggleButton alg2 = new ToggleButton("ALG2");
    ToggleButton alg3 = new ToggleButton("ALG3");
   
    Button print = new Button("print");
    Button clear = new Button("clear");
    Button step = new Button("step");
    Button start = new Button("start");
    Button stop = new Button("stop");
    
    MyGridPane scacchiera;
    
    HBox hbTop = new HBox();
    HBox hbBottom = new HBox();
    
    //ridimensionamento liquido
    static NumberBinding widthBinding;
    static NumberBinding heightBinding;
     
    @Override
    public void start(Stage primaryStage) {
     
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 600);
        
        //setto default alg1
        alg1.setSelected(true);
        
        //ridimensionamento liquido
        widthBinding = root.widthProperty().subtract(10);
        heightBinding = root.heightProperty().subtract(10);        
        
        hbTop.getChildren().addAll(alg1,alg2,alg3);
        hbBottom.getChildren().addAll(print,clear,step,start,stop);
        //ordinamento
        root.setTop(hbTop);
        hbTop.setAlignment(Pos.CENTER);
        hbTop.setSpacing(20);
        root.setBottom(hbBottom);
        hbBottom.setAlignment(Pos.CENTER);
        hbBottom.setSpacing(20);
        
        scacchiera = new MyGridPane(10);
        scacchiera.setAlignment(Pos.CENTER);
        scacchiera.setHgap(3);
        scacchiera.setVgap(3);
        root.setCenter(scacchiera);
        
        ListenerTasti listKey = new ListenerTasti();
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, listKey);
        
        
        final Timeline tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        KeyFrame move = 
            new KeyFrame(Duration.seconds(1),
            new EventHandler<ActionEvent>() {
                public void handle(ActionEvent event) {
                    stepFun();
                }
        });

        tl.getKeyFrames().add(move);

        //evento print
        print.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                Stage printStage = new Stage();
                StackPane pannello = new StackPane();
                Scene scene = new Scene(pannello, 400, 350);
                
                Label lblText = new Label(stampaMatriceAdiacenza());
                pannello.getChildren().add(lblText);   
        
                printStage.setTitle("Matrice stampata");
                printStage.setScene(scene);
                printStage.show(); 
            }
        });  
        
        clear.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                scacchiera.Clear();
            }
        });
        
        step.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                stepFun();
            }
        });
        
        alg1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                alg1.setSelected(true);
                alg2.setSelected(false);
                alg3.setSelected(false);
            }
        });
        
        alg2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                alg1.setSelected(false);
                alg2.setSelected(true);
                alg3.setSelected(false);
            }
        });
        
        alg3.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                alg1.setSelected(false);
                alg2.setSelected(false);
                alg3.setSelected(true);
            }
        });
        
        start.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                tl.play();
            }
        });
        
        stop.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent t) {
                tl.pause();
            }
        });
  
        primaryStage.setTitle("Esame Giugno 2014");
        primaryStage.setScene(scene);
        primaryStage.show();
    }  
    
    public String stampaMatriceAdiacenza() {
        String ret = "";
        boolean[][] filled = scacchiera.isFilled();
        for (int i = 0; i < filled.length; i++) {
            for(int j = 0; j < filled.length; j++)
                if(filled[i][j])
                    ret += "1";
                else
                    ret += "0";
            ret += "\n";
        }
        return ret;
    }
    
    public void stepFun() {
        if(alg1.isSelected())
            scacchiera.Alg1();
        else if(alg2.isSelected())
            scacchiera.Alg2();
        else if(alg3.isSelected())
            scacchiera.Alg3();
    }
    
    
    class ListenerTasti implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent t) {
            KeyCode tasto = t.getCode();
            if(tasto.equals(KeyCode.C))
                scacchiera.Clear();
            else if (tasto.equals(KeyCode.S))
                stepFun();       
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

