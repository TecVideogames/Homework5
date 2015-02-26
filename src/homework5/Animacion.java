package homework5;

import java.awt.Image;
import java.util.ArrayList;

/**
 * Animacion
 * 
 * Class that implements animations by displaying a sequence of images.
 * Developed by Prof. Antonio Mejorado for Videogames Project Subject, and
 * adopted in Homework 5.
 * (Code descriptions and comments where left as originally coded
 * but the indentation, variable name standards, and spacing where adapted
 * in order to meet the class standards).
 * 
 * @author Antonio Mejorado
 * @version 1.0
 */

public class Animacion{
	
    private ArrayList arrCuadros;
    private int iIndiceCuadroActual;
    private long lTiempoDeAnimacion;
    private long lDuracionTotal;
    private int iPosX;
    private int iPosY;
    private boolean boolTermina;
    private long lTiempoActual;
	
    /**
        Crea una nueva Animacion vacía
    */
    public Animacion() {
        arrCuadros = new ArrayList();
        lDuracionTotal = 0;
        iPosX = 0;
        iPosY = 0;
        boolTermina = false;
        iniciar();
    }

    /**
        Añade una cuadro a la animación con la duración
        indicada (tiempo que se muestra la imagen).
    */	
    public synchronized void sumaCuadro(Image imaImagen, long lDuracion) {
        lDuracionTotal += lDuracion;
        arrCuadros.add(new cuadroDeAnimacion(imaImagen, lDuracionTotal));
    }

    // Inicializa la animación desde el principio. 
    public synchronized void iniciar() {
        lTiempoDeAnimacion = 0;
        iIndiceCuadroActual = 0;
    }

    /**
        Actualiza la imagen (cuadro) actual de la animación,
        si es necesario.
    */
    public synchronized void actualiza(long lTiempoTranscurrido) {
        if (arrCuadros.size() > 1) {

            lTiempoDeAnimacion += lTiempoTranscurrido;

            if (lTiempoDeAnimacion >= lDuracionTotal) {
                lTiempoDeAnimacion = 
                        lTiempoDeAnimacion % lDuracionTotal;
                iIndiceCuadroActual = 0; 
            }

            while (lTiempoDeAnimacion > 
                getCuadro(iIndiceCuadroActual).lTiempoFinal){
                iIndiceCuadroActual ++;
            }
        }
    }
    
    /**
        isBooleanTermina
        gets boolTermina <code>boolean</code> value
    */
    public boolean isBoolTermina() {
        return boolTermina;
    }
    
    /**
        setBooleanTermina
        sets <code>boolTermina</code> value
        @param boolT
    */
    public void setBoolTermina(boolean boolT) {
        boolTermina = boolT;
    }
    
    /**
        getTiempoActual
        gets lTiempoActual <code>long</code> value
    */
    public long getTiempoActual() {
        return lTiempoActual;
    }
    /**
        setTiempoActual
        sets <code>lTiempoActual</code> value
        @param lTiempo
    */
    public void setTiempoActual(long lTiempo) {
        lTiempoActual = lTiempo;
    }

    /**
        Actualiza la imagen (cuadro) actual de la animación,
        si es necesario.
    */
    public synchronized void get(long tiempoTranscurrido) {
        if (arrCuadros.size() > 1) {
            lTiempoDeAnimacion += tiempoTranscurrido;

            if (lTiempoDeAnimacion >= lDuracionTotal) {
                lTiempoDeAnimacion = 
                        lTiempoDeAnimacion % lDuracionTotal;
                iIndiceCuadroActual = 0; 
            }

            while (lTiempoDeAnimacion > 
                getCuadro(iIndiceCuadroActual).lTiempoFinal) {
                iIndiceCuadroActual ++;
            }
        }
    }
    /**
     * Devuelve el tiempo que se ha estado ejecutando la animacion
     * 
    */
    public long getTiempoDeAnimacion() {
        return lTiempoDeAnimacion;
    }

    /**
     * Devuelve posicion en X de animacion
     * 
    */
    public long getPosX() {
        return iPosX;
    }

    /**
     * Devuelve posicion en Y de animacion
     * 
    */
    public long getPosY() {
        return iPosY;
    }

    /**
     * Updates animations X coordinate
     * 
    */
    public void setPosX(int iX) {
        iPosX = iX;
    }

    /**
     * Updates animations Y coordinate
     * 
    */
    public void setPosY(int iY) {
        iPosY = iY;
    }

    /**
        Captura la imagen actual de la animación. Regeresa null
        si la animación no tiene imágenes.
    */
    public synchronized Image getImagen() {
        if (arrCuadros.size() == 0) {
            return null;
        }
        else {
            return getCuadro(iIndiceCuadroActual).imaImagen;
        }
    }

    /**
        Devuelve numero de cuadro actual que despliega la animacion
    */
    private cuadroDeAnimacion getCuadro(int iI) {
        return (cuadroDeAnimacion)arrCuadros.get(iI);
    }
    
    /**
        Sub-clase para manejar cuadros (imagenes) que conforman una animacion
    */
    public class cuadroDeAnimacion {

        Image imaImagen;
        long lTiempoFinal;
        /**
            Constructor
        */
        public cuadroDeAnimacion() {
            this.imaImagen = null;
            this.lTiempoFinal = 0;
        }
        
        /**
            Constructor con parametros
        */
        public cuadroDeAnimacion(Image imaI, long lTiempo) {
            this.imaImagen = imaI;
            this.lTiempoFinal = lTiempo;
        }
        
        /**
            getImagen
            devuelve objeto de tipo <code>Imagen</code> 
        */
        public Image getImagen() {
            return imaImagen;
        }
        
        /**
            getTiempoFinal
            obtiene valor de <code>lTiempoFinal</code>
        */
        public long getTiempoFinal() {
            return lTiempoFinal;
        }
        
        /**
            setImagen
            asigna <code>Imagen</code>
            @param imaI
        */
        public void setImagen(Image imaI) {
            this.imaImagen = imaI;
        }
        
        /**
            setTiempoFinal
            asigna <code>long</code> 
            @param lTiempo
        */
        public void setTiempoFinal(long lTiempo) {
            this.lTiempoFinal = lTiempo;
        }
        
    }
		
}