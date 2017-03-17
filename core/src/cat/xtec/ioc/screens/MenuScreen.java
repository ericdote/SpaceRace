package cat.xtec.ioc.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import cat.xtec.ioc.SpaceRace;
import cat.xtec.ioc.helpers.AssetManager;
import cat.xtec.ioc.utils.Settings;


public class MenuScreen implements Screen {

    private Stage stage;
    private SpaceRace game;
    private boolean touch = false;
    private Label.LabelStyle textStyle;
    private Label facil;
    private Label medio;
    private Label dificil;

    public MenuScreen(SpaceRace game) {

        this.game = game;

        // Creem la càmera de les dimensions del joc
        OrthographicCamera camera = new OrthographicCamera(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        // Posant el paràmetre a true configurem la càmera per a
        // que faci servir el sistema de coordenades Y-Down
        camera.setToOrtho(true);

        // Creem el viewport amb les mateixes dimensions que la càmera
        StretchViewport viewport = new StretchViewport(Settings.GAME_WIDTH, Settings.GAME_HEIGHT, camera);

        // Creem l'stage i assginem el viewport
        stage = new Stage(viewport);

        // Afegim el fons
        stage.addActor(new Image(AssetManager.background));

        // Creem la imatge de la nau i li assignem el moviment en horitzontal
        Image spacecraft = new Image(AssetManager.spacecraft);
        float y = Settings.GAME_HEIGHT / 3;
        spacecraft.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(Actions.moveTo(0 - spacecraft.getWidth(), y), Actions.moveTo(Settings.GAME_WIDTH, y, 5))));

        stage.addActor(spacecraft);

        // Creem l'estil de l'etiqueta i l'etiqueta
        textStyle = new Label.LabelStyle(AssetManager.font, null);
        facil = new Label("Facil", textStyle);
        medio = new Label("Medio", textStyle);
        dificil = new Label("Dificil", textStyle);

        // Creem el contenidor necessari per aplicar-li les accions
        Container containerFacil = new Container(facil);
        containerFacil.setTransform(true);
        containerFacil.center();
        containerFacil.setPosition(Settings.GAME_WIDTH/2+10, 30);
        Container containerMedio = new Container(medio);
        containerMedio.setTransform(true);
        containerMedio.center();
        containerMedio.setPosition(Settings.GAME_WIDTH/2+10, 60);
        Container containerDificil = new Container(dificil);
        containerDificil.setTransform(true);
        containerDificil.center();
        containerDificil.setPosition(Settings.GAME_WIDTH/2+10, 90);
        //containerFacil.addAction(Actions.touchable(true));
        stage.addActor(containerFacil);
        stage.addActor(containerMedio);
        stage.addActor(containerDificil);



    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        stage.draw();
        stage.act(delta);

        // Si es fa clic en la pantalla, canviem la pantalla
        if (Gdx.input.isTouched() && touch == true) {
            game.setScreen(new GameScreen(stage.getBatch(), stage.getViewport()));
            dispose();
        } else {
            touch = false;
        }
        //gdxinput.x
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
