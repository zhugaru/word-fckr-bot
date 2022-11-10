package ru.zhugaru.wordfckr.logger;

import ru.zhugaru.wordfckr.entity.LoggerEntity;

public final class LoggerHelper {
    private LoggerHelper() {
    }
    public static LoggerEntity saveLog(String messageText, String firstName,
                                       String lastName, String username, long chatId) {
        LoggerEntity logger = new LoggerEntity();
        logger.setMessageText(messageText);
        logger.setFirstName(firstName);
        logger.setLastName(lastName);
        logger.setUsername(username);
        logger.setChatId(chatId);
        return logger;
    }
}
