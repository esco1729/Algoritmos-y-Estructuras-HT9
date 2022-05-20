import java.util.ArrayList;

/**
 * Clase con características del grafo
 * @author liter
 *
 */
public class Modelo {
	String origen, destino;
	int dist;

	public Modelo() {
		
	}
	
	/**
	 * 
	 * @param origen
	 * @param destino
	 * @param dist
	 */
	public Modelo(String origen, String destino, int dist) {
		this.origen = origen;
		this.destino = destino;
		this.dist = dist;
	}
	
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	
	public String getOrigen() {
		return origen;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDis(int dist) {
		this.dist = dist;
	}
	
	public int getDis() {
		return dist;
	}
	
	
    public ArrayList<String> grafoArr(ArrayList<Modelo> graph){
        ArrayList<String> grafoArr = new ArrayList<>();
        for(Modelo modelo: graph){
            if(!grafoArr.contains(modelo.getOrigen())){
            	grafoArr.add(modelo.getOrigen());
            }
            if(!grafoArr.contains(modelo.getDestino())){
            	grafoArr.add(modelo.getDestino());
            }
        }
        return grafoArr;
    }
}
