package charbosses.cards;

import charbosses.bosses.AbstractCharBoss;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.monsters.AbstractMonster;



public abstract class AbstractExtendCharbossCard extends AbstractBossCard {
    public AbstractExtendCharbossCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target, AbstractMonster.Intent intent,boolean isCustomCard) {

        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.owner = AbstractCharBoss.boss;
        this.limit = 99;
        this.intent = intent;

        if (isCustomCard) {
            this.portrait = new TextureAtlas.AtlasRegion(new Texture("DownfallExtension/images/cards/" + img + ".png"), 0, 0, 250, 190);
            this.portraitImg = new Texture("DownfallExtension/images/cards/" + img + "_p.png");
        }
    }
}