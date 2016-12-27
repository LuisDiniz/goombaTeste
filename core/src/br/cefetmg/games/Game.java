package br.cefetmg.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {

    private SpriteBatch batch;
    private Goomba goomba;
    private Texture map, spriteSheet, player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        map = new Texture("map.png");
        spriteSheet = new Texture("goomba-spritesheet.png");
        player = new Texture("goomba.png");
        goomba = new Goomba(player, spriteSheet);
    }

    public void update() {
        goomba.update();
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
        // Finaliza a comunicação com a GPU
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
