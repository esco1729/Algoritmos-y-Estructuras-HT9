import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class FloydTest {
	@Test
	void centro() throws FileNotFoundException, IOException {
        Floyd floyd = new Floyd();
        Modelo mod = new Modelo();
        ArrayList<Modelo> ciudad = floyd.leerArchivo();
        ArrayList<String> modelos = mod.grafoArr(ciudad);
        long[][] matrix = floyd.createMatrix(modelos, ciudad);
      
        //Ver cual es el centro
        int res = modelos.indexOf("Mixco");
        int result = floyd.centro(matrix);
        assertEquals(res, result);
	}
	
	@Test
	void floyd() throws FileNotFoundException, IOException {
        Floyd floyd = new Floyd();
        Modelo mod = new Modelo();
        ArrayList<Modelo> modelo = floyd.leerArchivo();
        ArrayList<String> grafo = mod.grafoArr(modelo);
        long[][] matrix = floyd.createMatrix(grafo, modelo);
        String origen = " Mixco ";
        String destino = " Antigua ";
        
        String ruta = "De Mixco hacia Antigua debe tomar la ruta desde Mixco";
        String result = floyd.floyd(matrix, grafo, origen, destino);
        System.out.print(result);
        
	}
}
