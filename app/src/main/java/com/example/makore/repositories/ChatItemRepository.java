package com.example.makore.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.makore.R;
import com.example.makore.entities.ChatListItem;

import java.util.LinkedList;
import java.util.List;

public class ChatItemRepository {
//private ChatItemDao dao;
private ChatListData ChatListData;
//private ChatAPI api;

 public ChatItemRepository() {
//    LocalDatabase db = LocalDatabase.getInstance();
//    dao = db.ChatDao(); // getting data from local storage
    ChatListData = new ChatListData();
//    api = new ChatAPI(ChatListData, dao); // getting data from server
    }


    class ChatListData extends MutableLiveData<List<ChatListItem>> {
    public ChatListData() {
        super();
        List<ChatListItem> s = new LinkedList<>();
        s.add(new ChatListItem("roi", R.drawable.ic_check, "aaaa"));
        s.add(new ChatListItem("roi2", R.drawable.ic_check, "aaaa2"));
        s.add(new ChatListItem("roi3", R.drawable.ic_check, "aaaa3"));
        s.add(new ChatListItem("roi4", R.drawable.ic_check, "aaaa4"));
        s.add(new ChatListItem("roi5", R.drawable.ic_check, "aaaa5"));
        s.add(new ChatListItem("roi6", R.drawable.ic_check, "aaaa6"));
        s.add(new ChatListItem("roi7", R.drawable.ic_check, "aaaa7"));
        s.add(new ChatListItem("roi8", R.drawable.ic_check, "aaaa8"));
        s.add(new ChatListItem("roi9", R.drawable.ic_check, "aaaa9"));
        s.add(new ChatListItem("roi10", R.drawable.ic_check, "aaaa10"));
        s.add(new ChatListItem("roi11", R.drawable.ic_check, "aaaa11"));
        s.add(new ChatListItem("roi12", R.drawable.ic_check, "aaaa12"));
        s.add(new ChatListItem("roi13", R.drawable.ic_check, "aaaa13"));
        s.add(new ChatListItem("roi14", R.drawable.ic_check, "aaaa14"));
        s.add(new ChatListItem("roi15", R.drawable.ic_check, "aaaa15"));
        s.add(new ChatListItem("roi16", R.drawable.ic_check, "aaaa16"));
        s.add(new ChatListItem("roi17", R.drawable.ic_check, "aaaa17"));
        s.add(new ChatListItem("roi18", R.drawable.ic_check, "aaaa18"));
        s.add(new ChatListItem("roi19", R.drawable.ic_check, "aaaa19"));
        s.add(new ChatListItem("roi20", R.drawable.ic_check, "aaaa20"));
        setValue(s);
//        setValue(new LinkedList<>());
//        ChatAPI chatAPI = new ChatAPI();
        //and doing everything that needs to get all the chatListItems.
    }

             @Override
             protected void onActive() {
                 super.onActive();
//                 new Thread(()->{ChatListData.postValue(dao.get());}).start();
             }
 }
    public LiveData <List<ChatListItem>> getAll() {
        return ChatListData;

    }
//    public void add(final ChatListItem chatItem) {
//            api.add(chatItem);
//        }
//
//       public void delete(final ChatListItem chatItem) {
//            api.delete(chatItem);
//     }
//
//         public void reload() {
//             api.get();
//        }

    }

