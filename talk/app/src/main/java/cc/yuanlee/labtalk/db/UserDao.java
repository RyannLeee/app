package cc.yuanlee.labtalk.db;

import android.content.Context;

import java.util.List;
import java.util.Map;

import cc.yuanlee.easeui.domain.EaseUser;

public class UserDao {
	public static final String TABLE_NAME = "user";
	public static final String COLUMN_NAME_ID = "username";
	public static final String COLUMN_NAME_NICK = "nick";
	public static final String COLUMN_NAME_AVATAR = "avatar";
	
	public static final String DISABLED_TABLE_NAME = "disabled";
	public static final String COLUMN_NAME_DISABLED_GROUPS = "disabled_groups";
	public static final String COLUMN_NAME_DISABLED_IDS = "disabled_ids";

	public UserDao(Context context) {
	}

	/**
	 * save contact list
	 * 
	 * @param contactList
	 */
	public void saveContactList(List<EaseUser> contactList) {
	    DBManager.getInstance().saveContactList(contactList);
	}

	/**
	 * get contact list
	 * 
	 * @return
	 */
	public Map<String, EaseUser> getContactList() {
		
	    return DBManager.getInstance().getContactList();
	}
	
	/**
	 * delete a contact
	 * @param username
	 */
	public void deleteContact(String username){
	    DBManager.getInstance().deleteContact(username);
	}
	
	/**
	 * save a contact
	 * @param user
	 */
	public void saveContact(EaseUser user){
	    DBManager.getInstance().saveContact(user);
	}
	
	public void setDisabledGroups(List<String> groups){
	    DBManager.getInstance().setDisabledGroups(groups);
    }
    
    public List<String>  getDisabledGroups(){       
        return DBManager.getInstance().getDisabledGroups();
    }
    
    public void setDisabledIds(List<String> ids){
        DBManager.getInstance().setDisabledIds(ids);
    }
    
    public List<String> getDisabledIds(){
        return DBManager.getInstance().getDisabledIds();
    }

}
