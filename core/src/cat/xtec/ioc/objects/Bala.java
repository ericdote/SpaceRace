package cat.xtec.ioc.objects;

import cat.xtec.ioc.helpers.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Eric on 16/03/2017.
 */

public class Bala extends Actor{

    private Rectangle balaCollision;
    private Vector2 position;
    private int width, height;
    private int direccion;

    public Bala(float x, float y, int width, int height){
        position = new Vector2(x, y);
        this.width = width;
        this.height = height;

        balaCollision = new Rectangle();
        setBounds(position.x, position.y, width, height);

    }

    public TextureRegion getBalaTexture(){ return AssetManager.bala; }


    @Override
    public void act(float delta) {
        super.act(delta);
        this.position.x += 60*delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(getBalaTexture(), position.x, position.y, width, height);
    }



}
