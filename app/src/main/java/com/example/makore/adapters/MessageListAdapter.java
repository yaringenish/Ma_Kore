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
            this.content = itemView.findViewById(R.id.tvMessageContent);
            this.date = itemView.findViewById(R.id.tvMessageDateTime);
        }
    }

    public MessageListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MessageListAdapter.MessageListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.message_item, parent, false);
        MessageListAdapter.MessageListItemViewHolder viewHolder = new MessageListAdapter.MessageListItemViewHolder(itemView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MessageListAdapter.MessageListItemViewHolder holder, int position) {
        if(messageListItems != null) {
            final Message current = messageListItems.get(position);
            holder.content.setText(current.getContent());
            holder.date.setText(current.getCreated().toString());
//            holder.ivPic.setImageResource(current.getPicture());
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
