package com.eangel.ExpandableListViewExample;


import java.util.ArrayList;

import com.eangel.ExpandableListViewExample.adapters.ExLvAdapter;
import com.eangel.ExpandableListViewExample.model.ChildData;
import com.eangel.ExpandableListViewExample.model.GroupData;
import com.eridroid.customexpandablelistview.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class ExLvFragment extends Fragment {

	 ExpandableListView expListView;
	 
	 //Default Constructor
	 public ExLvFragment(){}
	 
	 
	/**
	* Implement onCreateView method to draw fragment UI
    */  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		//Create the view containing the fragment
		 View rootView = (View) inflater.inflate(R.layout.exlv_fragment, container, false);
		 
		//Getting reference to the ExpandableListView in the Fragment
		   expListView = (ExpandableListView) rootView.findViewById(R.id.expand_listv);
		 
		 //Create static data to display
	        final ArrayList<GroupData> myGroups = createGroupData();
		   
		   //Create a new instance of the ExpandableListView Adapter
		   final ExLvAdapter expListAdapter = new ExLvAdapter(
				   getActivity(), myGroups);
	       //Set the adapter to the Ex Lv 
		   expListView.setAdapter(expListAdapter);
		   
		 //Set the group indicator of expandable list to right side
			 setGroupIndicatorToRight();
	     	 
		 //Collapse other Group items on a Group Expand set onGroupExpandListener 
			 expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
                  //hold the previously expanded group     
				 int previousItem = -1;
					@Override
					public void onGroupExpand(int groupPosition) {
						if (groupPosition != previousItem)
							//if a different Group is expanded, collapse the previous Expanded Group
							expListView.collapseGroup(previousItem);
						     previousItem = groupPosition;							
						}
				});
			 
			//To set on Click listener for the Group
			 expListView.setOnGroupClickListener(new OnGroupClickListener(){

				@Override
				public boolean onGroupClick(ExpandableListView parent, View v,
						int groupPosition, long id) {
					Toast.makeText(getActivity(),"Group "+ groupPosition +" is Clicked", Toast.LENGTH_SHORT).show();
					return false;
				}});
			 
			
			//To set on Click listener for the Children
			 expListView.setOnChildClickListener(new OnChildClickListener(){
				@Override
				public boolean onChildClick(ExpandableListView parent, View v,
						int groupPosition, int childPosition, long id) {
					Toast.makeText(getActivity(),"Group "+ groupPosition +" Child "+ childPosition +" is Clicked", Toast.LENGTH_SHORT).show();
					return false;
				}});
			 
			 
		 return rootView;
	}
	
	/**
	 * Method to get/create data and fill ArrayList with GroupData objects
	 */ 
	private ArrayList<GroupData> createGroupData()
	{
		ArrayList<GroupData> GroupList = new ArrayList<GroupData>();
		
		// Let's get our data from string-array resource
		String[] mGroups = getResources().getStringArray(R.array.groups);
		
		for(int i=0; i< mGroups.length; i++)
		{
			//Create a new Group object
			GroupData aGroup = new GroupData();
			aGroup.setGroupId(i);
			aGroup.setGroupTitle(mGroups[i]);
	
			//let's get each group children from string-array
			String[] mChildren = null;
			switch(i)
			{
			  case 0: mChildren = getActivity().getResources().getStringArray(R.array.Cars);break;
			  case 1: mChildren = getActivity().getResources().getStringArray(R.array.Colors);break;
			  case 2: mChildren = getActivity().getResources().getStringArray(R.array.Birds);break;
			  case 3: mChildren = getActivity().getResources().getStringArray(R.array.Genders);break;
			  case 4: mChildren = getActivity().getResources().getStringArray(R.array.ProgrammingLanguages);break;
			  default: mChildren = new String[] {"Child1","Child2","Child3"};
			}
				
			//Create an ArrayList of children
			ArrayList<ChildData> ChildList = new ArrayList<ChildData>();
			
			for(int j=0; j< mChildren.length; j++)
			{
				//Create a new child object
				ChildData aChild = new ChildData();
				
				//fill the child object
				aChild.setchildId(j);
				aChild.setchildName(mChildren[j]);
					
				//add each child object to the arrayList of Children
				ChildList.add(aChild);
			}
			
			//Finally add the ArrayList of children to the current GroupList object
			aGroup.setChildren(ChildList);
			
			//Finally add the current group object to the ArrayList of GroupList objects
			GroupList.add(aGroup);
			
		}
			
		return GroupList;
	}
	
	
	/**
	 * Method to set Group indicator to the Right side.
	 */
	private void setGroupIndicatorToRight() {
		
		    // Get the screen Metrics 
	        DisplayMetrics dm = new DisplayMetrics();
	        ((MainActivity)getActivity()).getWindowManager().getDefaultDisplay().getMetrics(dm);
	        //Get the screen width
	        int width = dm.widthPixels;
	        //Set the Indicator Bounds based on the API Version
	      if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
	        	expListView.setIndicatorBounds(width - getDipsFromPixel(75), width - getDipsFromPixel(35));
	      	} else {
	      	   expListView.setIndicatorBoundsRelative(width - getDipsFromPixel(60), width - getDipsFromPixel(25));
	       	}
	    }
	 
	/**
	 * Method to get dips from pixels
	 */
	    public int getDipsFromPixel(float pixels) {
	        // Get the screen's density scale
	        final float scale = getResources().getDisplayMetrics().density;
	        // Convert the dps to pixels, based on density scale
	        return (int) (pixels * scale + 0.5f);
	    }
}
