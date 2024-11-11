package io.github.SoldierVsZombies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.Random;

import static io.github.SoldierVsZombies.ShortLifeTimeSpriteType.*;

public class ShortLifeTimeSpriteTypeManager {

    private final ArrayList<ShortLifeTimeSprite> allShortLifeTimeSprites = new ArrayList<>();
    private Sprite deadFrameZombie;
    private Sprite[] deadFrameSkull;
    private Sprite blackGift;
    private Sprite[] portalShine;
    private Sprite wow;
    private Sprite[] woodFire;
    private Sprite[] flamingTorch;


    ShortLifeTimeSpriteTypeManager() {
        String javaVersion = System.getProperty("java.version");
        System.out.println("inside ShortLifeTimeSpriteManager Java version: " + javaVersion);
        loadSpawnAbleFrames();
    }

    public Sprite getShortLifeTimeFrame(ShortLifeTimeSprite shortLifetimeSprite) {
        Random random = new Random();
        if (shortLifetimeSprite.getShortLifeTimeSpriteType().equals(ShortLifeTimeSpriteType.DEAD_ZOMBIE))
            return deadFrameZombie;
        if (shortLifetimeSprite.getShortLifeTimeSpriteType().equals(WOW_YELL)) return wow;
        if (shortLifetimeSprite.getShortLifeTimeSpriteType().equals(ShortLifeTimeSpriteType.DEAD_SKULL))
            return shortLifetimeSprite.isBeyondHalfLifeTime() ? deadFrameSkull[1] : deadFrameSkull[0];
        if (shortLifetimeSprite.getShortLifeTimeSpriteType().equals(ShortLifeTimeSpriteType.PORTAL_SHINE))
            return shortLifetimeSprite.isLifeTimeInOddPartAlternatingPercentRange(10) ? portalShine[0] : portalShine[1];
        // so deadType is BLACK_GIFT
        if (shortLifetimeSprite.getShortLifeTimeSpriteType().equals(ShortLifeTimeSpriteType.WOOD_FIRE)) {
            return woodFire[random.nextInt(woodFire.length)];
        }
        if (shortLifetimeSprite.getShortLifeTimeSpriteType().equals(ShortLifeTimeSpriteType.FLAMING_TORCH)) {
            return flamingTorch[random.nextInt(flamingTorch.length)];
        }

        return blackGift;
    }

    public void addShortLifeTimeSprite(ShortLifeTimeSprite shortLifeTimeSpriteToAdd) {
        allShortLifeTimeSprites.add(shortLifeTimeSpriteToAdd);
    }

    public ArrayList<ShortLifeTimeSprite> getAllShortLifeTimeSprites() {
        return allShortLifeTimeSprites;
    }


    public void handleShortLifeTimeSpritesCountDown() {
        getAllShortLifeTimeSprites().removeIf(ShortLifeTimeSprite::isBeyondLifeTime);
    }

    public long getNumberOfGifts() {
        return getAllShortLifeTimeSprites().stream()
            .filter(shortLifeTimeSprite -> shortLifeTimeSprite.getShortLifeTimeSpriteType().equals(ShortLifeTimeSpriteType.BLACK_GIFT))
            .count();
    }


    void loadSpawnAbleFrames() {
        loadZombieDeadFrames();
        loadSkullDeadFrames();
        loadBlackGiftFrames();
        loadPortalShineFrames();
        loadWowFrames();
        loadWoodFireFrames();
        loadFlamingTorchFrames();
    }

    private void loadWowFrames() {
        wow = new Sprite();
        Texture wowFile = new Texture(Gdx.files.internal("images/wow100x100.png"));
        wow = new Sprite(wowFile, 0, 0, WOW_YELL.WIDTH, WOW_YELL.HEIGHT);
    }

    private void loadBlackGiftFrames() {
        blackGift = new Sprite();
        Texture blackGiftFile = new Texture(Gdx.files.internal("images/blackGiftBox100x100.png"));
        blackGift = new Sprite(blackGiftFile, 0, 0, BLACK_GIFT.WIDTH, BLACK_GIFT.HEIGHT);
    }

    private void loadPortalShineFrames() {
        portalShine = new Sprite[2];
        Texture portalShineFile1 = new Texture(Gdx.files.internal("images/portalshine_1_100x100.png"));
        Texture portalShineFile2 = new Texture(Gdx.files.internal("images/portalshine_2_100x100.png"));
        portalShine[0] = new Sprite(portalShineFile1, 0, 0, PORTAL_SHINE.WIDTH, PORTAL_SHINE.HEIGHT);
        portalShine[1] = new Sprite(portalShineFile2, 0, 0, PRINCESS.WIDTH, PORTAL_SHINE.HEIGHT);
    }

    private void loadSkullDeadFrames() {
        deadFrameSkull = new Sprite[2];
        Texture deadSkullFile1 = new Texture(Gdx.files.internal("images/skull100x100-ex1.png"));
        Texture deadSkullFile2 = new Texture(Gdx.files.internal("images/skull100x100-ex2.png"));
        deadFrameSkull[0] = new Sprite(deadSkullFile1, 0, 0, DEAD_SKULL.WIDTH, DEAD_SKULL.HEIGHT);
        deadFrameSkull[1] = new Sprite(deadSkullFile2, 0, 0, DEAD_SKULL.WIDTH, DEAD_SKULL.HEIGHT);
    }

    private void loadZombieDeadFrames() {
        deadFrameZombie = new Sprite();
        Texture deadZombieFile = new Texture(Gdx.files.internal("images/grave64x96.png"));
        deadFrameZombie = new Sprite(deadZombieFile, 0, 0, DEAD_ZOMBIE.WIDTH, DEAD_ZOMBIE.HEIGHT);
    }

    private void loadWoodFireFrames() {
        woodFire = new Sprite[12];
        Texture woodFireFile = new Texture(Gdx.files.internal("images/woodFire_12_100x100.png"));
        for (int lus = 0; lus < 12; lus++) {
            woodFire[lus] = new Sprite(woodFireFile, lus * 100, 0, WOOD_FIRE.WIDTH, WOOD_FIRE.HEIGHT);
        }
    }


    private void loadFlamingTorchFrames() {
        flamingTorch = new Sprite[11];
        Texture flamingTorchFile = new Texture(Gdx.files.internal("images/flamingTorch_11_352x64.png"));
        for (int lus = 0; lus < 11; lus++) {
            flamingTorch[lus] = new Sprite(flamingTorchFile, lus * FLAMING_TORCH.WIDTH, 0, FLAMING_TORCH.WIDTH, FLAMING_TORCH.HEIGHT);
        }
    }

    public boolean isOnPositionATypeOf(IntPosition pixelPosToTest, ShortLifeTimeSpriteType typeToTest) {
        for (ShortLifeTimeSprite shortLifeTimeSprite : getAllShortLifeTimeSprites()) {
            if (shortLifeTimeSprite.getShortLifeTimeSpriteType().equals(typeToTest) &&
                shortLifeTimeSprite.getPosition().equals(pixelPosToTest)) return true;
        }
        return false;
    }
}
