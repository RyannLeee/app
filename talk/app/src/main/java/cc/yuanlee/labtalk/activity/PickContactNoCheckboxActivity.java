package cc.yuanlee.labtalk.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cc.yuanlee.easeui.adapter.EaseContactAdapter;
import cc.yuanlee.easeui.domain.EaseUser;
import cc.yuanlee.easeui.widget.EaseSidebar;
import cc.yuanlee.labtalk.Constant;
import cc.yuanlee.labtalk.AppHelper;
import cc.yuanlee.labtalk.R;

@SuppressLint("Registered")
public class PickContactNoCheckboxActivity extends BaseActivity {

	protected EaseContactAdapter contactAdapter;
	private List<EaseUser> contactList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pick_contact_no_checkbox);
		ListView listView = (ListView) findViewById(R.id.list);
		EaseSidebar sidebar = (EaseSidebar) findViewById(R.id.sidebar);
		sidebar.setListView(listView);
		contactList = new ArrayList<EaseUser>();
		// get contactlist
		getContactList();
		// set adapter
		contactAdapter = new EaseContactAdapter(this, R.layout.ease_row_contact, contactList);
		listView.setAdapter(contactAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onListItemClick(position);
			}
		});

	}

	protected void onListItemClick(int position) {
		setResult(RESULT_OK, new Intent().putExtra("username", contactAdapter.getItem(position)
				.getUsername()));
		finish();
	}

	public void back(View view) {
		finish();
	}

	private void getContactList() {
		contactList.clear();
		Map<String, EaseUser> users = AppHelper.getInstance().getContactList();
		for (Entry<String, EaseUser> entry : users.entrySet()) {
			if (!entry.getKey().equals(Constant.NEW_FRIENDS_USERNAME) && !entry.getKey().equals(Constant.GROUP_USERNAME) && !entry.getKey().equals(Constant.CHAT_ROOM))
				contactList.add(entry.getValue());
		}
		// sort
        Collections.sort(contactList, new Comparator<EaseUser>() {

            @Override
            public int compare(EaseUser lhs, EaseUser rhs) {
                if(lhs.getInitialLetter().equals(rhs.getInitialLetter())){
                    return lhs.getNick().compareTo(rhs.getNick());
                }else{
                    if("#".equals(lhs.getInitialLetter())){
                        return 1;
                    }else if("#".equals(rhs.getInitialLetter())){
                        return -1;
                    }
                    return lhs.getInitialLetter().compareTo(rhs.getInitialLetter());
                }
                
            }
        });
	}

}
