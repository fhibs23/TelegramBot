import org.jsoup.Jsoup;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {
    /**
     * метод main создает объект класса бот
     * @param args
     */
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    @Override
    /**
     * метод onUpdateReceived отвечает на отправленное сообщение
     */
    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();

            long chat_id = update.getMessage().getChatId();
            if (message_text.equals("/setting")) {
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText(message_text);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message_text.equals("/help")) {
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Чем могу Вам помочь?");
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message_text.equals("/start") || message_text.equals("назад")) {
                SendMessage message = new SendMessage() // Create a message object object
                        .setChatId(chat_id)
                        .setText("Выберите действие:");
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                row.add("Языки программирования");
                keyboard.add(row);
                row = new KeyboardRow();
                row.add("Примеры");
                row.add("Справочник");
                keyboard.add(row);
                row = new KeyboardRow();
                row.add("Интересные факты");
                keyboard.add(row);
                keyboardMarkup.setKeyboard(keyboard);
                message.setReplyMarkup(keyboardMarkup);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message_text.equals("Языки программирования")) {
                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Выберите интересующий ЯП:");
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                List<KeyboardRow> keyboard = new ArrayList<>();
                KeyboardRow row = new KeyboardRow();
                row.add("Java");
                row.add("Python");
                keyboard.add(row);
                row = new KeyboardRow();
                row.add("C/C++");
                row.add("Swift");
                keyboard.add(row);
                row = new KeyboardRow();
                row.add("другой");
                row.add("назад");
                keyboard.add(row);
                keyboardMarkup.setKeyboard(keyboard);
                message.setReplyMarkup(keyboardMarkup);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }  else if (message_text.equals("другой")) {
                SendMessage message = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Введите, интересующий язык");
                try {
                    execute(message);

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message_text.equals("Интересные факты")) {
                Parser parser = new Parser();
                SendMessage message = null;
                try {
                    message = new SendMessage() // Create a message object object
                            .setChatId(chat_id)
                            .setText(parser.findFacts());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    execute(message);

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }else if (message_text.equals("Примеры")) {
                SendMessage msg = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Выберите конструкцию:\n" +
                                "/while \n" +
                                "/for \n" +
                                "/if");

                try {
                    execute(msg); // Call method to send the photo
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (message_text.equals("Справочник")) {
                SendMessage msg = new SendMessage()
                        .setChatId(chat_id)
                        .setText("Вы зашли в справочник. Выберите интересующую Вас команду:\n" +
                                "/algorithm - про алгоритмы\n" +
                                "/oop - про ооп\n" +
                                "/comments - про комментарии");

                try {
                    execute(msg); // Call method to send the photo
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {

                Parser parser = new Parser();
                try {
                    if (parser.find(message_text) != null) {
                        SendMessage message = null;
                        try {
                            message = new SendMessage() // Create a message object object
                                    .setChatId(chat_id)
                                    .setText(parser.find(message_text));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            execute(message);

                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    // проверить на наличие в другом
                    else if (parser.find(message_text)==null){
                        SendMessage message = new SendMessage() // Create a message object object
                                .setChatId(chat_id)
                                .setText("Такого языкы программирования не обнаружено.");
                        try {
                            execute(message);

                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * метод getBotUsername
     * @return возвращает имя бота
     */
    public String getBotUsername() {
        return "HelpCoding_bot";
    }

    /**
     * метод getBotToken() возвращает токен бота
     * @return токен бота
     */
    public String getBotToken() {
        return "";
    }


}
