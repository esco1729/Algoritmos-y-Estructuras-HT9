
abstract public class GraphMatrix<V, E>  implements Graph<V,E>{
	public void add(V label)
	// pre: label is a non-null label for vertex
	// post: a vertex with label is added to graph;
	// if vertex with label is already in graph, no action
	{
	// if there already, do nothing
	if (dict.containsKey(label)) return;
	
	// allocate a free row and column
	int row = freeList.removeFirst().intValue();
	// add vertex to dictionary
	dict.put(label, new GraphMatrixVertex<V>(label, row));
	}
	
	public 
	

}
