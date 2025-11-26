/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author yange
 */

/**
 * Clase abstracta base para representar un Archivo o un Directorio.
 */
public abstract class EstructuraArchivo {
    
    protected String nombre;
    protected boolean esDirectorio;
    protected String propietario;

    public EstructuraArchivo(String nombre, boolean esDirectorio, String propietario) { // 👈 REQUIERE EL PROPIETARIO
        this.nombre = nombre;
        this.esDirectorio = esDirectorio;
        this.propietario = propietario; // 👈 Asignación
    }

    // --- Getters y Setters ---

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { 
        this.nombre = nombre; // Para la operación de Actualizar (renombrar) [cite: 43]
    }
    public boolean esDirectorio() { return esDirectorio; }
    
    // Método abstracto para obtener el tamaño real (en bloques o contenido)
    public abstract int getTamano();
    public String getPropietario() { return propietario; }
}
