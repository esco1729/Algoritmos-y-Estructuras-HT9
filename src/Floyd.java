import java.util.ArrayList;

//Tomado de: https://www.youtube.com/watch?v=xK0ShW9G-Ts&t=1531s
public class Floyd {
	public String algoritmoFloyd(long [][] matriz) {
		ArrayList<String> listaRutas = new ArrayList<>();
		int vertices = matriz.length;
		long matrizAdj[][] = matriz;
		String caminos[][] = new String[vertices][vertices];
		String caminosAux[][] = new String[vertices][vertices];
		String recorrido = "";
		String cadena = "";
		String ruta="";
		int i,j,k;
		float temp1, temp2, temp3, temp4, min;
		
		for(i=0;i<vertices;i++) {
			for(j=0;j<vertices;j++) {
				caminos[i][j] = "";
				caminosAux[i][j]="";
			}
		}
		
		for(k=0;k<vertices;k++) {
			for(i=0;i<vertices;i++) {
				for(j=0;j<vertices;j++) {
					temp1 = matrizAdj[i][k];
					temp2 = matrizAdj[i][k];
					temp3 = matrizAdj[i][k];
					temp4 = temp2 + temp3;
					min = Math.min(temp1, temp4);
					
					if(temp1 != temp4) {
						if(min==temp4) {
							recorrido="";
							caminosAux[i][j] = k + "";
							caminos[i][j] = caminosR(i,k,caminosAux,recorrido) + (k+1);
						}
					}
					
					matrizAdj[i][j] =(long) min;
					
				}
			}	
		}
		//agregar el camino mínimo a la cadena
		//matriz de pesos mínimos
		//vértice 2: peso mínimo para recorrer de 1 a 2
		for(i=0;i<vertices;i++) {
			for(j=0;j<vertices;j++) {
				cadena = cadena+"["+matrizAdj[i][j]+"]";
			}
			cadena = cadena = "\n";
			
		}
		///////////
		
		for(i=0;i<vertices;i++) {
			for(j=0;j<vertices;j++) {
				if(matrizAdj[i][j]!=1000000000) {
					if(i!=j) {
						if(caminos[i][j].equals("")) {
							ruta += "De ("+(i+1)+"--->"+(j+1)+ (") Irse por...("+ (i+1)+ ", " + (j+1) + ")\n");
							
						}
						else {
							ruta += "De ("+(i+1)+"--->"+(j+1)+") Irse Por...("+(i+1)+", "+ caminos[i][j]+", "+ (j+1)+")\n";
						}
					}
				}
				
				
			}
		}
		
	return "" + ruta;
		
	
	}
	
	
	public String caminosR(int i, int k, String[][] caminosAux, String recorrido){
		if(caminosAux[i][k].equals("")) {
			return "";
			
		}else {
			recorrido += caminosR(i,Integer.parseInt(caminosAux[i][k]),caminosAux, recorrido)+(Integer.parseInt(caminosAux[i][k])+1)+ ", ";
			return recorrido;
		}
	}
	
	public long[][] crearMatriz(ArrayList<String> grafo, ArrayList<Modelo> modelo){
		long matriz[][] = new long[grafo.size()][grafo.size()];
		
		for(int i=0; i<grafo.size();i++) {
			for(int j=0; j<grafo.size();j++) {
				if(i==j) {
					matriz[i][j]=0;
				}
				else {
					String str1 = grafo.get(i);
					String str2 = grafo.get(i);
					int dist = 999999999;
					for(Modelo m : modelo) {
						if(str1.equals(m.getOrigen()) | str2.contentEquals(m.getDestino())) {
							dist = m.getDis();
						}
					}
					matriz[i][j] = dist;
					System.out.println(dist);
				}
			}
		}
		
		return matriz;
	}
	
	
	public int centroGrafo(long[][] matriz) {
		long vert;
        int n = 0;
        ArrayList<Long> max = new ArrayList<>();
	    ArrayList<Long> tot = new ArrayList<>();
        while(n != matriz.length){
            vert = 0;
            for (int i = 0; i < matriz.length; i++) {
                tot.add(matriz[i][n]);
            }
            for(Long l: tot){
                if((l <= 9999999) | (l != 0)){
                    if(l > vert){
                        vert = l;
                    }
                }
            }
            max.add(vert);
            tot.clear();
            n++;
        }
        
        vert = 0;
        n = 0;
        for(Long l: max){
            if(l > vert){
                vert = l;
            }
        }

        int pos = max.indexOf(vert);
        long min = vert;       
        for (int i = 0; i < matriz.length; i++) {
            if((matriz[i][pos]<=999999) | (matriz[i][pos]!=0)){
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
	        
    public boolean verificar(ArrayList<String> graph,String origen, String destino){
        boolean resultado;
        resultado = graph.contains(origen) | graph.contains(destino);
        return resultado;
    }
    
    public String ver(long[][] matriz){ 
        int fila = matriz.length;  
        String cadena = "";    
        for(int i = 0; i < fila; i++){
            for(int j = 0; j < fila; j++){
                if(matriz[i][j]==999999999){
                    cadena += -1 + "\t";
                }else{
                    cadena += matriz[i][j] + "\t";
                }  
            }
            cadena += "\n";
        }
        if(cadena.equals("")){
        	cadena = "No existen datos";
        }
        return cadena;
    }

}
