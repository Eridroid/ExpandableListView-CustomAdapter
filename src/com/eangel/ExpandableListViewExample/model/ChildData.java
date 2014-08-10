package com.eangel.ExpandableListViewExample.model;



public class ChildData {
	
	// The Child private fields
		private int childId;
		private String childName;
			
		//Default constructor
		public ChildData(){}
		
		//constructor for ChildData which initializes all fields
		public ChildData(int childId, String childName)
		{
			this.childId = childId;
			this.childName = childName;
		}
		
		//getters and setters for the fields
		public int getchildId()
		{
			return this.childId;
		}
		
		public void setchildId(int childId)
		{
			this.childId = childId;
		}
		
		public String getchildName()
		{
			return this.childName;
		}
	
		public void setchildName(String childName)
		{
			this.childName = childName;
		}
		
		
}
