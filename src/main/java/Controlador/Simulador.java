/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;



import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import Modelo.Archivo;
/**
 *
 * @author Arturo
 */
public class Simulador extends JFrame {
    
    
    
    private String TipoUsuario;
    
    
    public Simulador(){
        this.TipoUsuario = "ADMIN";
        this.crearArbolPrueba();
    }
    
    public DefaultMutableTreeNode crearArbolPrueba(){
        
        Archivo windows = new Archivo("windows", true);
        Archivo system32 = new Archivo("system32", true);
        Archivo programUser = new Archivo("ProgramUser", true);
        Archivo valorant = new Archivo("valorant", false);
        Archivo lol = new Archivo("League of Legends", false);
        
        
       
        
       DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(windows.getNombre(), windows.getEsDirectorio()); 
       DefaultMutableTreeNode carpeta1 = new DefaultMutableTreeNode(system32.getNombre(), system32.getEsDirectorio()); 
       DefaultMutableTreeNode carpeta2 = new DefaultMutableTreeNode(programUser.getNombre(), programUser.getEsDirectorio()); 
       DefaultMutableTreeNode archivo1 = new DefaultMutableTreeNode(valorant.getNombre(), valorant.getEsDirectorio()); 
       DefaultMutableTreeNode archivo2 = new DefaultMutableTreeNode(lol.getNombre(), lol.getEsDirectorio()); 
        
      
        
       raiz.add(carpeta1);
       carpeta1.add(carpeta2);
       carpeta2.add(archivo2);
       carpeta2.add(archivo1);
       
        
        return raiz;
    }
    
    public void agregar(String nombre, DefaultMutableTreeNode raiz) {
        DefaultMutableTreeNode NuevoNodo = new DefaultMutableTreeNode(nombre);
        raiz.add(NuevoNodo);
    }
    
    public void CambiarTipoUsuario(){
        if (this.TipoUsuario == "ADMIN" ) {
            this.TipoUsuario = "USER";
        } else {
            this.TipoUsuario = "ADMIN";
        }
    }
            
     public String getTipoUsuario() {
         return this.TipoUsuario;
     }
    
}
