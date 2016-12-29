package br.cefetmg.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class Game extends ApplicationAdapter {

    private final static int PRINCESA_POS_X = 500;
    private final static int PRINCESA_POS_Y = 12;
    
    private SpriteBatch batch;
    private Goomba goomba, princesa;
    private Texture map, spriteSheet, texturaPlayer, texturaPrincesa, setas;
    private OrthographicCamera camera;    
    private boolean comecoJogo = true, apareceSeta = true;
    private Task moverCamera, mostrarObjetivo;

    @Override
    public void create() {
        batch = new SpriteBatch();
        map = new Texture("map.png");
        spriteSheet = new Texture("goomba-spritesheet.png");
        texturaPlayer = new Texture("goomba.png");
        texturaPrincesa = new Texture("goomba.png");
        setas = new Texture("seta.png");
        goomba = new Goomba(texturaPlayer, spriteSheet);
                
        princesa = new Goomba(texturaPrincesa, spriteSheet);                
        princesa.x = PRINCESA_POS_X;
        princesa.y = PRINCESA_POS_Y;
        
        camera = new OrthographicCamera(256,200);              
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();                  
    }

    public void update() {
        // Animação para mover a camera do objetivo para o a posição do jogador
        // Verifica se é o começo do jogo
        if (comecoJogo) {
            mostrarObjetivo(princesa.x, princesa.y);
            moverCameraObjetivoJogador(princesa.x, princesa.y);
            comecoJogo = false;
        }
        
        // verifica se a seta ← está pressionada
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {          
                       
            goomba.animacaoCorrente = goomba.animacaoEsquerda;
            if (goomba.x > 0){
                goomba.x = goomba.x - 1;
                camera.position.x = camera.position.x - 1;
                goomba.goombaSprite.setPosition(goomba.x, goomba.y);
            }
        }
        // verifica se a seta direita está pressionada
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            goomba.animacaoCorrente = goomba.animacaoDireita;
            if (goomba.x < (map.getWidth() - goomba.goombaSprite.getWidth() - camera.viewportWidth / 2f)){ //??? Como a Camera está sempre centralizada tem que tirar metade ???
            //if (goomba.x < (map.getWidth() - goomba.goombaSprite.getWidth())){
                goomba.x = goomba.x + 1;
                               
                // Verifica se o goomba está na metade da camera
                //if ((goomba.x >= (camera.position.x /2f)) && ((camera.position.x + camera.viewportWidth) < map.getWidth())){
                if ((camera.position.x + camera.viewportWidth) < map.getWidth()){
                    camera.position.x = camera.position.x + 1;
                }
                goomba.goombaSprite.setPosition(goomba.x, goomba.y);
            }
        }
        // verifica se a seta para cima está pressionada
        else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            goomba.animacaoCorrente = goomba.animacaoCima;
            if (goomba.y < Gdx.graphics.getHeight() - goomba.goombaSprite.getHeight()){
                goomba.y = goomba.y + 1;
                goomba.goombaSprite.setPosition(goomba.x, goomba.y);
            }
        }
        // verifica se a seta para baixa está pressionada
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            goomba.animacaoCorrente = goomba.animacaoBaixo;
            if (goomba.y > 0){
                goomba.y = goomba.y - 1;
                goomba.goombaSprite.setPosition(goomba.x, goomba.y);
            }
        }
        camera.update();

    }
    
    @Override
    public void render() {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        update();
        // Começa a comunicação com a GPU e realiza todos os desenhos
        batch.begin();
        // Desenha o mapa
        batch.draw(map, 0, 0);
        // Desenha o Goomba
        goomba.render(batch);
        // Desenha a princesa
        princesa.render(batch);
        if (apareceSeta)
            batch.draw(setas, princesa.x, princesa.y + princesa.goombaSprite.getHeight() + 5);
        // Desenha a camera 
        batch.setProjectionMatrix(camera.combined);
        // Finaliza a comunicação com a GPU
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
    
    public void moverCameraObjetivoJogador(final int objetivoX, final int objetivoY){
        
        moverCamera = new Task(){ 
                        @Override
                         public void run() {
                             camera.position.x = camera.position.x - 5;
                         }
        };
        
        Timer.schedule(moverCamera, 3f, 0.05f, (int) (objetivoX-(camera.viewportWidth/2))/5 - 1);
    }

    private void mostrarObjetivo(final int objetivoX, final int objetivoY) {
        //Posiciona a cameera no objetivo;
        camera.position.x = objetivoX;
        
        // Inicializa a task mostrarObjetivo
        mostrarObjetivo = new Task(){
                            @Override
                            public void run() {
                                apareceSeta = !apareceSeta;
                            }
        };        
        // Realiza o efeito das setas "piscando" na tela
        Timer.schedule(mostrarObjetivo, 0, 0.5f, 6);      
    }

}
