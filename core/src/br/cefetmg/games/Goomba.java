package br.cefetmg.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author fegemo
 */
public class Goomba {
    
    public Sprite goombaSprite;
    public Texture player, spriteSheet;
    public Animation animacaoCorrente, animacaoEsquerda, animacaoDireita, animacaoCima, animacaoBaixo;
    public TextureRegion[][] quadrosAnimacao;
    public int x,y;
    float tempoAnimacao;
    
    public Goomba(Texture player, Texture spriteSheet) {
        // cria as regiões para cada quadro de animação
        // cria e configura as animações (andar para esquerda, direita, 
        // cima, baixo)
        goombaSprite = new Sprite(player);
        this.spriteSheet = spriteSheet; 
        quadrosAnimacao = TextureRegion.split(spriteSheet, 21, 24);
        // Define as animações
        animacaoBaixo = new Animation(0.3f, new TextureRegion[] {
          quadrosAnimacao[0][0], // 1ª linha, 1ª coluna
          quadrosAnimacao[0][1], // idem, 2ª coluna
          quadrosAnimacao[0][2],
          quadrosAnimacao[0][3],
          quadrosAnimacao[0][4]
        });
        animacaoBaixo.setPlayMode(Animation.PlayMode.LOOP);
        
        animacaoDireita = new Animation(0.3f, new TextureRegion[] {
          quadrosAnimacao[1][0], // 1ª linha, 1ª coluna
          quadrosAnimacao[1][1], // idem, 2ª coluna
          quadrosAnimacao[1][2],
          quadrosAnimacao[1][3],
          quadrosAnimacao[1][4]
        }); 
        animacaoDireita.setPlayMode(Animation.PlayMode.LOOP);
        
        animacaoCima = new Animation(0.3f, new TextureRegion[] {
          quadrosAnimacao[2][0], // 1ª linha, 1ª coluna
          quadrosAnimacao[2][1], // idem, 2ª coluna
          quadrosAnimacao[2][2],
          quadrosAnimacao[2][3],
          quadrosAnimacao[2][4]
        });
        animacaoCima.setPlayMode(Animation.PlayMode.LOOP);
        
        animacaoEsquerda = new Animation(0.3f, new TextureRegion[] {
          quadrosAnimacao[3][0], // 1ª linha, 1ª coluna
          quadrosAnimacao[3][1], // idem, 2ª coluna
          quadrosAnimacao[3][2],
          quadrosAnimacao[3][3],
          quadrosAnimacao[3][4]
        });
        animacaoEsquerda.setPlayMode(Animation.PlayMode.LOOP);
        
        // Inicializa as variáveis
        tempoAnimacao = 0;
        x = 0;
        y = 12;
        animacaoCorrente = animacaoBaixo;
    }
    
    public void update() {

        // verifica se a seta ← está pressionada
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            animacaoCorrente = animacaoEsquerda;
            if (x > 0){
                x = x - 1;
                goombaSprite.setPosition(x, y);
            }
            //velocidade.x = -1;
        }
        // verifica se a seta direita está pressionada
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            animacaoCorrente = animacaoDireita;
            if (x < Gdx.graphics.getWidth() - goombaSprite.getWidth()){
                x = x + 1;
                goombaSprite.setPosition(x, y);
            }
        }
        // verifica se a seta para cima está pressionada
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            animacaoCorrente = animacaoCima;
            if (y < Gdx.graphics.getHeight() - goombaSprite.getHeight()){
                y = y + 1;
                goombaSprite.setPosition(x, y);
            }
        }
        // verifica se a seta para baixa está pressionada
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            animacaoCorrente = animacaoBaixo;
            if (y > 0){
                y = y - 1;
                goombaSprite.setPosition(x, y);
            }
        }
        
    }
    
    public void render(SpriteBatch batch) {
        tempoAnimacao = tempoAnimacao + Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animacaoCorrente.getKeyFrame(tempoAnimacao);
        batch.draw(currentFrame,x,y);// O Sprite "se desenha"
        //goombaSprite.draw(batch);
    }
}
