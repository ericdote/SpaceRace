package cat.xtec.ioc.objects;

import cat.xtec.ioc.helpers.AssetManager;
import cat.xtec.ioc.utils.Settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * Created by Eric on 16/03/2017.
 */

public class Bala extends Actor{

    private Rectangle balaCollision;
    private Vector2 position;
    private int width, height;
    private int direccion;
    private ScrollHandler scrollHandler;

    public Bala(float x, float y, int width, int height, ScrollHandler scrollHandler){
        position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.scrollHandler = scrollHandler;
        balaCollision = new Rectangle();
        setBounds(position.x, position.y, width, height);

    }

    public TextureRegion getBalaTexture(){ return AssetManager.bala; }


    @Override
    public void act(float delta) {
        super.act(delta);
        this.position.x += 60*delta;
        balaCollision.set(position.x, position.y, width, height+2);
        colisionBalaAsteroide(scrollHandler.getAsteroids());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(getBalaTexture(), position.x, position.y, width, height);
    }
    
    public boolean colisionBalaAsteroide(ArrayList<Asteroid> asteroides){
        for (Asteroid asteroid: asteroides) {
            if(asteroid.collidesBala(this)){
                Gdx.app.log("Colision", "colision");
                this.remove();
                asteroid.setVisible(false);
                //asteroid.collides(); //QUE DEJE DE COLISIONAR

                return true;
            }
        }
        return false;
    }

    public Rectangle getBalaCollision() {return balaCollision;}
}
