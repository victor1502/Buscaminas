package com.example.buscaminas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Jugar extends AppCompatActivity{

    Casilla[][] casillas = new Casilla[8][8];
    private Tablero tablero;
    boolean clickable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        RelativeLayout layout = findViewById(R.id.layout);
        tablero = new Tablero(this);
        layout.addView(tablero);

        tablero.IniciarJuego();
    }

    public void botonReiniciar(View view) {
        tablero.IniciarJuego();
    }

    class Tablero extends View implements View.OnTouchListener{

        public Tablero(Context context){
            super(context);
            setFocusable(true);
            this.setOnTouchListener(this);
        }

        protected void onDraw(Canvas canvas){
            Log.i("coords","tablero");
            int ancho = canvas.getWidth();
            int alto = canvas.getHeight();

            Paint pincelBorde = new Paint();
            pincelBorde.setStrokeWidth(5);
            pincelBorde.setStyle(Paint.Style.STROKE);
            Paint pincelFillTapado = new Paint();
            pincelFillTapado.setStyle(Paint.Style.FILL);
            pincelFillTapado.setColor(Color.argb(255,166,166,166));
            Paint pincelFillDestapado = new Paint();
            pincelFillDestapado.setStyle(Paint.Style.FILL);
            pincelFillDestapado.setColor(Color.argb(255,230,230,230));

            RectF rect1 = new RectF(29,353,ancho-29,alto-140);
            int left = 29;
            int top = 353;
            int right = ancho-29;
            int bottom = alto-140;
            int ladoCasilla = 128;
            int posLeft = 0;
            int posRight = ladoCasilla*7;
            int posTop = 0;
            int posBottom = ladoCasilla*7;
            canvas.drawRect(rect1,pincelBorde);

            pincelBorde.setStrokeWidth(3);

            for(int i = 0; i<8;i++){
                for(int j=0;j<8;j++){
                    RectF rect2 = new RectF(left+posLeft,top+posTop,right-posRight,bottom-posBottom);
                    canvas.drawRect(rect2,pincelBorde);
                    if(casillas[i][j].destapada){
                        canvas.drawRect(rect2,pincelFillDestapado);
                    }else{
                        canvas.drawRect(rect2,pincelFillTapado);
                    }
                    casillas[i][j].PosicionCasilla(left+posLeft,top+posTop,right-posRight,bottom-posBottom);
                    posLeft += ladoCasilla;
                    posRight -= ladoCasilla;
                    if(posLeft == ladoCasilla*8){
                        posLeft = 0;
                    }
                    if(posRight == -128) {
                        posRight = ladoCasilla * 7;
                    }

                    if(casillas[i][j].destapada) {
                        if (casillas[i][j].contenido.equals("1")) {
                            Paint uno = new Paint();
                            uno.setTextSize(80);
                            uno.setARGB(255, 0, 0, 255);

                            canvas.drawText(casillas[i][j].contenido, j * ladoCasilla + (ladoCasilla / 2),
                                    i * ladoCasilla + top + (ladoCasilla / 2) + 30, uno);
                        } else if (casillas[i][j].contenido.equals("2")) {
                            Paint dos = new Paint();
                            dos.setTextSize(80);
                            dos.setARGB(255, 0, 255, 0);

                            canvas.drawText(casillas[i][j].contenido, j * ladoCasilla + (ladoCasilla / 2),
                                    i * ladoCasilla + top + (ladoCasilla / 2) + 30, dos);
                        } else if (casillas[i][j].contenido.equals("3")) {
                            Paint tres = new Paint();
                            tres.setTextSize(80);
                            tres.setARGB(255, 255, 0, 0);

                            canvas.drawText(casillas[i][j].contenido, j * ladoCasilla + (ladoCasilla / 2),
                                    i * ladoCasilla + top + (ladoCasilla / 2) + 30, tres);
                        } else if (casillas[i][j].contenido.equals("4")) {
                            Paint cuatro = new Paint();
                            cuatro.setTextSize(80);
                            cuatro.setARGB(255, 0, 0, 153);

                            canvas.drawText(casillas[i][j].contenido, j * ladoCasilla + (ladoCasilla / 2),
                                    i * ladoCasilla + top + (ladoCasilla / 2) + 30, cuatro);
                        } else if (casillas[i][j].contenido.equals("5")) {
                            Paint cinco = new Paint();
                            cinco.setTextSize(80);
                            cinco.setARGB(255, 102, 0, 0);

                            canvas.drawText(casillas[i][j].contenido, j * ladoCasilla + (ladoCasilla / 2),
                                    i * ladoCasilla + top + (ladoCasilla / 2) + 30, cinco);
                        } else if (casillas[i][j].contenido.equals("6")) {
                            Paint seis = new Paint();
                            seis.setTextSize(80);
                            seis.setARGB(255, 0, 163, 204);

                            canvas.drawText(casillas[i][j].contenido, j * ladoCasilla + (ladoCasilla / 2),
                                    i * ladoCasilla + top + (ladoCasilla / 2) + 30, seis);
                        } else if (casillas[i][j].contenido.equals("B")) {
                            Paint bomba = new Paint();
                            bomba.setTextSize(80);
                            bomba.setARGB(255, 0, 0, 0);

                            canvas.drawText(casillas[i][j].contenido, j * ladoCasilla + (ladoCasilla / 2),
                                    i * ladoCasilla + top + (ladoCasilla/2) + 30, bomba);
                        }
                    }
                }
                posTop += ladoCasilla;
                posBottom -= ladoCasilla;
            }
        }

        public void IniciarJuego(){
            clickable = true;
            Log.i("coords","iniciar");
            crearBombas();
            rellenarCasillas();
            tablero.invalidate();

        }

        public void crearBombas()
        {
            Log.i("coords","crear");
            for(int i=0;i<8;i++) {
                for(int j=0;j<8;j++) {
                    casillas[i][j] = new Casilla();
                }
            }
            int minas = 10;
            int numX, numY;
            while(minas>0)
            {
                numX = (int) Math.floor(Math.random()*8);
                numY = (int) Math.floor(Math.random()*8);
                if(!casillas[numX][numY].contenido.equals("B"))
                {
                    casillas[numX][numY].contenido = "B";
                    minas--;
                }
            }
        }

        public void rellenarCasillas()
        {
            Log.i("coords","rellenar");
            for(int i=0;i<8;i++)
            {
                for(int j=0;j<8;j++)
                {
                    if(!casillas[i][j].contenido.equals("B")) {
                        casillas[i][j].contenido = numeroBombas(i,j);
                    }
                }
            }
        }

        public String numeroBombas(int fila,int columna) {
            Log.i("coords","numero");
            int numeroBombas = 0;

            if(fila-1 >=0 && columna-1 >= 0) {
                if(casillas[fila-1][columna-1].contenido.equals("B")) {
                    numeroBombas++;
                }
            }
            if(fila-1 >=0) {
                if(casillas[fila-1][columna].contenido.equals("B")) {
                    numeroBombas++;
                }
            }
            if(fila-1 >=0 && columna+1 <= 7) {
                if(casillas[fila-1][columna+1].contenido.equals("B")) {
                    numeroBombas++;
                }
            }
            if(columna-1 >= 0) {
                if(casillas[fila][columna-1].contenido .equals("B")) {
                    numeroBombas++;
                }
            }
            if(columna+1 <= 7) {
                if(casillas[fila][columna+1].contenido.equals("B")) {
                    numeroBombas++;
                }
            }
            if(fila+1 <=7 && columna-1 >= 0) {
                if(casillas[fila+1][columna-1].contenido.equals("B")) {
                    numeroBombas++;
                }
            }
            if(fila+1 <=7) {
                if(casillas[fila+1][columna].contenido.equals("B")) {
                    numeroBombas++;
                }
            }
            if(fila+1 <=7 && columna+1 <= 7) {
                if(casillas[fila+1][columna+1].contenido.equals("B")) {
                    numeroBombas++;
                }
            }

            return ""+numeroBombas;
        }

        public void destaparAlrededores(int fila, int columna){
            Log.i("coords","destapar");
            if(fila >= 0 && fila < 8 && columna >= 0 && columna < 8){
                Log.i("coords",""+casillas[fila][columna].contenido+"/"+casillas[fila][columna].destapada);
                if(casillas[fila][columna].contenido.equals("0") && !casillas[fila][columna].destapada){
                    Log.i("coords","hola");
                    casillas[fila][columna].destapada = true;

                    destaparAlrededores(fila-1,columna-1);
                    destaparAlrededores(fila-1,columna);
                    destaparAlrededores(fila-1,columna+1);
                    destaparAlrededores(fila,columna-1);
                    destaparAlrededores(fila,columna+1);
                    destaparAlrededores(fila+1,columna-1);
                    destaparAlrededores(fila+1,columna);
                    destaparAlrededores(fila+1,columna+1);

                }
                else if(!casillas[fila][columna].contenido.equals("0") && !casillas[fila][columna].contenido.equals("B")){
                    Log.i("coords","adios");
                    casillas[fila][columna].destapada = true;
                }
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(clickable) {

                Log.i("coords", "onTouch");
                int x = (int) event.getX();
                int y = (int) event.getY();

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (casillas[i][j].clickDentroCasilla(x, y)) {

                            if (casillas[i][j].contenido.equals("B")) {
                                Toast.makeText(tablero.getContext(), "You lose", Toast.LENGTH_LONG).show();
                                Log.i("oops", "perdiste");
                                clickable = false;
                            } else if (casillas[i][j].contenido.equals("0")) {
                                Log.i("cero", "" + i + " " + j);
                                destaparAlrededores(i, j);
                            }
                            casillas[i][j].destapada = true;
                            tablero.invalidate();

                        }
                    }
                }
                if (ganar()) {
                    Toast.makeText(tablero.getContext(), "You Win!!", Toast.LENGTH_LONG).show();
                    Log.i("olee", "ganaste");
                    clickable = false;
                    terminarPartida();
                }
            }
            return true;
        }


        public boolean ganar(){
            Log.i("coords","ganar");
            int contador = 0;
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(casillas[i][j].destapada){
                        contador++;
                    }
                }
            }

            if(contador == (8*8)-10){
                return true;
            }
            else{
                return false;
            }
        }

        public void terminarPartida(){
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(casillas[i][j].contenido.equals("B")){
                        casillas[i][j].destapada = true;
                    }
                }
            }
            tablero.invalidate();
        }
    }
}