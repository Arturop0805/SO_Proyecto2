/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDeDatos;

/**
 *
 * @author Arturo
 */
public class ListaEnlazada<T> {
    
    private Nodo<T> inicio;
    private int tamaño;
    
    public ListaEnlazada() {
        this.inicio = null;
        this.tamaño = 0;
    }
    
    public Boolean EstaVacia() {
        if (this.inicio == null) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public Integer Tamaño() {
        return this.tamaño;
    }
    
    public void Insertar(T dato) {
        
        Nodo<T> NuevoNodo = new Nodo<>();
        NuevoNodo.setDato(dato);
        
        if (this.EstaVacia()) {
            
            this.inicio = NuevoNodo;
            
            
        } else {
           
             Nodo<T> auxiliar = this.inicio;
            
            while (auxiliar.getSiguiente() != null) {
                auxiliar = auxiliar.getSiguiente();
            }
            
            auxiliar.setSiguiente(NuevoNodo);
        }
        
        
        
      
        this.tamaño++;
        this.ActualizarIndices();
    }
    
    
    public void print() {
        
        Nodo<T> auxiliar = this.inicio;
        
        while (auxiliar != null) {
            System.out.println("valor: " + auxiliar.getDato() + " indice: " + auxiliar.getIndice());
            auxiliar = auxiliar.getSiguiente();
        }
     
        
    }
    
    public void InsertarAlInicio(T dato) {
        Nodo<T> NuevoNodo = new Nodo<>();
        NuevoNodo.setDato(dato);
        
        NuevoNodo.setSiguiente(this.inicio);
        this.inicio = NuevoNodo;
        tamaño++;
        this.ActualizarIndices();
        
  
    }
    
    
    public void InsertarPorIndice(int indice, T dato) {
          
        Nodo<T> NuevoNodo = new Nodo<>();
        NuevoNodo.setDato(dato);
        
        Nodo<T> auxiliar = this.inicio;
        
        
       
        
        if (indice > this.tamaño) {
            System.out.println("Indice mayor al tamaño de la lista");
            return;
        }
        
        
        if (indice == 0) {
            this.InsertarAlInicio(dato);
            return;
        }
        
        if (indice == tamaño) {
            this.Insertar(dato);{
            return;
        }
        }
        
        while (auxiliar.getSiguiente().getIndice() != indice) {
       
               auxiliar = auxiliar.getSiguiente();
               
        }
        
        
        if (auxiliar.getSiguiente().getIndice() == indice) {
            NuevoNodo.setSiguiente(auxiliar.getSiguiente());
            auxiliar.setSiguiente(NuevoNodo);

        }
          
        
        this.ActualizarIndices();
        tamaño++;
      }
    
    
    private void ActualizarIndices(){
        int contador = 0;
        Nodo<T> auxiliar = this.inicio;
        
        
        while (auxiliar != null) {
            auxiliar.setIndice(contador);
            auxiliar = auxiliar.getSiguiente();
            contador++;
        }
    }
    
    
   public boolean eliminar(T dato) {
    if (this.EstaVacia()) {
        System.out.println("esta vacia");
        return false;
    }
    
    // Caso especial: eliminar la cabeza
    if (this.inicio.getDato().equals(dato)) {
        this.inicio = this.inicio.getSiguiente();
        System.out.println("eliminado: " + dato);
        this.ActualizarIndices(); 
        return true;
    }
    
    Nodo<T> auxiliar = this.inicio;
    
    while (auxiliar.getSiguiente() != null) {
        if (auxiliar.getSiguiente().getDato().equals(dato)) {
            
           
            
            auxiliar.setSiguiente(auxiliar.getSiguiente().getSiguiente());
            System.out.println("eliminado: " + dato);
            this.ActualizarIndices();  
            return true;
        } else {
            auxiliar = auxiliar.getSiguiente();
        }
    }
    
    // Si no se encontró el elemento
    System.out.println("elemento no encontrado");
    return false;
}

    
   
   
   public void buscar(T dato) {
       Nodo<T> auxiliar = this.inicio;
       
       while (auxiliar.getSiguiente() != null) {
           if (auxiliar.getSiguiente().getDato() == dato) {
               System.out.println("se encontro: "+ auxiliar.getSiguiente().getDato() + "indice: " + auxiliar.getSiguiente().getIndice());
           }
           auxiliar = auxiliar.getSiguiente();
       }
   }
   
   public Nodo<T> buscarPorIndice(int indice) {
       
       if (indice < 0 || indice >= this.tamaño) {
        return null; // Índice fuera de límites
        }
       
       Nodo auxiliar = this.inicio;
       
       while (auxiliar != null) {
           if (auxiliar.getIndice() == indice){
               return auxiliar;
           }
           auxiliar = auxiliar.getSiguiente();
       }
       
       
       return null;
   }
   
   /**
 * Diagnóstico: Muestra todos los nodos e índices en la lista.
 */
    public void diagnosticoIndices() {
        System.out.println("\n--- DIAGNÓSTICO DE ÍNDICES DE LA LISTA ENLAZADA ---");
        Nodo<T> auxiliar = this.inicio;
        while (auxiliar != null) {
            // Asumiendo que T es Bloque, obtendremos el ID del bloque
            String datoStr = auxiliar.getDato() != null ? auxiliar.getDato().toString() : "NULL";

            System.out.println(String.format("Índice de Nodo: %02d | Contenido: %s", 
                                              auxiliar.getIndice(), datoStr));
            auxiliar = auxiliar.getSiguiente();
        }
        System.out.println("-------------------------------------------------");
    }
    
    public Nodo<T> getInicio() {
        return this.inicio;
    }

    /**
     * Permite establecer el nodo inicial de la lista.
     * Útil si se necesita reemplazar la cabeza de la lista directamente.
     * @param inicio El nuevo nodo de inicio.
     */
    public void setInicio(Nodo<T> inicio) {
        this.inicio = inicio;
    }
    
    public T ExtraerInicio() {
    if (this.EstaVacia()) {
        return null;
    }
    
    // 1. Guardar el dato a devolver
    T datoExtraido = this.inicio.getDato();
    
    // 2. Mover la cabeza al siguiente nodo
    this.inicio = this.inicio.getSiguiente();
    
    // 3. Reducir tamaño y actualizar índices
    this.tamaño--;
    
    // Si usas el método ActualizarIndices, llámalo aquí:
    // this.ActualizarIndices();
    
    // Si usas la asignación de índice en Insertar (recomendado), no necesitas ActualizarIndices
    
    return datoExtraido;
    }
    
}

    