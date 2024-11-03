package io.github.SoldierVsZombies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Iterator;

public class SpawnableTypeManager {

    public final int DEAD_WIDTH = 64;
    public final int DEAD_HEIGHT = 96;
    public final int GIFT_WIDTH = 100;
    public final int GIFT_HEIGHT = 100;
    private final ArrayList<SpawnableSprite> allTheSpawnableSprite = new ArrayList<>();
    private Sprite deadFrameZombie;
    private Sprite[] deadFrameSkull;
    private Sprite blackGift;



    SpawnableTypeManager() {
        loadDeadFrames();
    }

    public Sprite getDeadFrame(SpawnableSprite spawnableSprite) {
        if (spawnableSprite.getDeadType().equals(SpawnableType.DEAD_ZOMBIE)) return deadFrameZombie;
        if (spawnableSprite.getDeadType().equals(SpawnableType.DEAD_SKULL)) {
            if (spawnableSprite.isBeyondHalfLifeTime()) return deadFrameSkull[1];
            return deadFrameSkull[0];
        }
        // so deadType is BLACK_GIFT
        return blackGift;
    }

    public void addDead(SpawnableSprite spawnableSpriteToAdd) {
        allTheSpawnableSprite.add(spawnableSpriteToAdd);
    }

    public ArrayList<SpawnableSprite> getAllTheDead() {
        return allTheSpawnableSprite;
    }


    public void handleGravesCountDown() {
        Iterator<SpawnableSprite> deadsIterator = getAllTheDead().iterator();
        while (deadsIterator.hasNext()) {
            SpawnableSprite spawnableSprite = deadsIterator.next();
            if (spawnableSprite.isBeyondLifeTime()) {
                deadsIterator.remove();
            }
        }
    }


    void loadDeadFrames() {
        loadZombieDeadFrames();
        loadSkullDeadFrames();
        loadBlackGiftFrames();
    }

    private void loadBlackGiftFrames() {
        blackGift = new Sprite();
        Texture blackGiftFile = new Texture(Gdx.files.internal("images/blackGiftBox100x100.png"));
        blackGift = new Sprite(blackGiftFile, 0, 0,GIFT_WIDTH,GIFT_HEIGHT);
    }

    private void loadSkullDeadFrames() {
        deadFrameSkull = new Sprite[2];
        Texture deadSkullFile1 = new Texture(Gdx.files.internal("images/skull100x100-ex1.png"));
        Texture deadSkullFile2 = new Texture(Gdx.files.internal("images/skull100x100-ex2.png"));
        deadFrameSkull[0] = new Sprite(deadSkullFile1,0,0,DEAD_WIDTH,DEAD_HEIGHT);
        deadFrameSkull[1] = new Sprite(deadSkullFile2,0,0,DEAD_WIDTH,DEAD_HEIGHT);
    }

    private void loadZombieDeadFrames() {
        deadFrameZombie = new Sprite();
        Texture deadZombieFile = new Texture(Gdx.files.internal("images/grave64x96.png"));
        deadFrameZombie = new Sprite(deadZombieFile, 0, 0, DEAD_WIDTH, DEAD_HEIGHT);
    }
}
