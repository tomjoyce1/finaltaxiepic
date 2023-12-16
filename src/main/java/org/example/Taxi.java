package org.example;

public class Taxi {
    private String id;
    private String model;

    public Taxi(String id, String model) {
        this.id = id;
        this.model = model;
    }



   public String getLicensePlate(){
        return id;
   }

    public String getModel(){
        return model;
    }
}
