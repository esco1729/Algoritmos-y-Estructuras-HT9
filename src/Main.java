import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author liter
 *
 */
public class Main{
    public static void main(String[] args) throws IOException{
        Menu menu = new Menu();
        menu.vista();
    }
    
    
    public ArrayList<Modelo> read() throws FileNotFoundException, IOException{
        ArrayList<Modelo> grafo = new ArrayList<>();
        File txtFile = new File ("guategrafo.txt");
        FileReader reader = new FileReader(txtFile);
        BufferedReader bfReader = new BufferedReader(reader);
        String fila = "";
        Scanner sc = new Scanner(reader);
        
        String origen;
        String des;
        int dist;
        int cont = 0;
        
        while (sc.hasNextLine()) {
            fila = sc.nextLine();        
            origen = fila.substring(0, fila.indexOf(" "));
            fila = fila.substring(fila.indexOf(" ") + 1, fila.length());      
            des = fila.substring(0, fila.indexOf(" "));
            fila = fila.substring(fila.indexOf(" ") + 1, fila.length());     
            dist = Integer.parseInt(fila.substring(0, fila.length()));   
            cont++;
            grafo.add(new Modelo(origen, des, dist));
            reader.close();
            bfReader.close();
        }
        return grafo;
    }
    
    
    
    
    
}
	


