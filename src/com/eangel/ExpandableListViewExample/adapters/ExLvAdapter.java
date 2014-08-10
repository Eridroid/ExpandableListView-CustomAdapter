package com.eangel.ExpandableListViewExample.adapters;

import java.util.ArrayList;

import com.eangel.ExpandableListViewExample.model.ChildData;
import com.eangel.ExpandableListViewExample.model.GroupData;
import com.eridroid.customexpandablelistview.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;



 /**
 * A custom ExpandableListAdapter.
 */ 
public class ExLvAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<GroupData> groups;
	
	// Custom Constructor to pass the context and Data
		public ExLvAdapter(Context categoryFragment,ArrayList<GroupData> groups) {
			this.context = categoryFragment;
			this.groups = groups;
		}
		
	//Implement the Overridden methods
	@Override
	public int getGroupCount() {
		
		//return the group count  which is the size of the ArrayList holding groups
		return groups.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		
		//return the children count based on groupPosition 
		if(groups.get(groupPosition).getChildren() != null)
			return groups.get(groupPosition).getChildren().size();
		else
			return 0;
			
	}

	@Override
	public Object getGroup(int groupPosition) {
		
		//return a group object a specified groupPosition
		return groups.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		
		//return the child object at a specified groupPostion & childPosition
		return groups.get(groupPosition).getChildren().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		
	   //return an ID for the specific group clicked based on the groupPosition	
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		//return an ID for the specific group clicked based on the groupPosition & childPosition
		return childPosition;
		
	}

	@Override
	public boolean hasStableIds() {
		//we have stable ID return true
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		//holds the convertView
		View cv = null;
		
		//Get a group object @ position groupPosition
		  final GroupData group = (GroupData) getGroup(groupPosition);
				
		//Inflate the Group item layout
		 LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  if (convertView == null) {
				cv = (View)inflater.inflate(R.layout.group_item_layout, null);
		  }
		  else
		  {
			  cv = (View) convertView;
		  }
		//Get a reference to the group_item elements and set their values
		   TextView txtId = (TextView) cv.findViewById(R.id.txtId);
		   TextView txtTitle = (TextView) cv.findViewById(R.id.txtTitle);
		   
		   if (group != null) {
					
			      txtId.setText(Integer.toString(group.getGroupId()));
				  txtTitle.setText(group.getGroupTitle());
				}
		   
		   return cv;
				
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		//Get the child object at the specific groupPostion & childPosition
		final ChildData child = (ChildData) getChild(groupPosition,childPosition);

		//Get the reference to the LayoutInflator to inflate the Child item layout
		final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.child_item_layout, null);
		}
		
		
		//Get a reference to the child_item elements and set their values
		TextView txtId = (TextView) convertView.findViewById(R.id.child_txtId);
		TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
		
		if (child != null) {
				
			int childId = child.getchildId();
			txtId.setText(Integer.toString(childId));
			
			String childName = child.getchildName();
			txtName.setText(childName);
		}
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		//We set it to True so that we can set onClick listener on the Children
		return true;
	}

}
