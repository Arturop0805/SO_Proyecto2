/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilidades;

import Controlador.AdministradorDirectorios;
import Gestor.DiscoSimulado; // Clase correcta para la simulación del disco
import Modelo.Directorio; // Se usa Directorio para la raíz y los subdirectorios

/**
 * Encargada de la configuración inicial del Disco y la estructura de archivos.
 * Esta clase se encarga de instanciar y pre-configurar los objetos del Modelo y Control.
 */
public class SimuladorSetup {
    
    // El tamaño del disco se lee ahora de DiscoSimulado.CAPACIDAD_MAXIMA
    // private static final int CAPACIDAD_DISCO = 100; // Se elimina, se lee del DiscoSimulado
    
    // No necesitamos ID_PROCESO_SETUP, ya que crearEntrada usa el Propietario/Usuario
    // private static final int ID_PROCESO_SETUP = 99; 
    
    private DiscoSimulado discoSimulado;
    private AdministradorDirectorios adminFS;
    private String TipoUsuario;
    
    
    public SimuladorSetup(){
        // 1. Inicializa el Gestor (DiscoSimulado)
        this.discoSimulado = new DiscoSimulado(); // DiscoSimulado ya inicializa su tamaño internamente
        
        // 2. Inicializa el Controlador (AdminFS) e inyecta la dependencia del Disco.
        this.adminFS = new AdministradorDirectorios("/", "ROOT", this.discoSimulado); 
        
        this.TipoUsuario = "ADMIN";
        System.out.println("Disco inicializado con " + discoSimulado.getCapacidadMaxima() + " bloques.");
    }
    
    /**
     * Crea la estructura jerárquica de prueba y asigna bloques reales en el disco
     * usando el método de Asignación Encadenada, a través del controlador.
     * @return El AdministradorDirectorios configurado.
     */
    public AdministradorDirectorios setupSistemaArchivosPrueba() {
        
        // La raíz es de tipo Directorio
        Directorio raiz = adminFS.getRaiz();

        // --- Nivel 1 ---
        // Usamos adminFS.crearEntrada para delegar la lógica de creación. 
        // El resultado debe ser casteado a Directorio para usarlo como padre.
        Directorio windows = (Directorio) adminFS.crearEntrada("windows", true, 0, raiz, "ROOT");
        Directorio programUser = (Directorio) adminFS.crearEntrada("ProgramUser", true, 0, raiz, "ROOT");

        // --- Nivel 2 ---
        adminFS.crearEntrada("system32", true, 0, windows, "ROOT");
        Directorio valorant = (Directorio) adminFS.crearEntrada("valorant", true, 0, programUser, "USERA");
        Directorio lol = (Directorio) adminFS.crearEntrada("League of Legends", true, 0, programUser, "USERB");

        // --- Nivel 3: Archivos con Asignación de Bloques ---
        
        // El tamañoBloques se pasa en crearEntrada, y el controlador gestiona la asignación.
        // Total bloques a usar: 8 + 21 + 13 + 34 = 76 bloques.
        adminFS.crearEntrada("obb.val", false, 8, valorant, "USERA");
        adminFS.crearEntrada("apk.val", false, 21, valorant, "USERA");
        adminFS.crearEntrada("obb.lol", false, 13, lol, "USERB");
        adminFS.crearEntrada("apk.lol", false, 34, lol, "USERB");
        
        // Los métodos auxiliares crearDirectorio y crearArchivo ya no son necesarios.

        System.out.println("--- Setup Completado ---");
        System.out.println("Capacidad Total: " + discoSimulado.getCapacidadMaxima() + " bloques.");
        
        int bloquesUsados = discoSimulado.getCapacidadMaxima() - discoSimulado.getBloquesLibresCount();
        System.out.println("Bloques Usados en Setup: " + bloquesUsados + " bloques.");
        
        System.out.println("Bloques Libres Restantes: " + discoSimulado.getBloquesLibresCount() + " bloques.");
        
        return adminFS;
    }
    
    // --- Getters ---
    
    public AdministradorDirectorios getAdminFS() { return adminFS; }
    public DiscoSimulado getDiscoSimulado() { return discoSimulado; }
    public String getTipoUsuario() { return TipoUsuario; }
}