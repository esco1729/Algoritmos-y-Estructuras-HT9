import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
	}
	
    public ArrayList<Modelo> readTXTFile() throws FileNotFoundException, IOException{
        ArrayList<Modelo> grafo = new ArrayList<>();
        File txtFile = new File ("guategrafo.txt");
        FileReader reader = new FileReader(txtFile);
        BufferedReader bfReader = new BufferedReader(reader);
        String row = "";
        Scanner sc = new Scanner(reader);
        
        String org;
        String des;
        int dis;
        int cont = 0;
        
        while (sc.hasNextLine()) {
            row = sc.nextLine();        
            org = row.substring(0, row.indexOf(" "));
            row = row.substring(row.indexOf(" ") + 1, row.length());      
            des = row.substring(0, row.indexOf(" "));
            row = row.substring(row.indexOf(" ") + 1, row.length());     
            dis = Integer.parseInt(row.substring(0, row.length()));   
            cont++;
            grafo.add(new Modelo(org, des, dis));
            reader.close();
            bfReader.close();
        }
        return grafo;
    }
}
