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
public class GdxModuleSample implements ApplicationListener {

    private static final Logger log = new Logger(GdxModuleSample.class.getName(), Logger.DEBUG);

    @Override
    public void create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        log.debug("app=" + Gdx.app);
        log.debug("audio=" + Gdx.audio);
        log.debug("input=" + Gdx.input);
        log.debug("files=" + Gdx.files);
        log.debug("net=" + Gdx.net);
        log.debug("graphics=" + Gdx.graphics);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
