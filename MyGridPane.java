package esamegiugnoprog2;
import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.scene.layout.GridPane;

public class MyGridPane extends GridPane {
    private int size;
    private final Casella [][] matrix;
    private ArrayList<Casella> array; 
    
    public MyGridPane(int size) {
        this.size = size;
        matrix = new Casella[size][size];
        array = new ArrayList<>();
        fillMatrix();
        //aggiungo tutti i figli
        this.getChildren().addAll(array);
        //ridimensionamento liquido
        NumberBinding areaSize = Bindings.min(this.widthProperty(), this.heightProperty());
        
    }
    
    private void fillMatrix() {
        for (int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                matrix[i][j] = new Casella();
                array.add(matrix[i][j]);
                int spacing = 14;
                MyGridPane.setConstraints(matrix[i][j],j,i); 
                matrix[i][j].rect.widthProperty().bind(EsameGiugnoProg2.widthBinding.divide(spacing));
                matrix[i][j].rect.heightProperty().bind(EsameGiugnoProg2.heightBinding.divide(spacing)); 
                matrix[i][j].circle.radiusXProperty().bind(EsameGiugnoProg2.widthBinding.divide(spacing*2));
                matrix[i][j].circle.radiusYProperty().bind(EsameGiugnoProg2.heightBinding.divide(spacing*2)); 
            }
    }
    
    public boolean[][] isFilled(){
        boolean [][] tmp = new boolean[size][size];
        for (int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                if(matrix[i][j].isFilled)
                    tmp[i][j] = true;
                else
                    tmp[i][j]= false;
        return tmp;
    }
    
    public void Clear() {
        for (int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                matrix[i][j].unfillCircle();
            }
        
        
    }
    
    
    public void Alg1() {
        boolean [][] tmp = new boolean[size][size];
        for (int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                if(i!=0 && j!=(size-1))
                    tmp[i-1][j+1] = matrix[i][j].isFilled;
                else if(i== 0 && j==(size-1))
                    tmp[size-1][0] = matrix[i][j].isFilled;
                else if(i==0)
                    tmp[size-1][j+1] = matrix[i][j].isFilled;
                else if(j==(size-1))
                    tmp[i-1][0] = matrix[i][j].isFilled;
            }
        //ricreo matrice
        for (int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                if(tmp[i][j])
                    matrix[i][j].fillCircle();
                else
                    matrix[i][j].unfillCircle();
            }
    }
    
    
    public void Alg2() {
        boolean [][] tmp = new boolean[size][size];
        for (int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                if(j!=0)
                    tmp[i][j-1] = matrix[i][j].isFilled;
                else
                    tmp[i][size-1] = matrix[i][j].isFilled;
                    
            }
        //ricreo matrice
        for (int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                if(tmp[i][j])
                    matrix[i][j].fillCircle();
                else
                    matrix[i][j].unfillCircle();
            }
    }
    
    public void Alg3() {
        boolean [][] tmp = new boolean[size][size];
        for (int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                if(i==(size-1))
                    tmp[0][j] = matrix[i][j].isFilled;
                else
                    tmp[i+1][j] = matrix[i][j].isFilled;
                    
            }
        //ricreo matrice
        for (int i = 0; i < size; i++)
            for(int j = 0; j < size; j++) {
                if(tmp[i][j])
                    matrix[i][j].fillCircle();
                else
                    matrix[i][j].unfillCircle();
            }
    }
    
    public int getSize() { return size;}
}
