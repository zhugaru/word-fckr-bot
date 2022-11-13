package ru.zhugaru.wordfckr.bot;

import java.util.Random;

import static ru.zhugaru.wordfckr.bot.Util.*;

public class Fckr {
    public static String fckWordUp(String word) {
        String origWord = word;
        word = word.toLowerCase();

        if (word.equals(START)) {
            return START_MESSAGE;
        }

        for (char letter : word.toCharArray()) {
            if (!word.matches(CYRILLIC) || word.matches(SYMBOLS)) {
                return REACTIONS.get(new Random().nextInt(4));
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
                ? SHORT_TEXT_REACTIONS.get(new Random().nextInt(5))
                : origWord + PREFIX + word + ")))";
    }
}
