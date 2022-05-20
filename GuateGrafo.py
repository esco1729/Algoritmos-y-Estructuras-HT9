import networkx as nx
graph = nx.DiGraph()

with open("guategrafo.txt") as file:
    for x in file:
        lista = x.split(" ")
        if(lista[0] not in graph.nodes):
            graph.add_node(lista[0])
        if(lista[1] not in graph.nodes):
            graph.add_node(lista[1])
        graph.add_weighted_edges_from([(lista[0],lista[1],int(lista[2]))])


while True:
    print("1. Ruta\n")
    print("2. Centro\n")
    print("3. Modificar\n")
    print("4. Salir\n")
    opcion = input("Por favor ingrese una opcion: ")
    if(opcion == "1"):
        origen = input("Origen: ")
        destino = input("Destino: ")
        try:
            print("Distancia: "+str(nx.floyd_warshall(graph)[origen][destino]))
            print("Ruta: "+str(nx.shortest_path(graph,source=origen,target=destino, weight='weight')))
        except:
            print("Datos inválidos")
    elif(opcion == "2"):
        try:
            print("Centro: "+nx.center(graph))   
        except:
            print("Inténtelo de nuevo") 
        
    elif(opcion == "3"):
        print("1. Añadir ruta con tráfico\n")
        print("2. Nueva conexión\n")
        opc2 = input("")
        or1 = input("Ciudad de origen: ")
        des1 = input("Ciudad de destino: ")
        if(opc2 == "1"):
            try:
                graph.remove_edge(or1,des1)
            except:
                print("Inténtelo de nuevo")
        elif(opc2 == "2"):
            km = input("Distancia: ")
            try:
                graph.add_weighted_edges_from([(or1,des1,int(km))])
            except:
                print("Datos inválidos")
        else:
            print("Inténtelo nuevamente")
    elif(opcion == "4"):
        break
    else:
        print("Inténtelo nuevamente")
        
        
     