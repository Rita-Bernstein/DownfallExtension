package DownfallExtension;

import basemod.BaseMod;

import basemod.ModPanel;
import basemod.interfaces.*;
import charbosses.actions.util.CharBossMonsterGroup;
import charbosses.bosses.Crowbot.CharBossCrowbot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.nio.charset.StandardCharsets;
import java.util.*;


@SpireInitializer
public class DownfallExtension implements
        PostInitializeSubscriber,
        EditKeywordsSubscriber,
        EditStringsSubscriber {

    public static String MOD_ID = "DownfallExtension";

    public static String makeID(String id) {
        return MOD_ID + ":" + id;
    }

    public static String assetPath(String path) {
        return MOD_ID + "/" + path;
    }

    public static String CharacterAssetPath(String ClassName, String path) {
        return MOD_ID + "/" + ClassName + "/" + path;
    }

    public static final String MODNAME = "DownfallExtension";
    public static final String AUTHOR = "Rita";
    public static final String DESCRIPTION = "";

    public static ArrayList<AbstractGameEffect> effectsQueue = new ArrayList();

    public DownfallExtension() {
        BaseMod.subscribe(this);
    }

    @Override
    public void receivePostInitialize() {
        Texture badgeTexture = new Texture(assetPath("/images/badge.png"));
        ModPanel settingsPanel = new ModPanel();
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        this.initializeMonsters();
    }



    public static void initialize() {
        new DownfallExtension();
    }


    private void initializeMonsters() {
        BaseMod.addMonster(CharBossCrowbot.ID, () -> new CharBossMonsterGroup(new AbstractMonster[]{new CharBossCrowbot()}));
    }

    private Settings.GameLanguage languageSupport() {
        switch (Settings.language) {
            case ZHS:
                return Settings.language;
            default:
                return Settings.GameLanguage.ENG;
        }
    }

    public void receiveEditStrings() {
        Settings.GameLanguage language = languageSupport();
        loadLocStrings(Settings.GameLanguage.ENG);
        if (!language.equals(Settings.GameLanguage.ENG)) {
            loadLocStrings(language);
        }

    }

    private void loadLocStrings(Settings.GameLanguage language) {
        String path = "localization/" + language.toString().toLowerCase() + "/";

        BaseMod.loadCustomStringsFile(EventStrings.class, assetPath(path + "EventStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class, assetPath(path + "UIStrings.json"));
//        BaseMod.loadCustomStringsFile(PotionStrings.class, assetPath(path + "PotionStrings.json"));
        BaseMod.loadCustomStringsFile(CardStrings.class, assetPath(path + "CardStrings.json"));
        BaseMod.loadCustomStringsFile(MonsterStrings.class, assetPath(path + "MonsterStrings.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class, assetPath(path + "PowerStrings.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class, assetPath(path + "RelicStrings.json"));
//        BaseMod.loadCustomStringsFile(CharacterStrings.class, assetPath(path + "CharacterStrings.json"));
//        BaseMod.loadCustomStringsFile(OrbStrings.class, assetPath(path + "OrbStrings.json"));
//        BaseMod.loadCustomStringsFile(TutorialStrings.class,assetPath(path + "TutorialStrings.json"));
    }


    private void loadLocKeywords(Settings.GameLanguage language) {
        String path = "localization/" + language.toString().toLowerCase() + "/";
        Gson gson = new Gson();
        String json = Gdx.files.internal(assetPath(path + "KeywordStrings.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("downfallextension", keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveEditKeywords() {

        Settings.GameLanguage language = languageSupport();

        // Load english first to avoid crashing if translation doesn't exist for something
        loadLocKeywords(Settings.GameLanguage.ENG);
        if (!language.equals(Settings.GameLanguage.ENG)) {
            loadLocKeywords(language);
        }
    }
}
