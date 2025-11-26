/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


<<<<<<< HEAD
import Modelo.Disco;
import Utilidades.SimuladorSetup;
import javax.swing.tree.DefaultMutableTreeNode;

=======

import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import Modelo.Archivo;
import Modelo.Disco;
>>>>>>> 397e2148f20879731e40545b63d12913a1f16d7d
/**
 * Controlador principal. Centraliza la inicialización y acceso al Sistema 
 * de Archivos (FS) y al Disco, además de gestionar la sesión del usuario.
 */
public class Simulador { 
    
    private AdministradorDirectorios adminFS;
    private Disco discoSimulado;
    private String TipoUsuario;
    public Disco SD;
    
<<<<<<< HEAD
    public Simulador() {
        // Usa la clase de setup para inicializar todo el modelo
        SimuladorSetup setup = new SimuladorSetup();
        this.adminFS = setup.setupSistemaArchivosPrueba();
        this.discoSimulado = setup.getDiscoSimulado();
        this.TipoUsuario = setup.getTipoUsuario();
    }
    
    /**
     * Retorna el nodo raíz (DefaultMutableTreeNode) para que la Vista pueda 
     * construir el DefaultTreeModel. Este método reemplaza la antigua lógica
     * de construcción manual.
     */
    public DefaultMutableTreeNode crearArbolPrueba() {
        // La llamada a obtenerModeloArbol() invoca la función recursiva
        // que construye el árbol Swing a partir de las DirectorioEntrada.
        return (DefaultMutableTreeNode) this.adminFS.obtenerModeloArbol().getRoot();
=======
    
    public Simulador(int tamañoDisco){
        this.TipoUsuario = "ADMIN";
        this.SD = new Disco(tamañoDisco);
    }
    
    public DefaultMutableTreeNode crearArbolPrueba(){
        
        Archivo windows = new Archivo("windows", true,1);
        Archivo system32 = new Archivo("system32", true,1);
        Archivo programUser = new Archivo("ProgramUser", true,2);
        Archivo valorant = new Archivo("valorant", true,3);
        Archivo lol = new Archivo("League of Legends", true,5);
        Archivo data1 = new Archivo("obb", false,8);
        Archivo data2 = new Archivo("obb", false,13);
        Archivo apk1 = new Archivo("apk", false,21);
        Archivo apk2 = new Archivo("apk", false,34);
        
        
        
         SD.insertarArch(windows);
         SD.insertarArch(system32);
         SD.insertarArch(programUser);
         SD.insertarArch(valorant);
         SD.insertarArch(lol);
         SD.insertarArch(data1);
         SD.insertarArch(data2);
         SD.insertarArch(apk1);
         SD.insertarArch(apk2);

       
      
        
        
        
       DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(windows.getNombre(), windows.getEsDirectorio()); 
       DefaultMutableTreeNode carpeta1 = new DefaultMutableTreeNode(system32.getNombre(), system32.getEsDirectorio()); 
       DefaultMutableTreeNode carpeta2 = new DefaultMutableTreeNode(programUser.getNombre(), programUser.getEsDirectorio()); 
       DefaultMutableTreeNode juego1 = new DefaultMutableTreeNode(valorant.getNombre(), valorant.getEsDirectorio()); 
       DefaultMutableTreeNode juego2 = new DefaultMutableTreeNode(lol.getNombre(), lol.getEsDirectorio()); 
       DefaultMutableTreeNode archivo1 = new DefaultMutableTreeNode(data1.getNombre(), data1.getEsDirectorio()); 
       DefaultMutableTreeNode archivo2 = new DefaultMutableTreeNode(data2.getNombre(), data2.getEsDirectorio()); 
       DefaultMutableTreeNode apkValorant = new DefaultMutableTreeNode(apk1.getNombre(), apk1.getEsDirectorio()); 
       DefaultMutableTreeNode apkLoL = new DefaultMutableTreeNode(apk2.getNombre(), apk2.getEsDirectorio()); 
        
      raiz.add(carpeta1);
      raiz.add(carpeta2);
      carpeta2.add(juego1);
      carpeta2.add(juego2);
      juego1.add(archivo1);
      juego2.add(archivo2);
      juego1.add(apkValorant);
      juego2.add(apkLoL);
       
      
      windows.agregarHijo(system32);
      windows.agregarHijo(programUser);
      programUser.agregarHijo(valorant);
      programUser.agregarHijo(lol);
      valorant.agregarHijo(data1);
      valorant.agregarHijo(apk1);
      lol.agregarHijo(data2);
      lol.agregarHijo(apk2);
    
      
     
      
       
        
        return raiz;
>>>>>>> 397e2148f20879731e40545b63d12913a1f16d7d
    }
    
    // --- Gestión de Usuario (Se mantiene) ---
    
    public void CambiarTipoUsuario(){
        if (this.TipoUsuario.equals("ADMIN")) {
            this.TipoUsuario = "USER";
        } else {
            this.TipoUsuario = "ADMIN";
        }
    }
            
    public String getTipoUsuario() {
        return this.TipoUsuario;
    }
    
    // --- Acceso a Controladores para futuras operaciones (e.g., Agregar/Eliminar) ---
    public AdministradorDirectorios getAdminFS() {
        return adminFS;
    }
    
    public Disco getDiscoSimulado() {
        return discoSimulado;
    }
}