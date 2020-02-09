package com.example.transfinitte_decoders.chatbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.transfinitte_decoders.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    private List<ChatMessage> mChatMessage;

    // Pass in the contact array into the constructor
    public ChatListAdapter(List<ChatMessage> contacts) {
        mChatMessage = contacts;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.chat_message_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessage chatMessage = mChatMessage.get(position);
        TextView send = holder.sendMessage;
        TextView receive = holder.receiveMessage;
        send.setText(chatMessage.getmSendMessage());
        receive.setText(chatMessage.getmReceiveMessage());
    }

    @Override
    public int getItemCount() {
        return mChatMessage.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView sendMessage;
        public TextView receiveMessage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sendMessage = (TextView)itemView.findViewById(R.id.send_message);
            receiveMessage = (TextView)itemView.findViewById(R.id.receive_message);
        }
    }
}
