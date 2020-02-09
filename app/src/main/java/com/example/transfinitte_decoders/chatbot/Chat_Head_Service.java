package com.example.transfinitte_decoders.chatbot;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.transfinitte_decoders.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Chat_Head_Service extends Service {
    ArrayList<ChatMessage> chat;
    /*Copy just the below code to the button, you want to map this service to
    startService(new Intent(getApplicationContext(), Chat_Head_Service.class));*/

    /*Add the below to the main activity to allow for chatbot to work
    To be placed at the top
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION;

    static {
        CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    }
    The below code goes in onCreate
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        }
     The below code to be placed after onCreate
      @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {

            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK) {
            } else { //Permission is not available
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
     */
    private WindowManager mWindowManager;
    private View mChatHeadView;
    private View mMessageView;
    public Chat_Head_Service() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Inflate the chat head layout we created
        chat = new ArrayList<>();
        mChatHeadView = LayoutInflater.from(Chat_Head_Service.this).inflate(R.layout.service_chat_head, null);
        mMessageView = LayoutInflater.from(Chat_Head_Service.this).inflate(R.layout.service_message,null);
        final EditText editText = (EditText)mMessageView.findViewById(R.id.edit_text);
        RecyclerView recyclerView = (RecyclerView)mMessageView.findViewById(R.id.chat_recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        final ChatListAdapter chatListAdapter = new ChatListAdapter(chat);
        recyclerView.setAdapter(chatListAdapter);
        final ImageButton send = (ImageButton)mMessageView.findViewById(R.id.send_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().toLowerCase().contains("first aid")||editText.getText().toString().toLowerCase().contains("help")){
                    chat.add(new ChatMessage(editText.getText().toString(),"About what"));
                    chatListAdapter.notifyItemInserted(chat.size()-1);
                }else if(editText.getText().toString().toLowerCase().contains("hi")||editText.getText().toString().toLowerCase().contains("hello")||editText.getText().toString().toLowerCase().contains("hey")){
                    chat.add(new ChatMessage(editText.getText().toString(),"Vannakum"));
                    chatListAdapter.notifyItemInserted(chat.size()-1);
                }else if(editText.getText().toString().toLowerCase().contains("fracture")){
                    chat.add(new ChatMessage(editText.getText().toString(),
                            "Stop any bleeding. Apply pressure to the wound with a sterile bandage, a clean cloth or a clean piece of clothing.\n" + "Press SOS if extreme"));
                    chatListAdapter.notifyItemInserted(chat.size()-1);
                }else if(editText.getText().toString().toLowerCase().contains("wound")||editText.getText().toString().toLowerCase().contains("cut")){
                    chat.add(new ChatMessage(editText.getText().toString(),"Keeping the area clean and applying a thin layer of antibiotic ointment can help prevent infection"));
                    chatListAdapter.notifyItemInserted(chat.size()-1);

                }
                else if(editText.getText().toString().toLowerCase().contains("burn")||editText.getText().toString().toLowerCase().contains("blisters")){
                    chat.add(new ChatMessage(editText.getText().toString(),"immediately get the person away from the heat source to stop the burning\n" +
                            "cool the burn with cool or lukewarm running water for 20 minutes \n" +
                            "remove any clothing or jewellery that's near the burnt area "));
                    chatListAdapter.notifyItemInserted(chat.size()-1);

                }
                else if(editText.getText().toString().toLowerCase().contains("drown")||editText.getText().toString().toLowerCase().contains("sink")){
                    chat.add(new ChatMessage(editText.getText().toString(), "Get Help. Notify a lifeguard, if one is close.\n" +
                                                        "Check for Breathing. Place your ear next to the person's mouth and nose.\n" +
                            "If the Person is Not Breathing, Check Pulse.\n" +
                            "If There is No Pulse, Start CPR.\n"));
                }
                else if(editText.getText().toString().contains(""))
                editText.setText("");
            }
        });
        //Add the view to the window.
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        final WindowManager.LayoutParams params_message = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);
        //Specify the chat head position
        params.gravity = Gravity.TOP | Gravity.LEFT;        //Initially view will be added to top-left corner
        params.x = 0;
        params.y = 100;

        //Add the view to the window
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(mChatHeadView, params);

        //Set the close button.
        ImageView closeButton = (ImageView) mChatHeadView.findViewById(R.id.close_btn);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //close the service and remove the chat head from the window
                stopSelf();
            }
        });
        //Drag and move chat head using user's touch action.
        final ImageView chatHeadImage = (ImageView) mChatHeadView.findViewById(R.id.chat_head_profile_iv);
        RelativeLayout relativeLayout = mMessageView.findViewById(R.id.service_message);
        chatHeadImage.setOnTouchListener(new View.OnTouchListener() {
            private int lastAction;
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        //remember the initial position.
                        initialX = params.x;
                        initialY = params.y;

                        //get the touch location
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();

                        lastAction = event.getAction();
                        return true;
                    case MotionEvent.ACTION_UP:
                        int Xdiff = (int) (event.getRawX() - initialTouchX);
                        int Ydiff = (int) (event.getRawY() - initialTouchY);
                        params.x = 0;
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        mWindowManager.updateViewLayout(mChatHeadView, params);
                        //The check for Xdiff <10 && YDiff< 10 because sometime elements moves a little while clicking.
                        //So that is click event.
                        if (Xdiff < 5 && Ydiff < 5) {
                            if(!mMessageView.isAttachedToWindow()){
                                params_message.gravity = Gravity.BOTTOM;
                                mWindowManager.addView(mMessageView,params_message);

                            }else{
                                mWindowManager.removeView(mMessageView);
                            }
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        //Calculate the X and Y coordinates of the view.
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);

                        //Update the layout with new X & Y coordinate
                        mWindowManager.updateViewLayout(mChatHeadView, params);
                        if(mMessageView.isAttachedToWindow())
                            mWindowManager.updateViewLayout(mMessageView, params_message);
                        lastAction = event.getAction();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mChatHeadView != null) {
            mWindowManager.removeView(mChatHeadView);
        }
        if(mMessageView.isAttachedToWindow()){
            mWindowManager.removeView(mMessageView);
        }
    }
}

