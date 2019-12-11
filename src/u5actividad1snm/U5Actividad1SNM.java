/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u5actividad1snm;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Santiago Naranjo Marcillo
 */
public class U5Actividad1SNM {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Método principal main
     *
     * @param args
     */
    public static void main(String[] args) {

        U5Actividad1SNM programa = new U5Actividad1SNM();
        programa.inicio();
    }

    /**
     * Método inicio
     */
    public void inicio() {
        /* Decalarció0n variables */
        String respuestaUsuario;
        String textoRespuesta;
        Integer respuestaTamaño;
        Integer respuestaIntentos;
        Integer respuestaDificultad;
        Boolean seHaAcertado;

        /* Metodos */
        respuestaTamaño = validarTamaño();
        scanner.nextLine(); // Limpiar Buffer
        respuestaIntentos = validarIntentos();
        scanner.nextLine(); // Limpiar Buffer
        respuestaDificultad = validarDificultad();
        scanner.nextLine(); // Limpiar Buffer
        String palabraSecreta = generarPalabraSecreta(respuestaTamaño);
        
        
        
        

        do {
            respuestaUsuario = pedirRespuestaEnFormatoValido(respuestaTamaño);
            seHaAcertado = resolverRespuesta(palabraSecreta, respuestaUsuario);

            if (respuestaIntentos-- <= 1) {
                seHaAcertado = true;
            }
           
        } while (!seHaAcertado);
    }

    /**
     * Método de nivel 3: Genera una letra aleatoria
     *
     * @return la letra aleatoria generada
     */
    public Character generarLetraAleatoria() {
        Random rdn = new Random();

        Integer ASCIIcaracterA = 97;
        Integer ASCIIcaracterZ = 122;

        Character numAleatorio = (char) (rdn.nextInt(ASCIIcaracterZ - ASCIIcaracterA) + ASCIIcaracterA);
        return numAleatorio;
    }

    /**
     * Método de nivel 2: Genera una palabra de 5 letras aleatorias
     *
     * @return la palabra aleatoria de longitud cinco
     */
    public String montarPalabra(Integer tamaño) {

        String palabra = "";

        for (Integer i = 0; i < tamaño; i++) {
            palabra += generarLetraAleatoria();
        }

        return palabra;
    }

    /**
     * Método de nivel 2: Pide al usuario que introduzca una cadena de texto
     *
     * @return La cadena de texto introducida por el usuario
     */
//   public String preguntaTexto(Integer tamaño) {
//       String texto = "Escribe " +  tamaño + " letras minúsculas: ";
//       return texto;
//    }
    public String leerRespuesta(Integer tamaño) {
        System.out.print("Escribe " + tamaño + " letras minúsculas: ");
        return scanner.nextLine();
    }

    /**
     * Método de nivel 2: Indica si la respuesta proporcionada cumple con el
     * formato especificado
     *
     * @param respuesta La respuesta a analizar su formato
     * @return CIERTO si es un formato válido (5 letras y minúsculas), FALSO en
     * cualquier otro caso
     */
    public Boolean comprobarFormato(String respuesta, Integer tamaño) {
          String mensajeError = "Debes introducir "+ tamaño +" letras minúsculas. Continúa intentándolo";
        if (respuesta.length() != tamaño) {
            System.out.println(mensajeError);
            return false;
        } else {
            for (Integer i = 0; i < respuesta.length(); i++) {

                Character letraRespuesta = respuesta.charAt(i);
                if (letraRespuesta < 'a' || letraRespuesta > 'z') {
                    System.out.println(mensajeError);
                    return false;
                }
            }
        }
        
        return true;
    }

    /**
     * Método de nivel 2: A partir de la palabra secreta y una respuesta del
     * usuario proporciona la pista correspondiente
     *
     * @param palabraSecreta La palabra secreta generada
     * @param respuesta La respuesta del usuario para adivinar la palabra
     * secreta
     * @return La pista generada a raíz de la comparación entre la palabra
     * secreta y la respuesta
     */
    public String generarPista(String palabraSecreta, String respuesta) {

        String pista = "";
        for (Integer i = 0; i < palabraSecreta.length(); i++) {
            String letra = String.valueOf(respuesta.charAt(i));

            if (respuesta.charAt(i) == palabraSecreta.charAt(i)) {
                pista += "0";
            } else if (palabraSecreta.contains(letra)) {
                pista += "X";
            } else {
                pista += "-";
            }
        }
        return pista;
    }

    /**
     * Método de nivel 2: Indica si la pista proporcionada es la ganadora o no
     *
     * @param pista La pista a analizar
     * @return CIERTO si es la pista ganadora (00000) o falso en cualquier otro
     * caso
     */
    public Boolean darRespuesta(String pista) {

        Boolean esPistaGanadora = pista.equals("00000");

        System.out.print("La respuesta es [" + pista + "].");

        if (!esPistaGanadora) {
            System.out.println(" Continúa intentándolo");
        } else {
            System.out.println(" ¡Has acertado!");
        }
        return esPistaGanadora;
    }

    /**
     * Genera una palabra secreta
     *
     * @return La palabra secreta generada
     */
    public String generarPalabraSecreta(Integer tamaño) {

        return montarPalabra(tamaño);
    }

    /**
     * Pide al usuario que introduzca una palabra para adivinar la palabra
     * secreta
     *
     * @return La palabra inntroducida por el usuario cumpliendo con el formato
     * establecido
     */
    public String pedirRespuestaEnFormatoValido(Integer tamaño) {

        String respuestaUsuario;
        Boolean esFormatoCorrecto;
        do {
            respuestaUsuario = leerRespuesta(tamaño);
            esFormatoCorrecto = comprobarFormato(respuestaUsuario, tamaño);
        } while (!esFormatoCorrecto);

        return respuestaUsuario;
    }

    /**
     * Indica si la respuesta introducida por el usuario es la palabra secreta
     *
     * @param palabraSecreta La palabra secreta
     * @param respuesta La respuesta introducida por el usuario
     * @return CIERTO si la palabra secreta y la respuesta son iguales, FALSO en
     * cualquier otro caso
     */
    public Boolean resolverRespuesta(String palabraSecreta, String respuesta) {

        String pista = generarPista(palabraSecreta, respuesta);
        Boolean esRespuestaCorrecta = darRespuesta(pista);

        return esRespuestaCorrecta;
    }

    /*--------------------------------------------------------------------------/
                                    MEJORAS
    /--------------------------------------------------------------------------*/

    /**
     * MEJORA AÑADIR TAMAÑO
     *
     * @param leerTamaño Leer el tamaño
     * @param comprobarTamaño Devuelve un valor booleano, para comprobar si esta
     * entre 1 y 8
     * @return CIERTO si el número se encuentra entre 1 y 8, FALSO si no
     * @param validarTamaño Devuelve el valor introducido una vez haya pasado el
     * filtro
     *
     */
    public Integer leerTamaño() {
        System.out.print("¿Qué tamaño de palabra quieres?: ");

        return scanner.nextInt();
    }

    public Boolean comprobarTamaño(Integer respuestaTamaño) {

        if (respuestaTamaño >= 1 && respuestaTamaño <= 8) {
            return true;
        } else {
            return false;
        }

    }

    public Integer validarTamaño() {

        Integer respuestaTamaño;
        Boolean tamañoCorrecto;

        do {
            respuestaTamaño = leerTamaño();
            tamañoCorrecto = comprobarTamaño(respuestaTamaño);
        } while (!tamañoCorrecto);

        return respuestaTamaño;

    }

    /**
     * MEJORA AÑADIR INTENTOS
     *
     * @param leerIntentos Leer el tamaño
     * @param validarIntentos Devuelve un valor booleano, para comprobar si esta
     * entre 1 y 8
     * @return CIERTO si el número se encuentra entre 1 y 8, FALSO si no
     * @param validarIntentos Devuelve el valor introducido una vez haya pasado
     * el filtro
     */
    public Integer leerIntentos() {
        System.out.print("¿Cuántos intentos máximo?: ");

        return scanner.nextInt();
    }

    public Boolean comprobarIntentos(Integer respuestaIntentos) {

        return respuestaIntentos >= 5 && respuestaIntentos <= 15;
    }

    public Integer validarIntentos() {

        Integer respuestaIntentos;
        Boolean intentosCorrecto;

        do {
            respuestaIntentos = leerIntentos();
            intentosCorrecto = comprobarIntentos(respuestaIntentos);
        } while (!intentosCorrecto);

        return respuestaIntentos;
    }

    /**
     * MEJORA AÑADIR DIFICULTAD
     *
     * @param leerIntentos Leer el tamaño
     * @param validarIntentos Devuelve un valor booleano, para comprobar si esta
     * entre 1 y 8
     * @return CIERTO si el número se encuentra entre 1 y 8, FALSO si no
     * @param validarIntentos Devuelve el valor introducido una vez haya pasado
     * el filtro
     */
    public Integer leerDificultad() {
        System.out.print("Elige el nivel de dificultad (1:fácil, 2:normal, 3:profesional): ");

        return scanner.nextInt();
    }

    public Boolean comprobarDificultad(Integer dificultad) {
        return dificultad >= 1 && dificultad <= 3;
    }

    public Integer validarDificultad() {
        Integer respuestaDificultad;
        Boolean dificultadCorrecto;

        do {
            respuestaDificultad = leerDificultad();
            dificultadCorrecto = comprobarDificultad(respuestaDificultad);
        } while (!dificultadCorrecto);

        return respuestaDificultad;
    }

}
