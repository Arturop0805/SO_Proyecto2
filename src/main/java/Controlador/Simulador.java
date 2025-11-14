/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;



import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Arturo
 */
public class Simulador extends JFrame {
    
    
    
    public Simulador(){
        this.crearArbol();
    }
    
    public DefaultMutableTreeNode crearArbol(){
        
        
        
        
       
        
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("gestor"); 
       DefaultMutableTreeNode system32 = new DefaultMutableTreeNode("system32"); 
       DefaultMutableTreeNode programUser = new DefaultMutableTreeNode("programUser"); 
       DefaultMutableTreeNode SO = new DefaultMutableTreeNode("SO"); 
       DefaultMutableTreeNode LoL = new DefaultMutableTreeNode("LoL"); 
        
       
       raiz.add(system32);
       raiz.add(programUser);
       raiz.add(SO);
       
        JTree arbol = new JTree(raiz);
        setLayout (new BorderLayout());
        add (arbol, BorderLayout.NORTH);
        return raiz;
    }
    
    
     
    
}
