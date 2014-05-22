package com.hcl.mdx.data.model;


import org.apache.ddlutils.model.Database;

import com.hcl.mdx.database.objects.MDXTableMetadata;
import com.hcl.mdx.zk.ui.grid.builder.DataLoadDetailsGridBuilder;
import com.hcl.mdx.zk.ui.grid.builder.SchemaTableGridBuilder;
import com.hcl.mdx.zk.ui.listbox.builder.FlatFileDetailsListboxBuilder;

public class SessionDetailsObject implements AbstractModelObject{

		
	String xmlSchemaFilePath;
	
	SchemaTableGridBuilder schemaTableGridBuilder;
	
	FlatFileDetailsListboxBuilder flatFileDetailsListboxBuilder;
	
	Database mdxSchema;
	
	Database schemaToBuild;
	
	DataLoadDetailsGridBuilder dataLoadDetailsGridBuilder;
	
	MDXTableMetadata mdxTableMetadata;
	
		
	public SessionDetailsObject(){
		schemaTableGridBuilder = new SchemaTableGridBuilder();
		flatFileDetailsListboxBuilder = new FlatFileDetailsListboxBuilder();
		dataLoadDetailsGridBuilder = new DataLoadDetailsGridBuilder();
		mdxTableMetadata = new MDXTableMetadata();
	}
	
	
	
	/**
	 * @return the mdxTableMetadata
	 */
	public MDXTableMetadata getMdxTableMetadata() {
		return mdxTableMetadata;
	}



	/**
	 * @param mdxTableMetadata the mdxTableMetadata to set
	 */
	public void setMdxTableMetadata(MDXTableMetadata mdxTableMetadata) {
		this.mdxTableMetadata = mdxTableMetadata;
	}



	/**
	 * @return the dataLoadDetailsGridBuilder
	 */
	public DataLoadDetailsGridBuilder getDataLoadDetailsGridBuilder() {
		return dataLoadDetailsGridBuilder;
	}


	/**
	 * @param dataLoadDetailsGridBuilder the dataLoadDetailsGridBuilder to set
	 */
	public void setDataLoadDetailsGridBuilder(
			DataLoadDetailsGridBuilder dataLoadDetailsGridBuilder) {
		this.dataLoadDetailsGridBuilder = dataLoadDetailsGridBuilder;
	}


	/**
	 * @return the schemaToBuild
	 */
	public Database getSchemaToBuild() {
		return schemaToBuild;
	}


	/**
	 * @param schemaToBuild the schemaToBuild to set
	 */
	public void setSchemaToBuild(Database schemaToBuild) {
		this.schemaToBuild = schemaToBuild;
	}


	/**
	 * @return the mdxSchema
	 */
	public Database getMdxSchema() {
		return mdxSchema;
	}


	/**
	 * @param mdxSchema the mdxSchema to set
	 */
	public void setMdxSchema(Database mdxSchema) {
		this.mdxSchema = mdxSchema;
	}


	/**
	 * @return the flatFileDetailsListboxBuilder
	 */
	public FlatFileDetailsListboxBuilder getFlatFileDetailsListboxBuilder() {
		return flatFileDetailsListboxBuilder;
	}


	/**
	 * @param flatFileDetailsListboxBuilder the flatFileDetailsListboxBuilder to set
	 */
	public void setFlatFileDetailsListboxBuilder(
			FlatFileDetailsListboxBuilder flatFileDetailsListboxBuilder) {
		this.flatFileDetailsListboxBuilder = flatFileDetailsListboxBuilder;
	}

	
	/**
	 * @return the schemaTableGridBuilder
	 */
	public SchemaTableGridBuilder getSchemaTableGridBuilder() {
		return schemaTableGridBuilder;
	}



	/**
	 * @param schemaTableGridBuilder the schemaTableGridBuilder to set
	 */
	public void setSchemaTableGridBuilder(
			SchemaTableGridBuilder schemaTableGridBuilder) {
		this.schemaTableGridBuilder = schemaTableGridBuilder;
	}



	/**
	 * @return the xmlSchemaFilePath
	 */
	public String getXmlSchemaFilePath() {
		return xmlSchemaFilePath;
	}

	/**
	 * @param xmlSchemaFilePath the xmlSchemaFilePath to set
	 */
	public void setXmlSchemaFilePath(String xmlSchemaFilePath) {
		this.xmlSchemaFilePath = xmlSchemaFilePath;
	}



	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}
	
}
