import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
/**
 * Clase del menu principal
 * @author liter
 *
 */
public class Menu {

	/**
	 * Método para leer el archivo con las ciudades
	 * @throws IOException
	 */
    public void vista() throws IOException{

        ArrayList<Modelo> grafos = new ArrayList<>();
        File fileTXT = new File ("guategrafo.txt");
        FileReader reader = new FileReader(fileTXT);
        BufferedReader bfReader = new BufferedReader(reader);
        Scanner scanner = new Scanner(reader);
        String concat = "";

        
        Modelo graph = new Modelo(); 
        String origen, dest;
        int distancia;
        int cont = 0;


        Floyd floyd = new Floyd();

        ArrayList<String> ciudades = new ArrayList<>();
        long matrix[][]; 
        Scanner sc = new Scanner(System.in);
        int opcion;
        String opc;
        boolean salir = true;



        while (scanner.hasNextLine()) {
            concat = scanner.nextLine();
            
    
            origen = concat.substring(0, concat.indexOf(" "));
            concat = concat.substring(concat.indexOf(" ") + 1, concat.length());
            dest = concat.substring(0, concat.indexOf(" "));
            concat = concat.substring(concat.indexOf(" ") + 1, concat.length());  
            distancia = Integer.parseInt(concat.substring(0, concat.length()));
            cont++;
            
    
            grafos.add(new Modelo(origen, dest, distancia));
            reader.close();
            bfReader.close();
        }

        /**
         * Menú principal
         */
        while (salir) {
            System.out.println(" 1. Ver ruta más corta entre ciudades");
            System.out.println(" 2. Ver centro");
            System.out.println(" 3. Modificar grafo");
            System.out.println(" 4. Matriz de adyacencia");
            System.out.println(" 5. Salir");
            opcion = sc.nextInt();
            /**
             * Ver ruta entre dos ciudades
             */
            switch (opcion) {
                case 1: 
                    System.out.println("\n Ciudad de origen:");
                    String origen2 = sc.nextLine();
                    origen2 = sc.nextLine();
                    System.out.println("\n Ciudad de destino:");
                    String destino = sc.nextLine();

                    
                    ciudades.clear();
                    ciudades = graph.grafoArr(grafos);
                    matrix = floyd.createMatrix(ciudades, grafos);

                    
                    if(floyd.verificar(ciudades,origen2,destino)){    
                        System.out.println(floyd.floyd(matrix, ciudades, origen2, destino));
                        System.out.println("Presione 0 para continuar");
                        opc = sc.next();
                        
                    }else{
                        System.out.println("Dato no existente \n");
                        System.out.println("Presione 0 para continuar");
                        opc = sc.next();
                    }
                    break;
                case 2: 
                	/**
                	 * Ver el centro del grafo
                	 */
                    ciudades.clear();
                    ciudades = graph.grafoArr(grafos);
                    matrix = floyd.createMatrix(ciudades, grafos);
                    floyd.centro(matrix); 
                    System.out.println("Centro: " + ciudades.get(floyd.centro(matrix)) + "\n");
                    System.out.println("Presione 0 para continuar");
                    opc = sc.next();
                break;
                /**
                 * Agregar anomalías o nuevas conexiones
                 */
                case 3: 
                    String seleccion2 = "";
                    System.out.println("\t 1. Interrupción de tráfico");
                    System.out.println("\t 2. Nueva conexión");
                    seleccion2 = sc.next();
                    /**
                     * 
                     */
                    if("1".equals(seleccion2.toLowerCase())){
                        System.out.println("Ingresar ciudad de origen: ");
                        String ori = sc.next();
                        System.out.println("Ingresar ciudad de destino: ");
                        String dest2 = sc.next();
                        
                        boolean nit = false;
                        
                        for(Modelo c: grafos){
                            if((c.getOrigen().equals(ori)) && (c.getDestino().equals(dest2))){
                            	grafos.remove(c);
                                nit = true;
                                System.out.println("Ingrese 0 para continuar");
                                opc = sc.next();
                                break;
                            }
                        }
                        if(nit == false){
                            System.out.println("Ciudad ingresada inválida\n");
                            System.out.println("Presione 0 para continuar");
                            opc = sc.next();
                        }
                    }else if("2".equals(seleccion2.toLowerCase())){
                        System.out.println("Ciudad de origen:");
                        String ori = sc.next();
                        System.out.println("Ciudad de destino:");
                        String dest2 = sc.next();
                        System.out.println("Distancia: ");
                        int dist = sc.nextInt();
                        
                        grafos.add(new Modelo(ori, dest2, dist));
                        System.out.println("Datos agregados con éxito \n");
                        System.out.println("Presione 0 para continuar");
                        opc = sc.next();
                    }
                    break;
                case 4:  
                	/**
                	 * Ver matriz de adyacencia
                	 */
                    ciudades.clear();
                    ciudades = graph.grafoArr(grafos);
                    matrix = floyd.createMatrix(ciudades, grafos);
                    System.out.println("Matriz de adyacencia");
                    for(String s: ciudades){
                        System.out.print(s + " ");
                    }
                    System.out.println("");
                    System.out.println(floyd.mostrarMatriz(matrix));
                    System.out.println("\n");
                    System.out.println("Presione 0 para continuar");
                    opc = sc.next();
                break;
                case 5:
                    salir = false;
                default:
                    break;
            }
        }
    }
}