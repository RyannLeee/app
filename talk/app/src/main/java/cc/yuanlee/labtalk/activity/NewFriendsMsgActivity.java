package cc.yuanlee.labtalk.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

import cc.yuanlee.labtalk.R;
import cc.yuanlee.labtalk.adapter.NewFriendsMsgAdapter;
import cc.yuanlee.labtalk.db.InviteMessgeDao;
import cc.yuanlee.labtalk.domain.InviteMessage;

/**
 * Application and notification
 *
 */
public class NewFriendsMsgActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_friends_msg);

		ListView listView = (ListView) findViewById(R.id.list);
		InviteMessgeDao dao = new InviteMessgeDao(this);
		List<InviteMessage> msgs = dao.getMessagesList();
		Collections.reverse(msgs);

		NewFriendsMsgAdapter adapter = new NewFriendsMsgAdapter(this, 1, msgs);
		listView.setAdapter(adapter);
		dao.saveUnreadMessageCount(0);
		
	}

	public void back(View view) {
		finish();
	}
}
