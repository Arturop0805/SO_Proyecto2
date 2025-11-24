/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Arturo
 */
public class Archivo extends JFrame{
    
    private String nombre;
    private boolean EsDirectorio;
    private int size;
    
    
    public Archivo (String nombre,Boolean EsDirectorio) {
        this.nombre = nombre;
        this.EsDirectorio = EsDirectorio;
    }
    
    public Archivo () {
        this.nombre = null;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public void setEsDirectorio(boolean valor){
        this.EsDirectorio = valor;
    }
    
    public Boolean getEsDirectorio(){
        return this.EsDirectorio;
    }
            
}
