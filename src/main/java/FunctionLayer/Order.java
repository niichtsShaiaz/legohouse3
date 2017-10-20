/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

public class Order {
    
    private int id;
    private String date;
    private Pieces pieces;

    public Order(int id, String date, Pieces stykList) {
        this.id = id;
        this.date = date;
        this.pieces = stykList;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Pieces getStykList() {
        return pieces;
    }
    
    
    
}
