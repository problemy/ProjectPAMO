package com.example.smartarrium;
/*
* Class represents model of ESP8266 controller
*
* */
public class Controller {
    private static int  id = 0;
    private String apiKey;
    private String controllerName;
    private String ipAdress;



    private static String CONTROLLER_URL;
    public Controller(String apiKey, String controllerName, String ipAdress) {
        this.apiKey = apiKey;
        this.controllerName = controllerName;
        this.ipAdress = ipAdress;
        this.id++;


    }
    public static String getControllerUrl() {
        return CONTROLLER_URL;
    }
    public static int getId() {
        return id;
    }
    public static void setControllerUrl(String ipAdress, String controllerName ){
        CONTROLLER_URL = ""+ ipAdress + "/things/" + controllerName + "/";
    }
    public static void setId(int id) {
        Controller.id = id;
    }


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }




}
