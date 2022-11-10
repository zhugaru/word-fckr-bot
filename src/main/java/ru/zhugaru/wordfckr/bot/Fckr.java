package ru.zhugaru.wordfckr.bot;

import java.util.Random;

import static ru.zhugaru.wordfckr.bot.Util.*;

public class Fckr {
    public static String fckWordUp(String word) {
        String origWord = word;
        word = word.toLowerCase();

        if (word.equals("/start")) {
            return "Напиши мне любое слово, а я его как следует хуйну";
        }

        for (char letter : word.toCharArray()) {
            if (!word.matches(CYRILLIC) || word.matches(SYMBOLS)) {
                return reactions.get(new Random().nextInt(4));
            }
            if (getExceptionWord(origWord) != null) {
                return getExceptionWord(origWord);
            }
            if (VOWELS.indexOf(letter) != -1) {
                if (getRule().containsKey(letter)) {
                    word = getRule().get(letter) + word.substring(1);
                }
                break;
            } else {
                word = word.substring(1);
            }
        }
        return word.isEmpty()
                ? shortTextReactions.get(new Random().nextInt(6))
                : origWord + PREFIX + word + ")))";
    }
}
