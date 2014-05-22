package com.hcl.mdx.database.objects;

public class CanonicalColumnObject  implements Comparable<CanonicalColumnObject>{

	String columnName;
	String dataType;
	String identifier;
		
	public CanonicalColumnObject(String columnName, String dataType, String identifier) {
		this.columnName = columnName;
		this.dataType = dataType;
		this.identifier = identifier;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier)  {
		this.identifier = identifier;
	}

	public String getColumnName() {
		return columnName;
	}

	public String getDataType() {
		return dataType;
	}
	
	@Override
	public String toString(){
		return columnName;
	}

	@Override
	public int compareTo(CanonicalColumnObject o) {
		
		return columnName.compareTo(o.getColumnName());
	}
}
