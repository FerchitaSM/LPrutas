package bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

public class BotMain  extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(BotMain.class);
    SendMessage message = new SendMessage();
    String respuesta="";
    @Override
    public void onUpdateReceived(Update update) {
        String chatId=update.getMessage().getChatId().toString();
        respuesta=responder(update);
        message
                .setChatId(chatId)
                .setText(respuesta);


        try {
            log.info("mensaje enviado");
            this.execute(message);
        } catch (TelegramApiException e) {
            log.info("error");
            e.printStackTrace();
        }
    }

    private String responder(Update update) {
        String dato=respuesta=update.getMessage().getFrom().getFirstName();
        // verificar si el usuario exite
        //enviar dato segun corresponda
        return dato;

    }

    @Override
    public String getBotUsername() {
        return "pruebaRLP_bot";
    }

    @Override
    public String getBotToken() {
        return "1048217369:AAFJ7frG5Aikq2ttTMHVi-rvCSHQEDtF1ws";  // chatbot Fernanda
        //return "878308952:AAELkgmF0NkxPV7t7KvpQ3-JOWWVChLeMbg";  // chat Grupo
        // creence su chat bot para que podamos correr en conjunto si

    }
}
