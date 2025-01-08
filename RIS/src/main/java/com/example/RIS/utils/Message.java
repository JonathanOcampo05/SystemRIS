package com.example.RIS.utils;

public class Message {
    private String text;
    private TypesResponse type;
    private Object result;

    // Constructor para mensajes simples
    public Message(String text, TypesResponse type) {
        this.text = text;
        this.type = type;
        this.result = null; // Asegúrate de que el resultado sea nulo si no se proporciona
    }

    // Constructor para mensajes con resultados
    public Message(Object result, String text, TypesResponse type) {
        this.result = result;
        this.text = text;
        this.type = type;
    }

    // Getters y setters
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TypesResponse getType() {
        return type;
    }

    public void setType(TypesResponse type) {
        this.type = type;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
