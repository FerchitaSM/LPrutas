package com.example.lp.bot;

import com.example.lp.bl.MovilidadBl;
import com.example.lp.bl.TipoMovilidadBl;
import com.example.lp.domain.MovilidadEntity;
import com.example.lp.domain.TipoMovilidadEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class BotMain  extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(BotMain.class);
    SendMessage message = new SendMessage();

    MovilidadBl movilidadBl;
    TipoMovilidadBl tipoMovilidadBl;

    public BotMain ( MovilidadBl movilidadBl){
        this.movilidadBl = movilidadBl;
    }

    public BotMain(TipoMovilidadBl tipoMovilidadBl){
        this.tipoMovilidadBl=tipoMovilidadBl;
    }

    public BotMain(MovilidadBl movilidadBl, TipoMovilidadBl tipoMovilidadBl) {
        this.movilidadBl = movilidadBl;
        this.tipoMovilidadBl = tipoMovilidadBl;
    }

    @Override
    public void onUpdateReceived(Update update) {
        karen_prueba(update);
        /*
        String chatId =idUser(update);
        InlineKeyboardMarkup respuesta_botones = (InlineKeyboardMarkup) responderBotones(update);
        String respuesta_texto= responderTexto(update);

        message
                .setChatId(chatId)
                .setText(respuesta_texto)
                .setReplyMarkup(respuesta_botones);

        try {
            log.info("mensaje enviado");
            this.execute(message);
        } catch (TelegramApiException e) {
            log.info("error");
            e.printStackTrace();
        }
       */
    }

    private void karen_prueba(Update update) {
        log.info("update");
        if(update.hasMessage() && update.getMessage().hasText()){
            TipoMovilidadEntity tipoMovilidadEntity=tipoMovilidadBl.findTipoMovilidadById(1);
            SendMessage message=new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText("TipoMovilidad desde BDD: "+tipoMovilidadEntity);
            try{
                this.execute(message);
            }catch (TelegramApiException e){
                e.printStackTrace();
            }

        }
    }


    public String responderTexto(Update update) {
        String ret = "Elije una opcion";
        MovilidadEntity movilidadEntity = movilidadBl.findMovilidadById(0);
        ret= movilidadEntity.toString();
        return ret;
    }

    private ReplyKeyboard responderBotones(Update update) {
        //para mandar a la clse opciones
        String call_data="s/d";
        if(update.hasCallbackQuery()) {
            call_data=update.getCallbackQuery().getData();
        }
        BotOpciones op= new BotOpciones(call_data);
        List<String> opciones = op.lista_opciones();
        //lista con opciones

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        for (int i=0; i<opciones.size();i++)
        {
            rowInline.add(new InlineKeyboardButton().setText(opciones.get(i)).setCallbackData(opciones.get(i)));
        }
        rowsInline.add(rowInline);
        markupInline.setKeyboard(rowsInline);
        return markupInline;
    }


    public String idUser(Update update) {
        String chatId;
        if(update.hasCallbackQuery())
        {chatId = update.getCallbackQuery().getFrom().getId().toString();}
        else
        { chatId = update.getMessage().getFrom().getId().toString();}
        return chatId;
    }


    @Override
    public String getBotUsername() {
        return "Rutas_La_Paz_Bot"; // chat Grupo
        // creence su chat bot para que podamos correr en conjunto si
    }

    @Override
    public String getBotToken() {
        return "878308952:AAELkgmF0NkxPV7t7KvpQ3-JOWWVChLeMbg";  // chat Grupo
        // creence su chat bot para que podamos correr en conjunto si

    }
}


