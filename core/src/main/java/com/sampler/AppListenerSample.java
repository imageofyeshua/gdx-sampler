package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class AppListenerSample implements ApplicationListener {
    private static final Logger log = new Logger("AppListenerSample", Logger.DEBUG);
    private boolean renderInterrupted = true;

    @Override
    public void create() {
       // used to initialize game and load resources
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        log.debug("create");
    }

    @Override
    public void resize(int width, int height) {
        // used to handle setting a new screen size
        log.debug("resize() width= " + width + " height= " + height);
    }

    @Override
    public void render() {
        // used to update and render the game elements called 60 times per seconds
        if (renderInterrupted) {
            log.debug("render()");
            renderInterrupted = false;
        }
    }

    @Override
    public void pause() {
        // used to save game state when it loses focus
        log.debug("pause()");
        renderInterrupted = true;
    }

    @Override
    public void resume() {
        // used to handle the game to come back
        log.debug("resume()");
        renderInterrupted = true;
    }

    @Override
    public void dispose() {
        // used to free resources and cleanup
        log.debug("dispose()");
    }
}
