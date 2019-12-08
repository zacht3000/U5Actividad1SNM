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
        
        String palabraSecreta = generarPalabraSecreta();
        String respuestaUsuario;
        
        System.out.println(palabraSecreta);
        Boolean seHaAcertado;
        
        do {
            
            respuestaUsuario = pedirRespuestaEnFormatoValido();
            seHaAcertado = resolverRespuesta(palabraSecreta, respuestaUsuario);
        }while(!seHaAcertado);
    }

    /**
     * Método de nivel 3: Genera una letra aleatoria
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
     * @return la palabra aleatoria de longitud cinco
     */
    
    public String montarPalabra() {

        String palabra = "";

        for (Integer i = 0; i < 5; i++) {
            palabra += generarLetraAleatoria();
        }

        return palabra;
    }

    /**
     * Método de nivel 2: Pide al usuario que introduzca una cadena de texto
     * @return La cadena de texto introducida por el usuario
     */
    public String leerRespuesta() {
        System.out.print("Escribe 5 letras minúsculas: ");
        return scanner.nextLine();
    }

    /**
     * Método de nivel 2: Indica si la respuesta proporcionada cumple con el formato especificado
     * @param respuesta La respuesta a analizar su formato
     * @return CIERTO si es un formato válido (5 letras y minúsculas), FALSO en cualquier otro caso
     */
    public Boolean comprobarFormato(String respuesta) {

        if (respuesta.length() != 5) {
            return false;
        } else {
            for (Integer i = 0; i < respuesta.length(); i++) {

                Character letraRespuesta = respuesta.charAt(i);
                if (letraRespuesta < 'a' || letraRespuesta > 'z') {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Método de nivel 2: A partir de la palabra secreta y una respuesta del usuario proporciona la pista correspondiente
     * @param palabraSecreta La palabra secreta generada
     * @param respuesta La respuesta del usuario para adivinar la palabra secreta
     * @return La pista generada a raíz de la comparación entre la palabra secreta y la respuesta
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
     * @param pista La pista a analizar
     * @return CIERTO si es la pista ganadora (00000) o falso en cualquier otro caso
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
     * @return La palabra secreta generada
     */
    public String generarPalabraSecreta() {

        return montarPalabra();
    }

    /**
     * Pide al usuario que introduzca una palabra para adivinar la palabra secreta
     * @return La palabra inntroducida por el usuario cumpliendo con el formato establecido
     */
    public String pedirRespuestaEnFormatoValido() {

        String respuestaUsuario;
        Boolean esFormatoCorrecto;

        do {

            respuestaUsuario = leerRespuesta();
            esFormatoCorrecto = comprobarFormato(respuestaUsuario);
        } while (!esFormatoCorrecto);

        return respuestaUsuario;
    }
      /**
     * Indica si la respuesta introducida por el usuario es la palabra secreta
     * @param palabraSecreta La palabra secreta
     * @param respuesta La respuesta introducida por el usuario
     * @return CIERTO si la palabra secreta y la respuesta son iguales, FALSO en cualquier otro caso
     */
    public Boolean resolverRespuesta(String palabraSecreta, String respuesta) {

        String pista = generarPista(palabraSecreta, respuesta);
        Boolean esRespuestaCorrecta = darRespuesta(pista);

        return esRespuestaCorrecta;
    }
}
