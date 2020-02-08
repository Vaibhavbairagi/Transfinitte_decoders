package com.example.transfinitte_decoders.chatbot;

public class ChatMessage {
    String mSendMessage;
    String mReceiveMessage;
    public ChatMessage(String sendMessage,String receiveMessage){
        sendMessage = mSendMessage;
        receiveMessage = mReceiveMessage;
    }

    public String getmSendMessage() {
        return mSendMessage;
    }

    public String getmReceiveMessage() {
        return mReceiveMessage;
    }
}
