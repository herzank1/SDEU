/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects;

/**
 *
 * @author DeliveryExpress
 */


public class Position {

  private double latitud;
  private double longitud;

  // Constructor
  public Position(double latitud, double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  // Constructor que recibe un String en formato "latitud, longitud"
  public Position(String coordenadas) throws IllegalArgumentException {
    // Dividir el string en latitud y longitud
    String[] partes = coordenadas.split(",\\s*");

    if (partes.length != 2) {
      throw new IllegalArgumentException("Formato incorrecto. Debe ser 'latitud, longitud'");
    }

    try {
      this.latitud = Double.parseDouble(partes[0]);
      this.longitud = Double.parseDouble(partes[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Los valores de latitud y longitud deben ser números válidos.");
    }
  }

  // Getters
  public double getLatitud() {
    return latitud;
  }

  public double getLongitud() {
    return longitud;
  }

  // Setters
  public void setLatitud(double latitud) {
    this.latitud = latitud;
  }

  public void setLongitud(double longitud) {
    this.longitud = longitud;
  }

  // Método para obtener la posición en formato "latitud, longitud"
  @Override
  public String toString() {
    return latitud + ", " + longitud;
  }

  // Método para validar si las coordenadas son válidas
  public boolean isValid() {
    return latitud >= -90 && latitud <= 90 && longitud >= -180 && longitud <= 180;
  }


  // Método para generar la URL de Google Maps
  public String getUrlGoogleMapsMark() {
    return "https://www.google.com/maps?q=" + latitud + "," + longitud;
  }


  // Método para generar URL de navegación a un destino
  /***
   *
   * @param destino coordenadas string
   * @return
   */
  public String getUrlNavigateTo(String destino) {
    // Crear la URL de Google Maps para navegación
    return "https://www.google.com/maps/dir/?api=1&origin=" + latitud + "," + longitud + "&destination=" + destino;
  }

  public int calcDistanceInKmTo(Position position) {
    final int R = 6371; // Radio de la Tierra en kilómetros
    double latDistance = Math.toRadians(position.latitud - this.latitud);
    double lonDistance = Math.toRadians(position.longitud - this.longitud);

    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
               Math.cos(Math.toRadians(this.latitud)) * Math.cos(Math.toRadians(position.latitud)) *
               Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = R * c;

    return (int) Math.round(distance); // Redondea la distancia a un entero
}



}

