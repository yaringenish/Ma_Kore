package com.example.makore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.makore.R;
import com.example.makore.entities.ChatListItem;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListItemViewHolder> {

    class ChatListItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvDisplayName;
        private final TextView tvLastMsg;
        private final ImageView ivPic;

        private ChatListItemViewHolder(View itemView) {
            super(itemView);
            this.tvDisplayName = itemView.findViewById(R.id.tvDisplayName);
            this.tvLastMsg = itemView.findViewById(R.id.tvLastMsg);
            this.ivPic = itemView.findViewById(R.id.ivPic);
        }
    }

    private final LayoutInflater mInflater;
    private List<ChatListItem> chatListItems;


    public ChatListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public ChatListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.chat_list_item_layout, parent, false);
        return new ChatListItemViewHolder(itemView);
    }

//    public void bind(String base64Image) {
//        byte[] imageBytes = Base64.decode(base64Image, Base64.DEFAULT);
//
//        // Convert the byte array to an integer array
//        int[] imageInts = new int[imageBytes.length];
//        for (int i = 0; i < imageBytes.length; i++) {
//            imageInts[i] = imageBytes[i] & 0xFF;
//        }
//
//        // Pass the integer array to your ViewHolder
//        // For example, if you have an ImageView in your ViewHolder:
//        imageView.setImageDrawable(new ColorDrawable(imageInts[0]));
    @Override
    public void onBindViewHolder(ChatListItemViewHolder holder, int position) {
        if(chatListItems != null) {
            final ChatListItem current = chatListItems.get(position);
            holder.tvLastMsg.setText(current.getLstMsg());
            holder.tvDisplayName.setText(current.getDisplayName());
//            holder.ivPic.setImageResource(current.getPicture());
            holder.ivPic.setImageResource(0);
        }
    }



    public void setChatListItems(List<ChatListItem> c) {
        this.chatListItems = c;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if(chatListItems != null) {
            return chatListItems.size();
        } else {
            return 0;
        }
    }

    public List<ChatListItem> getChatListItems() {
        return chatListItems;
    }




}
