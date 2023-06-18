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
import com.example.makore.entities.Message;

import java.util.List;

public class MessageListAdapter extends  RecyclerView.Adapter<MessageListAdapter.MessageListItemViewHolder>{

    private final LayoutInflater mInflater;
    private List<Message> messageListItems;

    class MessageListItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView content;
        private final TextView date;


        private MessageListItemViewHolder(View itemView) {
            super(itemView);
            //change to the id
            this.content = itemView.findViewById(R.id.tvDisplayName);
            // change to the id
            this.date = itemView.findViewById(R.id.tvLastMsg);
        }
    }

    public MessageListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MessageListAdapter.MessageListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //change to the layout
        View itemView = mInflater.inflate(R.layout.chat_list_item_layout, parent, false);
        MessageListAdapter.MessageListItemViewHolder viewHolder = new MessageListAdapter.MessageListItemViewHolder(itemView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MessageListAdapter.MessageListItemViewHolder holder, int position) {
        if(messageListItems != null) {
            final messageListItem current = messageListItems.get(position);
            holder.tvLastMsg.setText(current.getLstMsg());
            holder.tvDisplayName.setText(current.getDisplayName());
//            holder.ivPic.setImageResource(current.getPicture());
            holder.ivPic.setImageResource(0);
        }
    }


    public void setMessageListItems(List<Message> c) {
        this.messageListItems = c;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(messageListItems != null) {
            return messageListItems.size();
        } else {
            return 0;
        }
    }

    public List<Message> getMessageListItems() {
        return messageListItems;
    }

}
