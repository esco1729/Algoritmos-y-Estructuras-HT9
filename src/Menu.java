import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {
		
	}
	
	
	
    public static int displaymenu(){

        int opcc = 0;

        System.out.println("Menú principal: \n" +
                "1. Obtener ruta más corta\n" +
                "2. Obtener centro\n" +
                "3. Modificar\n" +
                "4. Salir");

        while(opcc > 4 || opcc < 1){ 
            try{
                System.out.printf("");
                Scanner scan = new Scanner(System.in);
                opcc = scan.nextInt();
            } catch (Exception e) { }
        }

        return opcc;
    }


    public static int modificaciones(){
        int opcc = 0;

        System.out.println("Modificaciones: \n" +
                "1. Reportar ruta cerrada \n" +
                "2. Agregar ruta entre ciudades");

        while(opcc > 2 || opcc < 1){ //Menu
            try{
                System.out.println("Seleccione una opción");
                Scanner scan = new Scanner(System.in);
                opcc = scan.nextInt();
            } catch (Exception e) { }
        }
        return opcc;
    }

    public static ArrayList<String> origenDestino(Modelo g){

        ArrayList<String> lista =  new ArrayList<String>();

        String origen = "";
        String destino = "";
        long matriz[][];

        do{
            Scanner scan = new Scanner(System.in);
            System.out.println("Ingrese el nombre del origen");
            origen = scan.nextLine();
            if (g.getOrigen() == origen){
                lista.add(origen);
                break;
            }
            System.out.println("Ingrese una ciudad válida");
        } while (true);

        do{
            Scanner scan = new Scanner(System.in);
            System.out.println("Ingrese el nombre del destino");
            destino = scan.nextLine();
            if (g.getDestino() == destino){
                lista.add(destino);
                break;
            }
            System.out.println("Ingrese una ciudad válida");
        } while (true);
        
        return lista;
    }

    public static int recorrido(){
        int recorrido = 0;

        System.out.println("Ingrese la distancia");
        while (true){
            try{
                Scanner scan = new Scanner(System.in);
                recorrido = scan.nextInt();
                if (recorrido > 0 && recorrido < 400) {
                    break;
                } else {
                    System.out.println("Ingrese un número válido");
                }
            } catch (Exception e ) {
                System.out.println("Inténtelo de nuevo");}
        }
        return recorrido;
    }
}