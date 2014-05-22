package com.hcl.mdx.util;

import com.hcl.mdx.database.objects.connection.ConnectionDetailsObject;

public class Constants  {

	/*
	 * Set by the SessionListner class.
	 */
	public static String TMP_FOLDER_PATH = "";


	/*
	 * Database
	 */
	public static final ConnectionDetailsObject CONNECTION_DETAILS_OBJECT
	= new ConnectionDetailsObject(
			"hclt.dpsg.net", 
			1521, 
			"XE", 
			"datax", 
			"mohit");

	public static final String CANONICAL_MAP_TABLE_NAME =
		"CANONICAL_MAPPING";

	public static final String PHONE_LOOKUP_TABLE_NAME =
		"PHONE_LOOKUP";

	public static final String USER_DATA_TABLE_NAME =
		"IDL_STAGING";


	/*
	 * Session
	 */
	public static final String SESSION_DATA_DETAILS_VARIABLE_NAME = "DataDetails";
	public static final String SESSION_DATA_COLUMNS_VARIABLE_NAME = "DataColumnDetails";
	public static final String SESSION_DATA_CONTENTS_LIST_NAME = "DisplayList";
	public static final String SESSION_DETAILS_OBJECT_NAME = "SessionDetailsObject";
	public static final String PAGE_COMPOSER_PROPERTY_NAME = "PageComposer";
	public static final String BLANK_PAGE_URI = "blankPage.zul";

	/*
	 * MDX work flow status identifiers
	 */
	public static final String INITIAL_DATA_LOAD_STATUS_ID ="WF1";
	public static final String CLEANSE_STATUS_ID ="WF2";
	public static final String STANDARDIZE_STATUS_ID ="WF3";
	public static final String DE_DUP_STATUS_ID ="WF4";
	public static final String GOLDEN_RECORD_STATUS_ID ="WF5";

	/*
	 * CSS class names
	 */

	public static final String CSS_LV1_SUB_GRID_HEADER_COLOR_CLASS ="lv1SubGridClmHeader";
	public static final String CSS_BLACK_LABEL_9px_CLASS = "blackLabel";
	public static final String CSS_PASTEL_BLUE_LABEL_9px_CLASS = "pastelBlueLabel";
	public static final String CSS_FOREST_GREEN_LABEL_9px_CLASS = "forestGreenLabel";
	public static final String CSS_RED_LABEL_9px_CLASS = "redLabel";
	public static final String CSS_TOOLBAR_BUTTON_LABEL_9px_PLAIN_CLASS = "toolBarButton9px";
	public static final String CSS_HIGHLIGHTED_ROW_COLOR_CLASS = "preferredInfoHighlightColor";
	public static final String CSS_PROGRESS_BAR_COMPLETE_COLOR_CLASS = "progressMeterCompletedStyle";
	public static final String CSS_PROGRESS_BAR_ERROR_COLOR_CLASS = "progressMeterErrorStyle";
	public static final String CSS_FILTER_IMG_STYLE_CLASS = "filterImgStyleClass";
	public static final String FILTER_IMG_LOCATION = "images/funnel.png";
	public static final String DELETE_IMG_LOCATION ="images/delete.png";
	public static final String EDIT_IMG_LOCATION ="images/edit_item.png";
	public static final String REFRESH_VIEW_IMG_LOCATION ="images/refresh-view.gif";
	public static final String VIEW_DETAILS_IMG_LOCATION ="images/view_detail.png";
	public static final String RUN_OPERATION_IMG_LOCATION ="images/action_go.gif";

	/*
	 * ZK filter component IDs
	 */
	public static final String FIRST_NAME_FILTER_TEXBOX_ID = "firstNameFilter";
	public static final String MIDDLE_NAME_FILTER_TEXBOX_ID = "middleNameFilter";
	public static final String PREFIX_FILTER_TEXBOX_ID = "prefixFilter";
	public static final String GENDER_FILTER_TEXBOX_ID = "genderFilter";
	public static final String NATIONALITY_FILTER_TEXBOX_ID = "nationalityFilter";
	public static final String LAST_NAME_FILTER_TEXBOX_ID = "lastNameFilter";
	public static final String PARTY_ID_FILTER_TEXBOX_ID = "partyIdFilter";
	public static final String ORG_NAME_FILTER_TEXBOX_ID = "orgNameFilter";
	public static final String INDUSTRY_TYPE_FILTER_TEXBOX_ID = "industryTypeFilter";
	public static final String ORG_TYPE_FILTER_TEXBOX_ID = "orgTypeFilter";
	public static final String BIZ_CONTACT_NAME_FILTER_TEXBOX_ID = "bizContactNameFilter";

	/*
	 * Adapter configuration file names.
	 */
	//public static final String SIPERIAN_CONFIG_FILE = "com/hcl/mdx/util/SipClient.properties";
	public static final String SIPERIAN_CONFIG_FILE = "D:/MDX_CONFIG/SipClient.properties";

	/*
	 * MDX Table Prefixes
	 */
	public static final String 	IN_TABLE_PREFIX = "IN";
	public static final String 	OUT_TABLE_PREFIX = "OUT";
	public static final String 	MASTER_TABLE_PREFIX = "MST";
	public static final String 	ERROR_TABLE_PREFIX = "ERR";

	/*
	 * Match
	 */
	public static final String MATCH_PROCESSING_ACTION_A2 = "A2";
	public static final String MATCH_PROCESSING_ACTION_B = "B";
	public static final String QUALITY_STAGE_PARTY_TYPE_PERSON = "P";
	public static final String QUALITY_STAGE_PARTY_TYPE_ORGANIZATION = "O";
	public static final String QUALITY_STAGE_TRANSACTION_RECORD_TYPE = "T";
	public static final String QUALITY_STAGE_CANDIDATE_RECORD_TYPE = "C";
	public static final String QUALITY_STAGE_MATCH_RESULTS_TABLE_NAME = "SYS_PROB_MTCH_RES";
	public static final String QUALITY_STAGE_MATCH_RULE_ID = "QS100";

	/*
	 * Standardization
	 */
	public static enum StandardizationAdapterType{
		QualityStage
	}
	
	/*
	 * Queries
	 */
	public static final String QUERY_LIST_OF_TABLES="SELECT PHYSICAL_NAME FROM SYS_MDX_TB_MAP";
	public static final String COLOUMN_NAME="PHYSICAL_NAME";
}

