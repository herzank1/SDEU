/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;

/**
 *
 * @author HP
 */
public class GeneralUtils {

    // Método para convertir una imagen a texto (Base64)
    public static String imageToBase64(BufferedImage image, String formatName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, formatName, baos);
            byte[] bytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(bytes); // Convertir a Base64
        } catch (IOException e) {
            return null;
        }
    }

    // Método para convertir texto (Base64) de vuelta a imagen
    public static BufferedImage base64ToImage(String base64String) {
        byte[] bytes = Base64.getDecoder().decode(base64String); // Decodificar Base64
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        try {
            return ImageIO.read(bais); // Leer imagen desde el ByteArrayInputStream
        } catch (IOException e) {
            return null;
        }
    }

    public static void printBanner() {
        String archivo = "banner.txt";  // Ruta del archivo

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            // Leer línea por línea el archivo y mostrar en consola
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }

    }

    public static BufferedImage byteArrayToBufferedImage(byte[] imageData) {
        BufferedImage image = null;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageData)) {
            image = ImageIO.read(bais);
        } catch (IOException e) {
            // Manejo de excepciones

        }
        return image;
    }

}
