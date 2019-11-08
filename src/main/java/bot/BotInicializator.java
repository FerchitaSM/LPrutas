package bot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//@Component
public class BotInicializator  {
    private static final Logger log = LoggerFactory.getLogger(BotInicializator.class);

    public BotInicializator(){
        levantando_bot();
    }

   // @PostConstruct
   public void levantando_bot() {
       ApiContextInitializer.init();
       TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
       try {
           telegramBotsApi.registerBot(new BotMain());
           log.info("Bot levantado");
       } catch (TelegramApiException e) {
           log.info("Bot NO levantado");
           e.printStackTrace();
       }
    }
}
