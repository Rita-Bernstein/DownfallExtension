package charbosses.cards.crowbot;

import charbosses.bosses.Crowbot.CharBossCrowbot;
import charbosses.cards.AbstractBossCard;
import charbosses.cards.AbstractExtendCharbossCard;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import downfall.downfallMod;

public class EnFullMetalJacket extends AbstractExtendCharbossCard {
    public static final String ID = "DownfallExtension:FullMetalJacket";
    private static final CardStrings cardStrings;
    private boolean applyPower = true;

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    }

    public EnFullMetalJacket() {
        super(ID, EnFullMetalJacket.cardStrings.NAME, "crowbot/fullMetalJacket", 2, EnFullMetalJacket.cardStrings.DESCRIPTION, CardType.ATTACK, CardColor.BLUE, CardRarity.BASIC, CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_DEBUFF, true);
        this.baseDamage = 6;
        this.magicNumber = this.baseMagicNumber = 2;
        this.tags.add(CharBossCrowbot.Enums.AMMO);
    }

    public EnFullMetalJacket(boolean applyPower){
        this();
        this.applyPower = applyPower;
        if(!applyPower)
            this.intent = AbstractMonster.Intent.ATTACK;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
        addToBot(new DamageAction(p, new DamageInfo(m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        if(this.applyPower)
        addToBot(new ApplyPowerAction(p, m, new VulnerablePower(p, this.magicNumber, false), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new EnFullMetalJacket();
    }
}
