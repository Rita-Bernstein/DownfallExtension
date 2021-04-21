package charbosses.powers.cardpowers;

import DownfallExtension.DownfallExtension;
import com.megacrit.cardcrawl.core.AbstractCreature;
import downfall.downfallMod;

public class EnemyCompressionMoldPower extends AbstractCrowbotPower {
    public static final String POWER_ID = DownfallExtension.makeID("CompressionMold");


    public EnemyCompressionMoldPower(AbstractCreature owner) {
        super(POWER_ID);

        this.owner = owner;
        updateDescription();
        loadRegion("panache");
    }


    public void updateDescription() {
        this.description = this.DESCRIPTIONS[0];
    }
}


