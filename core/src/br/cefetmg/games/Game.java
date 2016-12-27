package br.cefetmg.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Game extends ApplicationAdapter {

    private SpriteBatch batch;
    private Goomba goomba;
    private Texture map, spriteSheet, player;
    private OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        map = new Texture("map.png");
        spriteSheet = new Texture("goomba-spritesheet.png");
        player = new Texture("goomba.png");
        goomba = new Goomba(player, spriteSheet);
        camera = new OrthographicCamera(256,200);
        //camera.position.set(0,0,0);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
        
        System.out.println("Map Width:" + map.getWidth());
        System.out.println("Goomba Width:" + goomba.goombaSprite.getWidth());
        System.out.println("GdxGraphicsGetWidth: " + Gdx.graphics.getWidth());              
        
    }

    public void update() {
        //goomba.update();
        
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
            //if (goomba.x < Gdx.graphics.getWidth() - goomba.goombaSprite.getWidth()){
            if (goomba.x < (map.getWidth() - goomba.goombaSprite.getWidth() - camera.viewportWidth / 2f)){ //??? Como a Camera está sempre centralizada tem que tirar metade ???
                goomba.x = goomba.x + 1;
                System.out.println("Camera x: " + camera.position.x);                
                System.out.println("Goomba x:" + goomba.x);
                               
                // Verifica se o goomba está na metade da camera
                if ((goomba.x >= (camera.position.x /2f)) && ((camera.position.x + camera.viewportWidth) < map.getWidth())){
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
        // Desenha a camera 
        batch.setProjectionMatrix(camera.combined);
        // Finaliza a comunicação com a GPU
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
