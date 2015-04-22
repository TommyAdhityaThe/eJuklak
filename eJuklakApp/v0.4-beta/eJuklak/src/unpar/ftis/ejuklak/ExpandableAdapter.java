package unpar.ftis.ejuklak;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ExpandableAdapter extends BaseExpandableListAdapter{
	
	Activity a;
	ArrayList<DrawerItem> al;
	
	public ExpandableAdapter(Activity c, ArrayList<DrawerItem> al) {
		// TODO Auto-generated constructor stub
		this.a = c;
		this.al = al;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return this.al.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return this.al.get(groupPosition).innerList.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return this.al.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return this.al.get(groupPosition).innerList.get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = this.a.getLayoutInflater();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.group_layout, null);
		}
		((TextView) convertView).setText(this.al.get(groupPosition).name);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = this.a.getLayoutInflater();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.child_layout, null);
		}
		((TextView) convertView).setText(this.al.get(groupPosition).innerList.get(childPosition));
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
