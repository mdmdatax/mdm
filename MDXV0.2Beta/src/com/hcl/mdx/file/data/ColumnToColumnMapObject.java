package com.hcl.mdx.file.data;

import com.hcl.mdx.database.objects.CanonicalColumnObject;

public class ColumnToColumnMapObject  {

	String name;
	CanonicalColumnObject canonicalColumnObject;
	
	public ColumnToColumnMapObject(String name, CanonicalColumnObject canonicalColumnObject) {
		this.name = name;
		this.canonicalColumnObject = canonicalColumnObject;
	}
	
	public CanonicalColumnObject getCanonicalColumnObject() {
		return canonicalColumnObject;
	}
	
	public String getName() {
		return name;
	}
	
}
