package com.example.lp.bl;

import com.example.lp.dao.UserChatRepository;
import com.example.lp.dao.UserTypeRepository;
import com.example.lp.dao.UsersRepository;
import com.example.lp.domain.UserChatEntity;
import com.example.lp.domain.UserTypeEntity;
import com.example.lp.domain.UsersEntity;
import com.example.lp.dto.Status;
import com.example.lp.dto.UserChatDto;
import com.example.lp.dto.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import javax.transaction.Transactional;
import java.security.SecureRandom;



import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

@Service
public class UsersBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBl.class);
    private UsersRepository usersRepository;
    private UserChatRepository userChatRepository;
    private UserTypeRepository userTypeRepository;

    @Autowired
    public UsersBl(UsersRepository usersRepository, UserChatRepository userChatRepository, UserTypeRepository userTypeRepository) {
        this.usersRepository = usersRepository;
        this.userChatRepository = userChatRepository;
        this.userTypeRepository = userTypeRepository;
    }


    //Funciones de User

    //funcion para ver si existe el usuario con el id del bot
    public boolean existingUser(int chat_id_bot) {
        LOGGER.info("existingUser.........................");
        boolean ret = true;
        UsersEntity usersEntity = this.usersRepository.findByIdUserBot(chat_id_bot);
        if (usersEntity == null) {
            ret =false;
        }
        return ret;
    }

    //funcion para buscar usuario por el id
    public UsersEntity findByIdUser(int id) {
        LOGGER.info("findByIdUser.........................");
        UsersEntity usersEntity = this.usersRepository.findByIdUser(id);
        if (usersEntity != null) {
            return usersEntity;
        } else {
            LOGGER.info("usersEntity null");
            throw new RuntimeException("Record cannot found for UsersEntity with ID: " + id);
        }
    }

    //funcion para buscar usuario por el id del bot
    public UsersEntity findByIdUserBot(int id) {
        LOGGER.info("findByIdUserBot.........................");
        UsersEntity usersEntity = this.usersRepository.findByIdUserBot(id);
        if (usersEntity != null) {
            return usersEntity;
        } else {
            LOGGER.info("usersEntity null");
            throw new RuntimeException("Record cannot found for UsersEntity with ID: " + id);
        }

    }

    //funcion para registrar a un nuevo usuario
    public UsersEntity registerUser(User users) {
        LOGGER.info("registrerUser.........................");
        Date sDate = getDate();
        UsersEntity usersEntity = new UsersEntity();
        System.out.println("gesteeee"+users.getId());
        usersEntity.setIdUserBot(users.getId());
        usersEntity.setIdUserType(UserType.USERS.getUserType());
        usersEntity.setuStatus(Status.ACTIVE.getStatus());
        usersEntity.setTxHost("localhost");
        usersEntity.setTxUser("fer");
        usersEntity.setTxDate(sDate);
        usersEntity.setUserName(users.getFirstName());
        usersRepository.save(usersEntity);
        return usersEntity;
    }


    //Funciones de UserChat

    //funcion para registrar un nuevo chat del usuario previamente crado
    public UserChatDto continueWhitUser(Update update ) {
        LOGGER.info("continueWithUser.........................");
        int chat_id = Integer.parseInt(update.getMessage().getChatId().toString());
        UsersEntity usersEntity = findByIdUserBot(chat_id);
        UserChatEntity lastmessage = userChatRepository.findLastChatByUserId(usersEntity.getIdUser());
        String response = "Inicio";

        if (lastmessage != null)
            response = String.valueOf(lastmessage.getInMessage());

        Date sDate = getDate();
        UserChatEntity userChatEntity = new UserChatEntity();
        userChatEntity.setIdUser(usersEntity.getIdUser());
        userChatEntity.setInMessage(update.getMessage().getText());
        userChatEntity.setOutMessage(response); //TODO FALATA PONER EL DATO DE RESPUESTA
        userChatEntity.setMsgDate(sDate);
        userChatEntity.setTxUser(update.getMessage().getFrom().getId().toString());
        userChatEntity.setTxHost(update.getMessage().getChatId().toString());
        userChatEntity.setTxDate(sDate);
        userChatEntity.setPointConversation(0);
        // Guardamos en base de datos
        userChatRepository.save(userChatEntity);
        UserChatDto userChatDto = new UserChatDto(userChatEntity);
        return userChatDto;
    }

    //funcion para regresar el punto de la ultima conversacion
    public String lastPointConversation(Update update ) {
        LOGGER.info("lastPointConversation.........................");
        String ret="0";
        int chat_id = Integer.parseInt(update.getMessage().getChatId().toString());


        UsersEntity usersEntity = findByIdUserBot(chat_id);
        ret = String.valueOf(userChatRepository.finUltimatePointConversatonChatByUserId(usersEntity.getIdUser()));
       return ret;
    }

    //Funciones de UserType

    //funcion para devolver el tipo "Administrador"
    public UserTypeEntity getTypeAdministrador() {
        LOGGER.info("UserTypeEntity.........................");
        UserTypeEntity userTypeEntity = this.userTypeRepository.findByType("Administrador");

        if (userTypeEntity != null) {
            return userTypeEntity;
        } else {
            LOGGER.info("userTypeEntity null");
            throw new RuntimeException("Record cannot found for userTypeEntity with type: Administrador ");
        }
    }

    //funcion para devolver el token del tipo "Administrador"
    public String getTokenAdministrador(){
        LOGGER.info("getTokenAdministrador.........................");
        String ret= null;
        UserTypeEntity userTypeEntity = getTypeAdministrador();
        ret= userTypeEntity.getToken();
        if (ret != null) {
            return ret;
        } else {
            LOGGER.info("null administrator token ");
            throw new RuntimeException("Token ");
        }
    }

    //funcion para generar un nuevo token
    public String tokenGenerator (){
        String token = "";
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        try {
            token = new String(bytes, "UTF-8"); // for UTF-8 encoding
        } catch (UnsupportedEncodingException e) {
            token = random.toString();
            e.printStackTrace();
        }
        return token;
    }


    //funcion para cambiar del usuario el tipo de usuario por Administrador en la BDD
    @Transactional
    public String changeUserTypeToAdministrator(UsersEntity usersEntity, String tokenUser) {
        LOGGER.info("changeTypeUser.........................");
        String ret="";
        UserTypeEntity userTypeEntity = getTypeAdministrador();
        if(tokenUser.equals(userTypeEntity.getToken())) {
            LOGGER.info("token accepted.........................");
            usersEntity.setIdUserType(UserType.ADMINISTRADOR.getUserType());
            usersRepository.save(usersEntity);
            changeToken(userTypeEntity);
            ret="Usted ya es un administrador";
            //Date.from(Instant.now());
        }else {
            LOGGER.info("token denied.........................");
            ret="El token ingresado es incorrecto intente nuevamente";
        }
        return ret;
    }

    //funcion para cambiar el token del tipo de usuario Administrador de la BDD
    @Transactional
    public void changeToken(UserTypeEntity userTypeEntity) {
        String token = tokenGenerator();
        userTypeEntity.setToken(token);
        userTypeRepository.delete(userTypeEntity);
        userTypeRepository.save(userTypeEntity);

    }

    //extras
    private Date getDate() {
        java.util.Date uDate = new java.util.Date();
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }


    public void saveMessageAndUser(Update update ) {
        int chat_id = Integer.parseInt(update.getMessage().getChatId().toString());
        if(!existingUser(chat_id)){
            registerUser(update.getMessage().getFrom());
        } else {
            findByIdUserBot(update.getMessage().getFrom().getId());
        }
        continueWhitUser(update);
    }



    public String idMessage (long chat_id) {
        UsersEntity usersEntity = usersRepository.findByIdUserBot((int) chat_id );
        UserChatEntity userChatEntity = userChatRepository.findLastChatByUserId(usersEntity.getIdUser());
        String mensaje = String.valueOf(userChatEntity.getIdUserChat());
        return mensaje;
    }


    @Transactional
    public void changeResponseChatMessage(long chat_id, String response) {
        UsersEntity usersEntity = usersRepository.findByIdUserBot((int) chat_id );
        UserChatEntity userChatEntity = userChatRepository.findLastChatByUserId(usersEntity.getIdUser());
        userChatEntity.setOutMessage(response);
        //userChatRepository.delete(userChatEntity);
        userChatRepository.save(userChatEntity);
    }

    @Transactional
    public void changePointConversationChatMessage(long chat_id, String point_conversation) {
        UsersEntity usersEntity = usersRepository.findByIdUserBot((int) chat_id );
        UserChatEntity userChatEntity = userChatRepository.findLastChatByUserId(usersEntity.getIdUser());
        userChatEntity.setPointConversation(Integer.parseInt(point_conversation));
        //userChatRepository.delete(userChatEntity);
        userChatRepository.save(userChatEntity);
    }

}