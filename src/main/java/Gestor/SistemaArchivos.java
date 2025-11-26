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
import EstructurasDeDatos.ListaEnlazada; 

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
        this.raiz = new Directorio("/", PROPIETARIO_ROOT); 
        
        this.disco = new DiscoSimulado();
        this.planificador = new PlanificadorDisco();    
        this.modoActual = ModoUsuario.ADMINISTRADOR;    
    }
    
    /**
     * Implementa la búsqueda jerárquica de una entrada de archivo o directorio.
     * REEMPLAZA EL USO DE split() por indexOf() y substring().
     * @param ruta Ruta completa de la entrada (ej: /windows/system32)
     * @return EstructuraArchivo si se encuentra, null si no.
     */
    public EstructuraArchivo buscarRuta(String ruta) {
        if (ruta == null || ruta.equals("/") || ruta.equalsIgnoreCase("root")) {
            return getRaiz();
        }

        // 1. Normalizar la ruta (eliminar barra inicial si existe)
        String rutaLimpia = ruta.trim();
        if (rutaLimpia.startsWith("/")) {
            rutaLimpia = rutaLimpia.substring(1);
        }
        
        Directorio actual = getRaiz();
        EstructuraArchivo resultado = null;
        String pathRestante = rutaLimpia;
        
        // 2. Recorrer el árbol usando métodos de String básicos
        while (pathRestante.length() > 0) {
            int separadorIndex = pathRestante.indexOf('/');
            String nombreParte;

            if (separadorIndex == 0) {
                // Caso de barra doble (e.g., //path) o componente vacío, ignorar y avanzar
                pathRestante = pathRestante.substring(1);
                continue;
            } else if (separadorIndex == -1) {
                // Último componente de la ruta
                nombreParte = pathRestante;
                pathRestante = ""; // Marcamos como vacío para terminar el bucle
            } else {
                // Componente intermedio
                nombreParte = pathRestante.substring(0, separadorIndex);
                pathRestante = pathRestante.substring(separadorIndex + 1);
            }
            
            if (actual == null) return null;

            // Buscar el componente actual en el directorio 'actual'
            resultado = actual.buscarContenido(nombreParte); 
            
            if (resultado == null) {
                return null; // Componente no encontrado
            }
            
            // Si AÚN queda ruta restante, el 'resultado' debe ser un directorio para continuar
            if (pathRestante.length() > 0) {
                if (resultado.esDirectorio()) {
                    actual = (Directorio) resultado;
                } else {
                    return null; // Error: Intentando entrar en un archivo
                }
            }
        }
        return resultado;
    }
    
    /**
     * Verifica si la operación está permitida en el modo actual.
     * @param operacion Operación solicitada.
     * @return true si es permitida, false si está restringida.
     */
    public boolean tienePermiso(Operacion operacion) {
        if (modoActual == ModoUsuario.ADMINISTRADOR) {
            return true; 
        } else {
            // Usuario restringido a solo lectura (LEER)
            return operacion == Operacion.LEER;
        }
    }
    
    // --- Operaciones de Alto Nivel (CRUD) ---
    
    /**
     * Envía una solicitud de E/S para ser procesada por el planificador.
     */
    public void enviarSolicitud(Proceso proceso) {
        if (proceso.getOperacion() != Operacion.LEER && !tienePermiso(proceso.getOperacion())) {
            System.out.println("Permiso denegado para la operación " + proceso.getOperacion());
            proceso.setEstado(Proceso.Estado.TERMINADO); 
            return;
        }
        
        planificador.agregarSolicitud(proceso);
    }
    
    /**
     * Ejecuta una operación CRUD que ya fue planificada.
     */
    public boolean ejecutarOperacion(Proceso proceso) {
        proceso.setEstado(Proceso.Estado.EJECUTANDO);    

        switch (proceso.getOperacion()) {
            case CREAR:
                return ejecutarCrear(proceso);
            case ELIMINAR:
                return ejecutarEliminar(proceso);
            case ACTUALIZAR:
                return ejecutarActualizar(proceso);    
            case LEER:
                return ejecutarLeer(proceso);    
            default:
                return false;
        }
    }

    private boolean ejecutarCrear(Proceso proceso) {
        if (!tienePermiso(proceso.getOperacion())) return false;    

        String rutaCompleta = proceso.getRutaArchivo();
        
        // 1. Parsear la ruta para obtener el padre y el nombre de la entrada
        int lastSeparator = rutaCompleta.lastIndexOf('/');
        String nombreEntrada = (lastSeparator == -1)    
                               ? rutaCompleta : rutaCompleta.substring(lastSeparator + 1);
        String rutaPadre = (lastSeparator <= 0)    
                           ? "/" : rutaCompleta.substring(0, lastSeparator);

        if (nombreEntrada.isEmpty()) {
            System.out.println("ERROR: Nombre de archivo inválido.");
            proceso.setEstado(Proceso.Estado.TERMINADO);
            return false;
        }
        
        // 2. Encontrar el directorio padre usando la lógica de búsqueda corregida
        EstructuraArchivo estructuraPadre = buscarRuta(rutaPadre);
        
        // CORRECCIÓN: Se reemplaza 'estructuraPadra' por 'estructuraPadre'
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

        // 4. Crear el archivo
        Archivo nuevoArchivo = new Archivo(
            nombreEntrada, // Nombre de archivo extraído
            proceso.getTamanoSolicitado(),    
            proceso.getId(),    
            // Asumimos que el constructor de Archivo requiere el propietario, 
            // y que el propietario es el del proceso que lo crea.
            String.valueOf(proceso.getId()) // Se usa el ID del proceso como propietario temporal
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

    private boolean ejecutarActualizar(Proceso proceso) {
        if (!tienePermiso(proceso.getOperacion())) return false;    
        
        EstructuraArchivo estructura = buscarRuta(proceso.getRutaArchivo());
        if (estructura != null) {
            estructura.setNombre(proceso.getRutaArchivo() + "_UPDATED");    
            proceso.setEstado(Proceso.Estado.TERMINADO);
            return true;
        }
        return false;
    }
    
    private boolean ejecutarLeer(Proceso proceso) {
        EstructuraArchivo estructura = buscarRuta(proceso.getRutaArchivo());
        if (estructura != null) {
            System.out.println("Leyendo/Visualizando: " + estructura.getNombre() +    
                                 ", Tamaño: " + estructura.getTamano() + " bloques.");
            proceso.setEstado(Proceso.Estado.TERMINADO);
            return true;
        }
        return false;
    }
    
    // --- Getters y Setters de Estado ---
    
    

    public Directorio getRaiz() { return raiz; }
    public DiscoSimulado getDisco() { return disco; }
    public PlanificadorDisco getPlanificador() { return planificador; }
    public ModoUsuario getModoActual() { return modoActual; }
    public void setModoActual(ModoUsuario modoActual) { this.modoActual = modoActual; }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(Directorio raiz) {
        this.raiz = raiz;
    }
}