package ru.zhugaru.wordfckr.bot;

import java.util.*;
import static java.util.Map.entry;


public class Util {
    protected static final String CYRILLIC = "^[ёа-я\\s]+$";
    protected static final List<String> reactions = new ArrayList<>(List.of(
            "Russian only, dolbaeb", "Ty eblan?", "Ebaaaat'", "Izvinis'"));
    protected static final String PREFIX = " ху";
    protected static final List<String> shortTextReactions = new ArrayList<>(List.of(
            "хуй))", "попробуй что-нибудь другое, плез", "извинись", "ну ок", "дядь, ну ты чо?"
    ));
    protected static final String SYMBOLS = "[/^[`!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?~\\d]*$g]";
    protected static final String VOWELS = "аеёиоуыэюя";

    protected static Map<Character, Character> getRule() {
        return Map.of(
                'а', 'я',
                'о', 'е',
                'у', 'ю',
                'ы', 'и',
                'э', 'е');
    }

    protected static String getExceptionWord(String word) {
        Map<String, String> exceptions = Map.ofEntries(
                entry("валера", "хуера"),
                entry("александра", "хуяндра"),
                entry("санек", "хуек"),
                entry("санёк", "хуёк"),
                entry("шашлык", "машлык"),
                entry("артур", "хуйртур"));

        String lowerCaseWord = word.toLowerCase();
        if (exceptions.containsKey(lowerCaseWord)) {
            return word + " " + exceptions.get(lowerCaseWord) + ")))";
        }
        return null;
    }
}