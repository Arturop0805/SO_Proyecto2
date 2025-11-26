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
 * Esta clase se encarga de instanciar y pre-configurar los objetos del Modelo y Control.
 */
public class SimuladorSetup {
    
    // Constantes de configuración
    private static final int CAPACIDAD_DISCO = 100; // Capacidad total en bloques
    private static final int ID_PROCESO_SETUP = 99; // ID de proceso usado para inicializar
    
    private Disco discoSimulado;
    private AdministradorDirectorios adminFS;
    private String TipoUsuario; // Podría usarse para simular la sesión inicial
    
    
    public SimuladorSetup(){
        // 1. Inicializa el Modelo (Disco)
        this.discoSimulado = new Disco(CAPACIDAD_DISCO);
        
        // 2. Inicializa el Controlador (AdminFS) e inyecta la dependencia del Disco.
        // ✅ CORRECCIÓN CRÍTICA: Se pasa el discoSimulado al constructor del AdminFS.
        this.adminFS = new AdministradorDirectorios("/", "root", this.discoSimulado); 
        
        this.TipoUsuario = "ADMIN";
        System.out.println("Disco inicializado con " + CAPACIDAD_DISCO + " bloques.");
    }
    
    /**
     * Crea la estructura jerárquica de prueba y asigna bloques reales en el disco
     * usando el método de Asignación Encadenada.
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
        
        // Total bloques a usar: 8 + 21 + 13 + 34 = 76 bloques.
        crearArchivo("obb.val", 8, "userA", valorant);
        crearArchivo("apk.val", 21, "userA", valorant);
        crearArchivo("obb.lol", 13, "userB", lol);
        crearArchivo("apk.lol", 34, "userB", lol);
        
        System.out.println("--- Setup Completado ---");
        System.out.println("Capacidad Total: " + CAPACIDAD_DISCO + " bloques.");
        System.out.println("Bloques Usados en Setup: " + (CAPACIDAD_DISCO - discoSimulado.getBloquesLibres()) + " bloques.");
        System.out.println("Bloques Libres Restantes: " + discoSimulado.getBloquesLibres() + " bloques.");
        
        return adminFS;
    }
    
    // --- Métodos Auxiliares ---
    
    /**
     * Crea una nueva entrada de directorio. Los directorios no consumen bloques de datos.
     */
    private DirectorioEntrada crearDirectorio(String nombre, String propietario, DirectorioEntrada padre) {
        // Directorio: numBloques = 0, primerBloqueID = -1 (no asignado)
        DirectorioEntrada nuevoDir = new DirectorioEntrada(nombre, true, 0, -1, propietario);
        // La jerarquía se construye llamando al método agregarHijo de DirectorioEntrada
        padre.agregarHijo(nuevoDir); 
        return nuevoDir;
    }

    /**
     * Intenta asignar bloques en el disco y, si tiene éxito, crea la entrada de archivo.
     */
    private void crearArchivo(String nombre, int bloques, String propietario, DirectorioEntrada padre) {
        // 1. Asignar bloques en el Disco (Lógica Encadenada)
        int primerID = discoSimulado.allocateBlocks(bloques, nombre, ID_PROCESO_SETUP);
        
        if (primerID != -1) {
            // 2. Crear la entrada de directorio/archivo con el ID del primer bloque
            DirectorioEntrada nuevoArchivo = new DirectorioEntrada(nombre, false, bloques, primerID, propietario);
            padre.agregarHijo(nuevoArchivo);
        } else {
            System.err.println("ERROR CRÍTICO: No se pudo crear el archivo " + nombre + " de " + bloques + " bloques. Disco lleno o espacio fragmentado.");
        }
    }
    
    // --- Getters ---
    
    public AdministradorDirectorios getAdminFS() { return adminFS; }
    public Disco getDiscoSimulado() { return discoSimulado; }
    public String getTipoUsuario() { return TipoUsuario; }
}