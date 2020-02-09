package com.example.transfinitte_decoders.chatbot;

public class ChatMessage {
    String mSendMessage;
    String mReceiveMessage;
    public ChatMessage(String sendMessage,String receiveMessage){
        this.mSendMessage = sendMessage;
        mReceiveMessage = receiveMessage;
    }

    public String getmSendMessage() {
        return mSendMessage;
    }

    public String getmReceiveMessage() {
        return mReceiveMessage;
    }
}
