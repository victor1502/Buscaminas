package com.example.buscaminas;

public class Casilla {

    public String contenido = "";
    public boolean destapada = false;
    public int left,top,right,bottom;

    public void PosicionCasilla(int left,int top, int right, int bottom){
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public boolean clickDentroCasilla(int x, int y){
        if(left<x && right>x && top<y && bottom>y){
            return true;
        }
        else{
            return false;
        }
    }
}
