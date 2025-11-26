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


public class DirectorioEntrada { // NOTA: Clase renombrada y sin herencia de JFrame
    
    private String nombre;
    private boolean esDirectorio;
    private int numBloques; // Renombrado de 'size'
    private int primerBloqueID; // CRÍTICO: Enlace al disco
    private String propietario; // Propietario para la gestión de permisos
    private String permisos;    // Ej: "rw-r--r--"
    
    // Lista de hijos para estructura jerárquica (solo directorios)
    public ListaEnlazada<DirectorioEntrada> ListaHijos;
    
    public Boolean enDisco; // Sigue siendo útil

    // Constructor recomendado
    public DirectorioEntrada(String nombre, boolean esDirectorio, int numBloques, int primerBloqueID, String propietario) {
        this.nombre = nombre;
        this.esDirectorio = esDirectorio;
        this.numBloques = numBloques; 
        this.primerBloqueID = primerBloqueID; // ID del primer bloque asignado (-1 para directorios)
        this.propietario = propietario;
        this.enDisco = (primerBloqueID != -1); // Un archivo está en disco si tiene un ID
        
        // Inicialización de la estructura y permisos
        this.ListaHijos = new ListaEnlazada<>();
        this.permisos = esDirectorio ? "rwxr-xr-x" : "rw-rw-r--";
    }

    // --- Métodos de Asignación y Gestión (Adaptados) ---
    
    // Método CRÍTICO para el JTree
    @Override
    public String toString() {
        // Esto es lo que verá el JTree, crucial para la interfaz gráfica
        return nombre + (esDirectorio ? "/" : "");
    }
    
    // Tu lógica de agregar/eliminar hijo sigue siendo válida
    public void agregarHijo(DirectorioEntrada dato){
        if (this.esDirectorio){
            this.ListaHijos.Insertar(dato);
        } else {
            System.out.println("NO ES DIRECTORIO");
        }
    }
    
    // --- Getters Esenciales (Actualizados) ---
    public String getNombre() { return this.nombre; }
    public Boolean getEsDirectorio() { return this.esDirectorio; }
    public int getNumBloques() { return this.numBloques; } // Renombrado
    public int getPrimerBloqueID() { return this.primerBloqueID; } // Nuevo enlace
    public String getPermisos() { return this.permisos; }
    // ... (otros getters y setters) ...
}