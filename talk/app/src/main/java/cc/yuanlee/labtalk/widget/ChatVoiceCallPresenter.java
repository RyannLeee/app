package cc.yuanlee.labtalk.widget;

import android.content.Context;
import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import cc.yuanlee.easeui.widget.chatrow.EaseChatRow;
import cc.yuanlee.easeui.widget.presenter.EaseChatRowPresenter;

public class ChatVoiceCallPresenter extends EaseChatRowPresenter {
    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        return new ChatRowVoiceCall(cxt, message, position, adapter);
    }
}
