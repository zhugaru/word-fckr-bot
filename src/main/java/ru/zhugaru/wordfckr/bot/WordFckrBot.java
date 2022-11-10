package ru.zhugaru.wordfckr.bot;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.zhugaru.wordfckr.entity.LoggerEntity;
import ru.zhugaru.wordfckr.repository.LoggerRepository;

import static ru.zhugaru.wordfckr.bot.Fckr.fckWordUp;
import static ru.zhugaru.wordfckr.logger.LoggerHelper.saveLog;

@Setter
@Component
public class WordFckrBot extends TelegramLongPollingBot {
    @Autowired
    private LoggerRepository loggerRepository;
    @Value("${bot.token}")
    private String token;
    @Value("${bot.username}")
    private String username;

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String messageText = update.getMessage().getText();
                String firstName = update.getMessage().getChat().getFirstName();
                String lastName = update.getMessage().getChat().getLastName();
                String username = update.getMessage().getChat().getUserName();
                long chatId = update.getMessage().getChatId();
                SendMessage outMessage = getMessage(chatId, messageText);
                LoggerEntity logger = saveLog(outMessage.getText(), firstName, lastName, username, chatId);
                loggerRepository.save(logger);

                execute(outMessage);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private SendMessage getMessage(long chatId, String messageText) {
        SendMessage outMessage = new SendMessage();
        outMessage.setChatId(String.valueOf(chatId));
        outMessage.setText(fckWordUp(messageText));
        return outMessage;
    }
}


