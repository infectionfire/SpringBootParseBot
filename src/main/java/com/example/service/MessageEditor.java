package com.example.service;

import com.example.model.Data;
import com.example.parser.methods.StructureCardBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


public class MessageEditor {
    static Logger log = LogManager.getLogger();

    public static SendMessage answerCreator(Message message, Long chatId){

        SendMessage response = new SendMessage();
        response.setChatId(String.valueOf(chatId));
        String text = "Введите валидную ссылку";
        try{
        text = StructureCardBuilder.BuildDescription(message.getText());
        }catch (Exception e){
            log.error(e);
        }
        DataServiceImpl dataService = new DataServiceImpl();
        Data data = new Data(text, message.getText());
        response.setText(text);
        try {
            dataService.saveData(data);
        } catch (Exception e) {
            log.error("Not unique value");
        }
            return response;
        }
    }
