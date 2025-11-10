/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.so_proyecto2;
import EstructurasDeDatos.Cola;
import EstructurasDeDatos.ListaEnlazada;
/**
 *
 * @author Arturo
 */
public class SO_Proyecto2 {

    public static void main(String[] args) {
        
        
       
        Cola cola = new Cola();
        ListaEnlazada lista = new ListaEnlazada();
        
        lista.Insertar(1);
        lista.Insertar(3);
        lista.Insertar(5);
        lista.Insertar(7);
        lista.Insertar(9);
        
        
      
        lista.print();
        
        
    }
}
