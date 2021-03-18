package ru.openclade.bot;


import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;



public class TelegramBot extends TelegramLongPollingBot {
	
	public static void main(String[] args) {
	ApiContextInitializer.init();
	TelegramBotsApi telebot = new TelegramBotsApi();
	try {
		telebot.registerBot(new TelegramBot());
	} catch(TelegramApiException e) {
		e.printStackTrace(); // comments
	}
	}

	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "OpenClade";
	}
	public void sendMessage(Message message, String text, boolean isReply) {
		SendMessage send = new SendMessage(); // daws
		send.enableMarkdown(true);
		send.setChatId(message.getChatId().toString());
		if(isReply) {
			send.setReplyToMessageId(message.getMessageId());
		}
		send.setText(text);
		try {
			sendMessage(send);
		} catch(TelegramApiException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onUpdateReceived(Update update) {
		 
		Message message = update.getMessage();
		if(message != null && message.hasText()) {
			switch(message.getText()) {
			case "/help":
				this.sendMessage(message, "Hello, Brother", true);
				break;
			case "/start":
				this.sendMessage(message, "Стартуем!", false);
				break;
			default:
				this.sendMessage(message, "Начни игру:  /start", false);
		}
			}
			
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "1305724214:AAFwTq3eBExJXBxCHV7ZaIpP8hRPkkKCHts";
	}
}
