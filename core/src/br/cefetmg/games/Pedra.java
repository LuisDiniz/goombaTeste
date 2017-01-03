package br.cefetmg.games;

import com.badlogic.gdx.graphics.Texture;

public class Pedra {
    
    private float x;
    private float y;
    private float fimAnimacaoX;
    private boolean visivel;
    private final Texture TEXTURA_PEDRA = new Texture("Pedra.png");
    private static final int VELOCIDADE_PEDRA = 2;
    
    public Pedra(){
        this.x = 0;
        this.y = 0;
        visivel = false;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }    

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }  

    public Texture getTEXTURA_PEDRA() {
        return TEXTURA_PEDRA;
    }
    
    public void ativarArmadilha(float x, float y, float larguraCamera){
        this.x = x + larguraCamera / 2f;
        this.y = y;
        fimAnimacaoX = this.x - larguraCamera;
        visivel = true;
    }
    
    public void animacaoPedra(){
        if (this.x >= fimAnimacaoX)
            this.x = this.x - VELOCIDADE_PEDRA;
        else
            visivel = false;
    }
           
}
