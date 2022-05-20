import java.io.BufferedReader;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author liter
 *
 */
public class Floyd {
    public String floyd(long[][] matrix, ArrayList<String> grafo, String origen, String destino){
        int vertice = matrix.length;
        long adjMatrix[][] = matrix;
        
        String rutas[][] = new String[vertice][vertice];
        String auxrutas[][] = new String[vertice][vertice];
        String path = "";
        String cadena = ""; 
        String ruta = "";
        
        int i, j, k;
        float temp1, temp2, temp3, temp4, min;
        int str2 = grafo.indexOf(origen);
        int dest = grafo.indexOf(destino);
        
        for(i = 0; i < vertice; i++){
            for(j = 0; j < vertice; j++){
                rutas[i][j] = "";
                auxrutas[i][j] = "";
            }
        }
        
        for (k = 0; k < vertice; k++) {
            for (i = 0; i < vertice; i++) {
                for (j = 0; j < vertice; j++) {
                    temp1 = adjMatrix[i][j];
                    temp2 = adjMatrix[i][k];
                    temp3 = adjMatrix[k][j];
                    temp4 = temp2 + temp3;
                    
                    min = Math.min(temp1, temp4);
                    if(temp1 != temp4){
                        if(min == temp4){
                            path = "";
                            auxrutas[i][j] = k + "";
                            rutas[i][j] = totalRec(i, k, auxrutas, path) + (k + 1);                            
                        }
                    }
                    adjMatrix[i][j] = (long) min;
                }
            }
        }
        
        ArrayList<Integer> todasRutas = new ArrayList<>();
        

        for (i = 0; i < vertice; i++) {
            for (j = 0; j < vertice; j++) {
                if(adjMatrix[i][j] != 1000000000){
                    if(i != j){
                        if(rutas[i][j].equals("") && (i == str2) && (j == dest)){
                            ruta += "De " + grafo.get(i) + " hacia " + grafo.get(j) + " debe tomar la ruta desde " + grafo.get(i);
                        }else if(!rutas[i][j].equals("") && (j == dest) && (i == str2)){
                            String rut = rutas[i][j];
                            if(!rut.contains(",")){
                                todasRutas.add(Integer.parseInt(rut));
                            }
                            while(rut.contains(",")){
                                String walk = rut.substring(0, rut.indexOf(","));
                                rut = rut.substring(rut.indexOf(",") + 2);
                                todasRutas.add(Integer.parseInt(walk));
                                if(!rut.contains(",")){
                                    todasRutas.add(Integer.parseInt(rut));
                                }
                            }
                            
                            String centro = "";
                            for(Integer in: todasRutas){
                                
                                centro += grafo.get(in - 1) + ", ";
                            }
                            ruta += "De " + grafo.get(i) + "hacia " + grafo.get(j) + "debe tomar la ruta desde " + grafo.get(i) + "(centro: " + centro + ")";
                        }
                    }
                }
            }
        }
        return "\n" + ruta;
    }
     /**
      * Recorrido
      * @param i
      * @param k
      * @param caminito
      * @param recorrido
      * @return
      */
    public String totalRec(int i, int k, String[][] caminito, String recorrido){
        if(caminito[i][k].equals("")){
            return "";
        }else{
            recorrido += totalRec(i, Integer.parseInt(caminito[i][k]), caminito, recorrido) + (Integer.parseInt(caminito[i][k]) + 1) + ", ";
            return recorrido;
        }
    }
    /**
     * Método para crear matriz
     * @param migrafo
     * @param model
     * @return
     */
    public long[][] createMatrix(ArrayList<String> migrafo, ArrayList<Modelo> model){
        long matriz[][] = new long[migrafo.size()][migrafo.size()];
        
        for (int i = 0; i < migrafo.size(); i++) {
            for (int j = 0; j < migrafo.size(); j++) {
                if(i == j){
                    matriz[i][j] = 0;
                }else{
                    String o = migrafo.get(i);
                    String d = migrafo.get(j);
                    int distance = 999999999;
                    for(Modelo c: model){
                        if(o.equals(c.getOrigen()) && d.equals(c.getDestino())){
                            distance = c.getDis();
                        }
                    }
                    matriz[i][j] = distance;
                }
            }
        }
        return matriz;
    }
    /**
     * Método para buscar el centro
     * @param matriz
     * @return
     */
    public int centro(long[][] matriz){
        ArrayList<Long> maximum = new ArrayList<>();
        ArrayList<Long> sum = new ArrayList<>();
        long max;
        int count = 0;
        
        while(count != matriz.length){
            max = 0;
            for (int i = 0; i < matriz.length; i++) {
                sum.add(matriz[i][count]);
            }
            for(Long l: sum){
                if((l <= 9999999) && (l != 0)){
                    if(l > max){
                        max = l;
                    }
                }
            }
            maximum.add(max);
            sum.clear();
            count++;
        }
        
        max = 0;
        count = 0;
        for(Long l: maximum){
            if(l > max){
                max = l;
            }
        }

        int pos = maximum.indexOf(max);
        long min = max;       
        for (int i = 0; i < matriz.length; i++) {
            if((matriz[i][pos]<=999999) && (matriz[i][pos]!=0)){
                if(matriz[i][pos] < min){
                    min = matriz[i][pos];
                }
            }
        }
        
        int centro = 0;
        for (int i = 0; i < matriz.length; i++) {
            if(matriz[i][pos] == min){
                centro = i;
                break;
            }
        }
        
        return centro;
    }
    /**
     * Lectura del archivo con ciudades
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ArrayList<Modelo> leerArchivo() throws FileNotFoundException, IOException{
        ArrayList<Modelo> grafo = new ArrayList<>();
        File txtFile = new File ("guategrafo.txt");
        FileReader reader = new FileReader(txtFile);
        BufferedReader bfReader = new BufferedReader(reader);
        String fila = "";
        Scanner sc = new Scanner(reader);
        
        String str2;
        String des;
        int dis;
        int cont = 0;
        
        while (sc.hasNextLine()) {
            fila = sc.nextLine();        
            str2 = fila.substring(0, fila.indexOf(" "));
            fila = fila.substring(fila.indexOf(" ") + 1, fila.length());      
            des = fila.substring(0, fila.indexOf(" "));
            fila = fila.substring(fila.indexOf(" ") + 1, fila.length());     
            dis = Integer.parseInt(fila.substring(0, fila.length()));   
            cont++;
            grafo.add(new Modelo(str2, des, dis));
            reader.close();
            bfReader.close();
        }
        return grafo;
    }
    /**
     * Verificar contenido del grafo 
     * @param grafo
     * @param origen
     * @param destino
     * @return
     */
    public boolean verificar(ArrayList<String> grafo,String origen, String destino){
        boolean result;
        result = grafo.contains(origen) && grafo.contains(destino);
        return result;
    }
    /**
     * Mostrar matriz
     * @param matrix
     * @return
     */
    public String mostrarMatriz(long[][] matrix){ 
        int fila = matrix.length;  
        String cadena = "";    
        for(int x = 0; x < fila; x++){
            for(int y = 0; y < fila; y++){
                if(matrix[x][y]==999999999){
                    cadena += -1 + "\t";
                }else{
                    cadena += matrix[x][y] + "\t";
                }  
            }
            cadena += "\n";
        }
        if(cadena.equals("")){
            cadena = "No hay datos disponibles";
        }
        return cadena;
    }
}
