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
        
        Archivo windows = new Archivo("windows", true,1);
        Archivo system32 = new Archivo("system32", true,1);
        Archivo programUser = new Archivo("ProgramUser", true,2);
        Archivo valorant = new Archivo("valorant", true,3);
        Archivo lol = new Archivo("League of Legends", true,5);
        Archivo data1 = new Archivo("obb", false,8);
        Archivo data2 = new Archivo("obb", false,13);
        Archivo apk1 = new Archivo("apk", false,21);
        Archivo apk2 = new Archivo("apk", false,34);
        
        
       
        
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
