package com.example.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;

import static com.example.service.MessageEditor.answerCreator;


/**
 * 
 * This example bot is an echo bot that just repeats the messages sent to him
 *
 */
@Component
class ExampleBot extends TelegramLongPollingBot {
	private static final Logger logger = LoggerFactory.getLogger(ExampleBot.class);
	
	private final String token;
	private final String username;

	ExampleBot(@Value("${bot.token}") String token, @Value("${bot.username}") String username) {
		this.token = token;
		this.username = username;
	}
	
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
		if (update.hasMessage()) {
			Message message = update.getMessage();
			Long chatId = message.getChatId();
			SendMessage response = answerCreator(message,chatId);
			try {
				execute(response);
				logger.info("Sent message \"{}\" ", chatId);
			} catch (TelegramApiException e) {
				logger.error("Failed to send message \"{}\" due to error: {}", chatId, e.getMessage());
			}
		}
	}

	@PostConstruct
	public void start() {
		logger.info("username: {}, token: {}", username, token);
	}
}
