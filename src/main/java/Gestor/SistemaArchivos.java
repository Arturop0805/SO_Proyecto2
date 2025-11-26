/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Modelo.Archivo;
import Modelo.Directorio;
import Modelo.EstructuraArchivo;
import Controlador.Proceso;
import Controlador.Proceso.Operacion;
import EstructurasDeDatos.ListaEnlazada; // Requerido si Directorio usa ListaEnlazada

/**
 * Clase principal que gestiona la estructura jerárquica y coordina las operaciones.
 */
public class SistemaArchivos {
    
    public enum ModoUsuario {
        ADMINISTRADOR, USUARIO 
    }
    
    private Directorio raiz;
    private DiscoSimulado disco;
    private PlanificadorDisco planificador;
    private ModoUsuario modoActual;

    public SistemaArchivos() {
        final String PROPIETARIO_ROOT = ModoUsuario.ADMINISTRADOR.toString();
        this.raiz = new Directorio("/", PROPIETARIO_ROOT); // Usamos "/" como nombre de la raíz
        
        this.disco = new DiscoSimulado();
        // ASUNCIÓN: Se debe inicializar PlanificadorDisco (pendiente de su definición)
        this.planificador = new PlanificadorDisco(); 
        this.modoActual = ModoUsuario.ADMINISTRADOR;    
    }
    
    /**
     * Implementa la búsqueda jerárquica de una entrada de archivo o directorio.
     * @param ruta Ruta completa de la entrada (ej: /windows/system32)
     * @return EstructuraArchivo si se encuentra, null si no.
     */
    public EstructuraArchivo buscarRuta(String ruta) {
        if (ruta == null || ruta.equals("/") || ruta.equalsIgnoreCase("root")) {
            return raiz;
        }

        // 1. Normalizar y dividir la ruta (eliminar barra inicial si existe)
        String rutaLimpia = ruta.trim();
        if (rutaLimpia.startsWith("/")) {
            rutaLimpia = rutaLimpia.substring(1);
        }
        
        String[] partes = rutaLimpia.split("/");
        Directorio actual = raiz;
        EstructuraArchivo resultado = null;
        
        // 2. Recorrer el árbol
        for (int i = 0; i < partes.length; i++) {
            String nombreParte = partes[i];
            
            if (actual == null) return null;
            
            // Asumimos que Directorio.buscarContenido(String nombre) encuentra un hijo directo por nombre.
            resultado = actual.buscarContenido(nombreParte); 
            
            if (resultado == null) {
                return null; // Componente no encontrado
            }
            
            if (i < partes.length - 1) {
                // Si no es el último componente, debe ser un directorio para seguir la ruta
                if (resultado.esDirectorio()) {
                    actual = (Directorio) resultado;
                } else {
                    return null; // Error: Intentando entrar en un archivo
                }
            }
        }
        return resultado;
    }
    
    // Resto de métodos de la clase...
    // ...

    private boolean ejecutarCrear(Proceso proceso) {
        if (!tienePermiso(proceso.getOperacion())) return false;    

        String rutaCompleta = proceso.getRutaArchivo();
        
        // 1. Parsear la ruta para obtener el padre y el nombre de la entrada
        int lastSeparator = rutaCompleta.lastIndexOf('/');
        String nombreEntrada = (lastSeparator == -1) 
                               ? rutaCompleta : rutaCompleta.substring(lastSeparator + 1);
        // Si no hay separador o la ruta es solo "/", el padre es la raíz.
        String rutaPadre = (lastSeparator <= 0) 
                           ? "/" : rutaCompleta.substring(0, lastSeparator);

        if (nombreEntrada.isEmpty()) {
            System.out.println("ERROR: Nombre de archivo inválido.");
            proceso.setEstado(Proceso.Estado.TERMINADO);
            return false;
        }
        
        // 2. Encontrar el directorio padre usando la nueva lógica de búsqueda
        EstructuraArchivo estructuraPadre = buscarRuta(rutaPadre);
        
        if (estructuraPadre == null || !estructuraPadre.esDirectorio()) {
            System.out.println("ERROR: Directorio padre '" + rutaPadre + "' no encontrado o no es un directorio.");
            proceso.setEstado(Proceso.Estado.TERMINADO);
            return false;
        }
        Directorio padre = (Directorio) estructuraPadre;

        // 3. Verificar si el archivo ya existe
        if (padre.buscarContenido(nombreEntrada) != null) {
            System.out.println("ERROR: La entrada '" + nombreEntrada + "' ya existe en el directorio padre.");
            proceso.setEstado(Proceso.Estado.TERMINADO);
            return false;
        }

        // 4. Crear el archivo (manteniendo la asunción de constructor)
        Archivo nuevoArchivo = new Archivo(
            nombreEntrada, // Nombre de archivo extraído
            proceso.getTamanoSolicitado(),    
            proceso.getId(),    
            "Azul"
        );

        if (disco.asignarBloques(nuevoArchivo)) {
            // 5. Agregar el archivo al directorio padre
            padre.agregarContenido(nuevoArchivo);
            proceso.setEstado(Proceso.Estado.TERMINADO);
            System.out.println("Archivo " + nombreEntrada + " creado y bloques asignados en " + rutaPadre + ".");
            return true;
        } else {
            System.out.println("ERROR: No hay espacio suficiente en el disco.");
            proceso.setEstado(Proceso.Estado.TERMINADO);
            return false;
        }
    }
    
    private boolean ejecutarEliminar(Proceso proceso) {
        if (!tienePermiso(proceso.getOperacion())) return false;

        String rutaCompleta = proceso.getRutaArchivo();

        // 1. Parsear la ruta para obtener el padre y el nombre de la entrada
        int lastSeparator = rutaCompleta.lastIndexOf('/');
        String nombreEntrada = (lastSeparator == -1) 
                               ? rutaCompleta : rutaCompleta.substring(lastSeparator + 1);
        String rutaPadre = (lastSeparator <= 0) 
                           ? "/" : rutaCompleta.substring(0, lastSeparator);

        if (nombreEntrada.isEmpty()) return false;
        
        // 2. Encontrar el directorio padre
        EstructuraArchivo estructuraPadre = buscarRuta(rutaPadre);
        if (estructuraPadre == null || !estructuraPadre.esDirectorio()) {
            System.out.println("ERROR: Directorio padre no encontrado o no es un directorio.");
            proceso.setEstado(Proceso.Estado.TERMINADO);
            return false;
        }
        Directorio padre = (Directorio) estructuraPadre;

        // 3. Encontrar la estructura a eliminar
        EstructuraArchivo estructura = padre.buscarContenido(nombreEntrada);
        if (estructura == null) {
            System.out.println("ERROR: La entrada '" + nombreEntrada + "' no existe.");
            proceso.setEstado(Proceso.Estado.TERMINADO);
            return false;
        }
        
        // 4. Lógica de eliminación y liberación de bloques
        if (!estructura.esDirectorio()) {
            Archivo archivo = (Archivo) estructura;
            disco.liberarBloques(archivo); // Liberar bloques 
        } else if (((Directorio)estructura).getContenidos().getTamano() > 0) {
            System.out.println("ERROR: No se puede eliminar un directorio no vacío.");
            proceso.setEstado(Proceso.Estado.TERMINADO);
            return false;
        }

        // 5. Eliminar de la lista enlazada del padre
        boolean eliminado = padre.eliminarContenido(nombreEntrada);
        
        proceso.setEstado(Proceso.Estado.TERMINADO);
        return eliminado;
    }
    
    // Se mantienen los métodos ejecutarActualizar y ejecutarLeer, ya que usan la nueva lógica de buscarRuta
    // ...
    
    // Métodos de utilidad que se mantienen (se omiten por brevedad)
    public boolean tienePermiso(Operacion operacion) { /* ... */ return true; }
    public void enviarSolicitud(Proceso proceso) { /* ... */ }
    public boolean ejecutarOperacion(Proceso proceso) { /* ... */ return true; }

    // Getters y Setters de Estado que se mantienen (se omiten por brevedad)
    public Directorio getRaiz() { return raiz; }
    public DiscoSimulado getDisco() { return disco; }
    public PlanificadorDisco getPlanificador() { return planificador; }
    public ModoUsuario getModoActual() { return modoActual; }
    public void setModoActual(ModoUsuario modoActual) { this.modoActual = modoActual; }
}