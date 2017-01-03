package br.cefetmg.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Pedra {
    
    private float x;
    private float y;
    private float fimAnimacaoX;
    private boolean visivel;
    private final Texture TEXTURA_PEDRA = new Texture("Pedra.png");
    private final Texture TEXTURA_PEDRA_SPIRTESHEET = new Texture("PedraSpritesheet.png");
    private static final int VELOCIDADE_PEDRA = 2;
    private Sprite pedraSprite;
    public Animation animacaoPedra;
    public TextureRegion[][] quadrosAnimacao;
    float tempoAnimacao;
    
    public Pedra(){
        this.x = 0;
        this.y = 0;
        visivel = false;
        pedraSprite = new Sprite(TEXTURA_PEDRA);
        quadrosAnimacao = TextureRegion.split(TEXTURA_PEDRA_SPIRTESHEET, 89, 44);
        // Define as animações
        animacaoPedra = new Animation(0.3f, new TextureRegion[] {
          quadrosAnimacao[0][0], // 1ª linha, 1ª coluna
          quadrosAnimacao[0][1], // idem, 2ª coluna
          quadrosAnimacao[0][2],
          quadrosAnimacao[0][3],
        });
        animacaoPedra.setPlayMode(Animation.PlayMode.LOOP);
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
    
    public void atualizarPosicaoPedra(){
        if (this.x >= fimAnimacaoX)
            this.x = this.x - VELOCIDADE_PEDRA;
        else
            visivel = false;
    }
    
    public void render(SpriteBatch batch){
        tempoAnimacao = tempoAnimacao + Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animacaoPedra.getKeyFrame(tempoAnimacao);
        batch.draw(currentFrame,x,y);// O Sprite "se desenha"
        atualizarPosicaoPedra();
    }
           
}
