package com.hi5dev.physics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.HashMap;

public class PhysicsGame extends ApplicationAdapter {
  final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

  TextureAtlas textureAtlas;

  SpriteBatch batch;

  OrthographicCamera camera;

  ExtendViewport viewport;

  @Override
  public void create() {
    Box2D.init();

    batch = new SpriteBatch();

    camera = new OrthographicCamera();

    viewport = new ExtendViewport(800, 600, camera);

    textureAtlas = new TextureAtlas("sprites.txt");

    addSprites();
  }

  private void addSprites() {
    Array<AtlasRegion> regions = textureAtlas.getRegions();

    for (AtlasRegion region : regions) {
      Sprite sprite = textureAtlas.createSprite(region.name);

      sprites.put(region.name, sprite);
    }
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height, true);

    batch.setProjectionMatrix(camera.combined);
  }

  @Override
  public void render() {
    Gdx.gl.glClearColor(0.57f, 0.77f, 0.85f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();

    drawSprite("banana", 0, 0);
    drawSprite("cherries", 100, 100);

    batch.end();
  }

  private void drawSprite(String name, float x, float y) {
    Sprite sprite = sprites.get(name);

    sprite.setPosition(x, y);

    sprite.draw(batch);
  }

  @Override
  public void dispose() {
    textureAtlas.dispose();

    sprites.clear();
  }
}
