package cc.yuanlee.labtalk.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.hyphenate.chat.EMClient;

import java.util.List;

import cc.yuanlee.easeui.widget.EaseAlertDialog;
import cc.yuanlee.labtalk.AppHelper;
import cc.yuanlee.labtalk.R;

public class AddContactActivity extends BaseActivity{
	private EditText editText;
	private RelativeLayout searchedUserLayout;
	private TextView nameText;
	private Button searchBtn;
	private String toAddUsername;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		TextView mTextView = (TextView) findViewById(R.id.add_list_friends);
		
		editText = (EditText) findViewById(R.id.edit_note);
		String strAdd = getResources().getString(R.string.add_friend);
		mTextView.setText(strAdd);
		String strUserName = getResources().getString(R.string.user_name);
		editText.setHint(strUserName);
		searchedUserLayout = (RelativeLayout) findViewById(R.id.ll_user);
		nameText = (TextView) findViewById(R.id.name);
		searchBtn = (Button) findViewById(R.id.search);
	}
	
	
	/**
	 * search contact
	 * @param v
	 */
	public void searchContact(View v) {
		final String name = editText.getText().toString().toLowerCase();
		String saveText = searchBtn.getText().toString();
		
		if (getString(R.string.button_search).equals(saveText)) {
			toAddUsername = name;
			if(TextUtils.isEmpty(name)) {
				new EaseAlertDialog(this, R.string.Please_enter_a_username).show();
				return;
			}

			final AVQuery<AVUser> query = new AVQuery<>("_User");
			query.whereEqualTo("username", name);
			query.findInBackground(new FindCallback<AVUser>() {
                @Override
                public void done(List<AVUser> list, AVException e) {
                    if (e == null) {
                        if (list != null && list.size() > 0) {
                            //show the username and add button if user exist
                            searchedUserLayout.setVisibility(View.VISIBLE);
                            nameText.setText(toAddUsername);
                        } else {
                            searchResultNull();
                        }
                    } else {
                        Toast.makeText(AddContactActivity.this, R.string.Network_error, Toast.LENGTH_SHORT).show();
                    }
                }
            });
		}
	}

    public void searchResultNull(){
        searchedUserLayout.setVisibility(View.INVISIBLE);
        new EaseAlertDialog(this, R.string.user_not_exist).show();
    }
	
	/**
	 *  add contact
	 * @param view
	 */
	public void addContact(View view){
		if(EMClient.getInstance().getCurrentUser().equals(nameText.getText().toString())){
			new EaseAlertDialog(this, R.string.not_add_myself).show();
			return;
		}
		
		if(AppHelper.getInstance().getContactList().containsKey(nameText.getText().toString())){
		    //let the user know the contact already in your contact list
		    if(EMClient.getInstance().contactManager().getBlackListUsernames().contains(nameText.getText().toString())){
		        new EaseAlertDialog(this, R.string.user_already_in_contactlist).show();
		        return;
		    }
			new EaseAlertDialog(this, R.string.This_user_is_already_your_friend).show();
			return;
		}
		
		progressDialog = new ProgressDialog(this);
		String stri = getResources().getString(R.string.Is_sending_a_request);
		progressDialog.setMessage(stri);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.show();
		
		new Thread(new Runnable() {
			public void run() {
				
				try {
					//demo use a hardcode reason here, you need let user to input if you like
					String s = getResources().getString(R.string.Add_a_friend);
					EMClient.getInstance().contactManager().addContact(toAddUsername, s);
					runOnUiThread(new Runnable() {
						public void run() {
							progressDialog.dismiss();
							String s1 = getResources().getString(R.string.send_successful);
							Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
						}
					});
				} catch (final Exception e) {
					runOnUiThread(new Runnable() {
						public void run() {
							progressDialog.dismiss();
							String s2 = getResources().getString(R.string.Request_add_buddy_failure);
							Toast.makeText(getApplicationContext(), s2 + e.getMessage(), Toast.LENGTH_LONG).show();
						}
					});
				}
			}
		}).start();
	}
	
	public void back(View v) {
		finish();
	}
}
