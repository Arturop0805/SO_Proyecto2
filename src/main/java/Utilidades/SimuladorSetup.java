/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import Controlador.AdministradorDirectorios;
import Modelo.Disco;
import Modelo.DirectorioEntrada;

/**
 * Encargada de la configuración inicial del Disco y la estructura de archivos.
 * Puede vivir en un paquete aparte como 'Utilidades' o 'Config'.
 */
public class SimuladorSetup {
    
    private static final int CAPACIDAD_DISCO = 100; 
    private Disco discoSimulado;
    private AdministradorDirectorios adminFS;
    private String TipoUsuario; 
    private static final int ID_PROCESO_SETUP = 99;
    
    public SimuladorSetup(){
        this.discoSimulado = new Disco(CAPACIDAD_DISCO);
        this.adminFS = new AdministradorDirectorios("/", "root");
        this.TipoUsuario = "ADMIN";
        System.out.println("Disco inicializado con " + CAPACIDAD_DISCO + " bloques.");
    }
    
    /**
     * Crea la estructura jerárquica de la prueba (Windows, ProgramUser, etc.) 
     * y asigna bloques reales en el disco.
     * @return El AdministradorDirectorios configurado.
     */
    public AdministradorDirectorios setupSistemaArchivosPrueba() {
        DirectorioEntrada raiz = adminFS.getRaiz();

        // --- Nivel 1 ---
        DirectorioEntrada windows = crearDirectorio("windows", "root", raiz);
        DirectorioEntrada programUser = crearDirectorio("ProgramUser", "root", raiz);

        // --- Nivel 2 ---
        crearDirectorio("system32", "root", windows);
        DirectorioEntrada valorant = crearDirectorio("valorant", "userA", programUser);
        DirectorioEntrada lol = crearDirectorio("League of Legends", "userB", programUser);

        // --- Nivel 3: Archivos con Asignación de Bloques ---
        // Archivos grandes de la prueba: obb(8), apk(21), obb(13), apk(34)
        crearArchivo("obb", 8, "userA", valorant);
        crearArchivo("apk", 21, "userA", valorant);
        crearArchivo("obb", 13, "userB", lol);
        crearArchivo("apk", 34, "userB", lol);
        
        System.out.println("Bloques Usados en Setup: " + (CAPACIDAD_DISCO - discoSimulado.getBloquesLibres()));
        return adminFS;
    }
    
    // Métodos auxiliares de creación (se mantienen)
    private DirectorioEntrada crearDirectorio(String nombre, String propietario, DirectorioEntrada padre) {
        DirectorioEntrada nuevoDir = new DirectorioEntrada(nombre, true, 0, -1, propietario);
        padre.agregarHijo(nuevoDir);
        return nuevoDir;
    }

    private void crearArchivo(String nombre, int bloques, String propietario, DirectorioEntrada padre) {
        int primerID = discoSimulado.allocateBlocks(bloques, nombre, ID_PROCESO_SETUP);
        if (primerID != -1) {
            DirectorioEntrada nuevoArchivo = new DirectorioEntrada(nombre, false, bloques, primerID, propietario);
            padre.agregarHijo(nuevoArchivo);
        } else {
            System.err.println("ERROR CRÍTICO: No se pudo crear " + nombre + ". Disco lleno.");
        }
    }
    
    // Getters para que el Controlador pueda acceder a las instancias
    public AdministradorDirectorios getAdminFS() { return adminFS; }
    public Disco getDiscoSimulado() { return discoSimulado; }
    public String getTipoUsuario() { return TipoUsuario; }
}