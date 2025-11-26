/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author yange
 */


import EstructurasDeDatos.ListaEnlazada;

/**
 * Representa una entrada en el directorio (ya sea un archivo o un subdirectorio).
 * Actúa como nodo en la estructura jerárquica del sistema de archivos.
 * NOTA: Esta clase NO extiende JFrame, manteniendo la separación Modelo/Vista.
 * * @author yange
 */
public class DirectorioEntrada { 
    
    private String nombre;
    private boolean esDirectorio;
    private int numBloques;           // Número total de bloques que ocupa el archivo/directorio
    private int primerBloqueID;       // CRÍTICO: ID del primer bloque asignado en el disco (-1 si no está asignado)
    private String propietario;        // Propietario para la gestión de permisos
    private String permisos;           // Ej: "rw-r--r--"
    
    // Lista de hijos para estructura jerárquica (solo directorios la usarán activamente)
    public ListaEnlazada<DirectorioEntrada> ListaHijos;
    
    public Boolean enDisco; // Estado para rastrear si la entrada tiene bloques asignados
    
    // --- CONSTRUCTOR PRINCIPAL ---
    
    public DirectorioEntrada(String nombre, boolean esDirectorio, int numBloques, int primerBloqueID, String propietario) {
        this.nombre = nombre;
        this.esDirectorio = esDirectorio;
        this.numBloques = numBloques; 
        this.primerBloqueID = primerBloqueID; 
        this.propietario = propietario;
        
        // Asignación de permisos por defecto
        this.permisos = esDirectorio ? "rwxr-xr-x" : "rw-rw-r--";
        
        // Un archivo/directorio está en disco si tiene un ID de bloque válido (ID >= 0)
        this.enDisco = (primerBloqueID != -1); 
        
        // Inicialización de la lista de hijos
        this.ListaHijos = new ListaEnlazada<>();
    }
    
    // --- MÉTODOS DE MANEJO DE ESTRUCTURA ---
    
    /**
     * Agrega un archivo o subdirectorio como hijo si esta entrada es un directorio.
     * @param dato La entrada de directorio/archivo a agregar.
     */
    public void agregarHijo(DirectorioEntrada dato){
        if (this.esDirectorio){
            this.ListaHijos.Insertar(dato);
        } else {
            System.out.println("NO ES DIRECTORIO: No se pueden agregar hijos.");
        }
    }
    
    /**
     * Elimina un hijo de la lista.
     * @param dato La entrada de directorio/archivo a eliminar.
     */
    public void eliminarHijo(DirectorioEntrada dato) {
        // Asume que el método 'eliminar' de ListaEnlazada funciona con objetos tipo T
        this.ListaHijos.eliminar(dato);
    }
    
    // --- MÉTODOS DE VISUALIZACIÓN ---
    
    @Override
    public String toString() {
        // Muestra el nombre con una barra al final si es directorio (ideal para JTree)
        return nombre + (esDirectorio ? "/" : "");
    }
    
    // --- GETTERS Y SETTERS ---
    
    public String getNombre() { return this.nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Boolean getEsDirectorio() { return this.esDirectorio; }
    public void setEsDirectorio(boolean esDirectorio) { this.esDirectorio = esDirectorio; }

    public int getNumBloques() { return this.numBloques; }
    public void setNumBloques(int numBloques) { this.numBloques = numBloques; }

    public int getPrimerBloqueID() { return this.primerBloqueID; }
    /**
     * Establece el ID del primer bloque (necesario al crear o mover archivos).
     * @param primerBloqueID El nuevo ID del bloque.
     */
    public void setPrimerBloqueID(int primerBloqueID) { 
        this.primerBloqueID = primerBloqueID; 
        // Actualiza el estado enDisco automáticamente
        this.enDisco = (primerBloqueID != -1);
    }

    public String getPropietario() { return propietario; }
    public void setPropietario(String propietario) { this.propietario = propietario; }

    public String getPermisos() { return this.permisos; }
    public void setPermisos(String permisos) { this.permisos = permisos; }

    public Boolean getEnDisco() { return this.enDisco; }
    public void setEnDisco(Boolean enDisco) { this.enDisco = enDisco; }
}