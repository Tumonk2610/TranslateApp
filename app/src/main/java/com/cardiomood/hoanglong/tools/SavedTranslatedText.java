package com.cardiomood.hoanglong.tools;

import com.cardiomood.hoanglong.db.entity.TranslationHistoryEntity;

import java.util.Arrays;

import translate.provider.TranslatedText;

public class SavedTranslatedText extends TranslatedText {

    private TranslationHistoryEntity historyItem;

    public SavedTranslatedText(TranslationHistoryEntity historyItem) {
        super(historyItem.getSourceLang(), historyItem.getTargetLang(),
                Arrays.asList(historyItem.getTranslation().split("\\n")));
        this.historyItem = historyItem;
    }

    public TranslationHistoryEntity getHistoryItem() {
        return historyItem;
    }

    public void setHistoryItem(TranslationHistoryEntity historyItem) {
        this.historyItem = historyItem;
    }
}
