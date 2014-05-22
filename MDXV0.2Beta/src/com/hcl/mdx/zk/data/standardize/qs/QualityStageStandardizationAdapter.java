package com.hcl.mdx.zk.data.standardize.qs;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.apache.commons.beanutils.DynaBean;

import com.hcl.mdx.data.model.AbstractModelObject;
import com.hcl.mdx.data.model.GenericRecord;
import com.hcl.mdx.data.model.MDXDynaBeanWrapper;
import com.hcl.mdx.file.data.TableAndClmMapDetailsObject;
import com.hcl.mdx.task.objects.ProgressMessageObject;
import com.hcl.mdx.zk.data.standardize.MDXDataStandardizationAdapter;
import com.ibm.isd.MDXService.MDX_Services.MDX_ServicesInAddress;
import com.ibm.isd.MDXService.MDX_Services.MDX_ServicesInOrgName;
import com.ibm.isd.MDXService.MDX_Services.MDX_ServicesInPersonName;
import com.ibm.isd.MDXService.MDX_Services.MDX_ServicesInPhone;
import com.ibm.isd.MDXService.MDX_Services.MDX_ServicesOutAddress;
import com.ibm.isd.MDXService.MDX_Services.MDX_ServicesOutOrgName;
import com.ibm.isd.MDXService.MDX_Services.MDX_ServicesOutPersonName;
import com.ibm.isd.MDXService.MDX_Services.MDX_ServicesOutPhone;
import com.ibm.isd.MDXService.MDX_Services.soapoverhttp.MDX_ServicesProxy;

public class QualityStageStandardizationAdapter extends MDXDataStandardizationAdapter{

	private Enum entityToStandardize;
	private TableAndClmMapDetailsObject sourceMap;
	private TableAndClmMapDetailsObject targetMap;

	private static enum LIST_OF_STDIZABLE_ENTITIES{
		PersonName,
		OrganizationName,
		Address,
		ContactDetails
	}

	public static enum QSPersonNameStdizationINAttributes{

		PersonNamePrefix,

		PersonGivenNameOne,

		PersonGivenNameTwo,

		PersonGivenNameThree,

		PersonGivenNameFour,

		PersonFamilyName,

		PersonNameSuffix
	}

	public static enum QSAddressStdizationINAttributes{
		AddressLineOne,

		AddressLineTwo,

		AddressLineThree,

		City,

		State,

		PostalCode,

		Country
	}

	public static enum QSAddressStdizationOUTAttributes{
		AddressLineOne,

		AddressLineTwo,

		HouseNumber,

		StreetPrefixDirectional,

		StreetPrefixType,

		StreetName,

		StreetSuffixType,

		BoxType,

		BoxValue,

		FloorType,

		FloorValue,

		UnitType,

		UnitValue,

		BuildingName,

		CityName,

		StateAbbreviation,

		Zip,

		CountryCode
	}

	public static enum QSContactsStdizationINAttributes{
		PhoneNumber
	}
	public static enum QSContactsStdizationOUTAttributes{
		countrycode_mnphone,

		areacode_mnphone,

		exchange_mnphone,

		number_mnphone,

		extension_mnphone,

		phoneformatted_mnphone
	}


	public static enum QSOrganizationNameStdizationINAttributes{
		OrgName
	}
	public static enum QSOrganizationNameStdizationOUTAttributes{
		MatchPrimaryName
	}

	public QualityStageStandardizationAdapter() {}

	public QualityStageStandardizationAdapter(
			TableAndClmMapDetailsObject sourceMap, 
			TableAndClmMapDetailsObject targetMap){
		this.sourceMap = sourceMap;
		this.targetMap = targetMap;
	}

	public static enum QSPersonNameStdizationOUTAttributes{
		NameType,

		GenderCode,

		NamePrefix,

		FirstName,

		MiddleName,

		PrimaryName,

		NameGeneration,

		NameSuffix,

		AdditionalName,

		MatchFirstName,

		MatchPrimaryName
	}

	@Override
	public ArrayList<AbstractModelObject> doStandardization(
			ArrayList<AbstractModelObject> input,
			ProgressMessageObject progressMessageObject) throws Exception {
		if(entityToStandardize.equals(LIST_OF_STDIZABLE_ENTITIES.Address)){
			progressMessageObject.setActivityName("QualityStageStandardizationAdapter: Stdizing Address Records");
			progressMessageObject.setProcessStatus("Initializing...");
			return doAddressStandardization(input, progressMessageObject);
		}
		else if(entityToStandardize.equals(LIST_OF_STDIZABLE_ENTITIES.ContactDetails)){
			progressMessageObject.setActivityName("QualityStageStandardizationAdapter: Stdizing Contact Records");
			progressMessageObject.setProcessStatus("Initializing...");
			return doContactsStandardization(input, progressMessageObject);
		}
		else if(entityToStandardize.equals(LIST_OF_STDIZABLE_ENTITIES.PersonName)){
			progressMessageObject.setActivityName("QualityStageStandardizationAdapter: Stdizing Individual Name Records");
			progressMessageObject.setProcessStatus("Initializing...");
			return doIndividualNameStandardization(input, progressMessageObject);
		}
		else if(entityToStandardize.equals(LIST_OF_STDIZABLE_ENTITIES.OrganizationName)){
			progressMessageObject.setActivityName("QualityStageStandardizationAdapter: Stdizing Organization Name Records");
			progressMessageObject.setProcessStatus("Initializing...");
			return doOrganizationPartyInfoStandardization(input, progressMessageObject);
		}
		else{
			throw new Exception("Invalid Stdization Option: "+ entityToStandardize);
		}
	}


	@Override
	public Enum[] getListOfStdizableEntities() {
		return LIST_OF_STDIZABLE_ENTITIES.values();
	}

	@Override
	public void setEntityToStandardize(Enum entity) {
		this.entityToStandardize = entity;		
	}

	@Override
	public Enum[] getListOfINAttributesForSelectedEntity(String entity) throws Exception {
		if (LIST_OF_STDIZABLE_ENTITIES.valueOf(entity).compareTo(LIST_OF_STDIZABLE_ENTITIES.PersonName) == 0){
			return QSPersonNameStdizationINAttributes.values();
		}
		else if (LIST_OF_STDIZABLE_ENTITIES.valueOf(entity).compareTo(LIST_OF_STDIZABLE_ENTITIES.OrganizationName) == 0){
			return QSOrganizationNameStdizationINAttributes.values();
		}
		else if (LIST_OF_STDIZABLE_ENTITIES.valueOf(entity).compareTo(LIST_OF_STDIZABLE_ENTITIES.Address) == 0){
			return QSAddressStdizationINAttributes.values();
		}
		else if (LIST_OF_STDIZABLE_ENTITIES.valueOf(entity).compareTo(LIST_OF_STDIZABLE_ENTITIES.ContactDetails) == 0){
			return QSContactsStdizationINAttributes.values();
		}
		throw new Exception("Invalid standardization entity type: "+entity);
	}

	@Override
	public Enum[] getListOfOUTAttributesForSelectedEntity(String entity) throws Exception {
		if (LIST_OF_STDIZABLE_ENTITIES.valueOf(entity).compareTo(LIST_OF_STDIZABLE_ENTITIES.PersonName) == 0){
			return QSPersonNameStdizationOUTAttributes.values();
		}
		else if (LIST_OF_STDIZABLE_ENTITIES.valueOf(entity).compareTo(LIST_OF_STDIZABLE_ENTITIES.OrganizationName) == 0){
			return QSOrganizationNameStdizationOUTAttributes.values();
		}
		else if (LIST_OF_STDIZABLE_ENTITIES.valueOf(entity).compareTo(LIST_OF_STDIZABLE_ENTITIES.Address) == 0){
			return QSAddressStdizationOUTAttributes.values();
		}
		else if (LIST_OF_STDIZABLE_ENTITIES.valueOf(entity).compareTo(LIST_OF_STDIZABLE_ENTITIES.ContactDetails) == 0){
			return QSContactsStdizationOUTAttributes.values();
		}
		throw new Exception("Invalid standardization entity type: "+entity);
	}

	public ArrayList<AbstractModelObject> doIndividualNameStandardization(
			ArrayList<AbstractModelObject> input,
			ProgressMessageObject progressMessageObject) throws Exception{

		ArrayList<AbstractModelObject> stdizedRecords = null;
		progressMessageObject.setProgressPercent(0);
		MDX_ServicesProxy MDX_ServicesProxy;
		try{
			MDX_ServicesProxy = new MDX_ServicesProxy();

			stdizedRecords = new ArrayList<AbstractModelObject>();
			for(int counter = 0; counter < input.size(); counter++){

				MDXDynaBeanWrapper nextInput = (MDXDynaBeanWrapper) input.get(counter);
				DynaBean nextInputBean = nextInput.getDynaBean();

				/*
				 * Set all the Fields
				 */
				MDX_ServicesInPersonName inPersonName = new MDX_ServicesInPersonName();
				inPersonName.setId(""+
						nextInputBean.get(
								sourceMap.getKeyColumnName().toLowerCase()));

				String personGivenNameOneCanonicalClmName = sourceMap.getCanonicalNameForClm(
						QSPersonNameStdizationINAttributes.PersonGivenNameOne.toString());
				if(personGivenNameOneCanonicalClmName != null){
					inPersonName.setPersongivennameone(
							(String) nextInputBean.get(personGivenNameOneCanonicalClmName.toLowerCase()));
				}

				String personFamilyNameCanonicalClmName = sourceMap.getCanonicalNameForClm(QSPersonNameStdizationINAttributes.PersonFamilyName.toString());
				if(personFamilyNameCanonicalClmName != null){
					inPersonName.setPersonfamilyname(
							(String) nextInputBean.get(personFamilyNameCanonicalClmName.toLowerCase()));
				}

				String personNamePrefixCanonicalClmName = sourceMap.getCanonicalNameForClm(QSPersonNameStdizationINAttributes.PersonNamePrefix.toString());
				if(personNamePrefixCanonicalClmName != null){
					inPersonName.setPersonnameprefix((String) nextInputBean.get(personNamePrefixCanonicalClmName.toLowerCase()));
				}

				String personNameSuffixCanonicalClmName = sourceMap.getCanonicalNameForClm(QSPersonNameStdizationINAttributes.PersonNameSuffix.toString());
				if(personNameSuffixCanonicalClmName != null){
					inPersonName.setPersonnamesuffix((String) nextInputBean.get(personNameSuffixCanonicalClmName.toLowerCase()));
				}
				MDX_ServicesInPersonName[] names = {inPersonName};

				/*
				 * Standardize the Record.
				 */
				MDX_ServicesOutPersonName[] stdizedNames = MDX_ServicesProxy.MDX_PERSONNAME(names);

				/*
				 * Build an AbstractModelObject out of the cleansed record.
				 */
				GenericRecord nextOutput = new GenericRecord();
				nextOutput.setId(stdizedNames[0].getId());
				nextOutput.addField(targetMap.getCanonicalNameForClm(QSPersonNameStdizationOUTAttributes.MatchFirstName.toString()), stdizedNames[0].getMatchfirstname());
				nextOutput.addField(targetMap.getCanonicalNameForClm(QSPersonNameStdizationOUTAttributes.MatchPrimaryName.toString()), stdizedNames[0].getMatchprimaryname());
				nextOutput.addField(targetMap.getCanonicalNameForClm(QSPersonNameStdizationOUTAttributes.NamePrefix.toString()), stdizedNames[0].getNameprefix());
				nextOutput.addField(targetMap.getCanonicalNameForClm(QSPersonNameStdizationOUTAttributes.NameSuffix.toString()), stdizedNames[0].getNamesuffix());
				stdizedRecords.add(nextOutput);

				/*
				 * Update Progress
				 */
				int progressPercent = (counter+1) * 100 / (input.size()); 
				String stdizeProgressString = 
					counter   + 
					" of "  +  input.size()  +  
					" records standardized. ("  + 
					progressPercent + "% completed.)"
					;

				progressMessageObject.setProcessStatus(stdizeProgressString);
				progressMessageObject.setProgressPercent(progressPercent);
			}
			progressMessageObject.setProcessStatus("Operation Completed");
			progressMessageObject.setProgressPercent(100);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error Standardizing Individual Name Info Records: "+e.getMessage());
		}
		return stdizedRecords;
	}
	/**
	 * 
	 * @param input
	 * @param progressMessageObject
	 * @return
	 * @throws Exception
	 */
	public ArrayList<AbstractModelObject> doOrganizationPartyInfoStandardization(ArrayList<AbstractModelObject> input,
			ProgressMessageObject progressMessageObject) throws Exception{

		ArrayList<AbstractModelObject> stdizedRecords = null;
		progressMessageObject.setProgressPercent(0);
		MDX_ServicesProxy MDX_ServicesProxy;

		try{
			MDX_ServicesProxy = new MDX_ServicesProxy();
			stdizedRecords = new ArrayList<AbstractModelObject>();
			for(int counter = 0; counter < input.size(); counter++){

				MDXDynaBeanWrapper nextInput = (MDXDynaBeanWrapper) input.get(counter);
				DynaBean nextInputBean = nextInput.getDynaBean();

				/*
				 * Set all the Fields
				 */
				MDX_ServicesInOrgName MDX_ServicesInOrgName = new MDX_ServicesInOrgName();

				MDX_ServicesInOrgName.setId(""+
						nextInputBean.get(
								sourceMap.getKeyColumnName().toLowerCase()));

				String orgNameCanonicalClmName = sourceMap.getCanonicalNameForClm(
						QSOrganizationNameStdizationINAttributes.OrgName.toString());

				if(orgNameCanonicalClmName != null){
					MDX_ServicesInOrgName.setName(
							(String) nextInputBean.get(orgNameCanonicalClmName.toLowerCase()));
				}

				MDX_ServicesInOrgName[] inOrgNames = {MDX_ServicesInOrgName};


				/*
				 * Stdize the Record.
				 */
				MDX_ServicesOutOrgName[] outOrgNames = MDX_ServicesProxy.MDX_ORGNAME(inOrgNames);

				/*
				 * Build an AbstractModelObject out of the cleansed record.
				 */
				GenericRecord nextOutput = new GenericRecord();
				nextOutput.setId(outOrgNames[0].getId());
				nextOutput.addField(
						targetMap.getCanonicalNameForClm(
								QSOrganizationNameStdizationOUTAttributes.MatchPrimaryName.toString()), 
								outOrgNames[0].getMatchprimaryname());

				stdizedRecords.add(nextOutput);

				/*
				 * Update Progress
				 */
				int progressPercent = (counter+1) * 100 / (input.size()); 
				String cleanseProgressString = 
					counter   + 
					" of "  +  input.size()  +  
					" records standardized. ("  + 
					progressPercent + "% completed.)"
					;

				progressMessageObject.setProcessStatus(cleanseProgressString);
				progressMessageObject.setProgressPercent(progressPercent);
			}

			progressMessageObject.setProcessStatus("Operation Completed");
			progressMessageObject.setProgressPercent(100);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error Standardizing Organization Party Info Records: "+e.getMessage());
		}
		return stdizedRecords;
	}



	public ArrayList<AbstractModelObject> doAddressStandardization(ArrayList<AbstractModelObject> input,
			ProgressMessageObject progressMessageObject) throws Exception{

		ArrayList<AbstractModelObject> stdizedRecords = null;
		progressMessageObject.setProgressPercent(0);
		MDX_ServicesProxy MDX_ServicesProxy;

		try{
			MDX_ServicesProxy = new MDX_ServicesProxy();
			stdizedRecords = new ArrayList<AbstractModelObject>();
			for(int counter = 0; counter < input.size(); counter++){

				MDXDynaBeanWrapper nextInput = (MDXDynaBeanWrapper) input.get(counter);
				DynaBean nextInputBean = nextInput.getDynaBean();

				/*
				 * Set all the Fields
				 */
				MDX_ServicesInAddress inAddress = new MDX_ServicesInAddress();
				inAddress.setId(Integer.parseInt(""+nextInputBean.get(
						sourceMap.getKeyColumnName().toLowerCase())));

				String addresslineoneCanonicalName = sourceMap.getCanonicalNameForClm(
						QSAddressStdizationINAttributes.AddressLineOne.toString());
				String addressLineOneValue = null;
				if(addresslineoneCanonicalName != null){
					addressLineOneValue = (String) nextInputBean.get(addresslineoneCanonicalName.toLowerCase()); 
					inAddress.setAddresslineone(addressLineOneValue);
				}

				String addresslinetwoCanonicalName = sourceMap.getCanonicalNameForClm(
						QSAddressStdizationINAttributes.AddressLineTwo.toString());
				String addressLineTwoValue = null;
				if(addresslinetwoCanonicalName != null){
					addressLineTwoValue = (String) nextInputBean.get(addresslinetwoCanonicalName.toLowerCase());
					inAddress.setAddresslinetwo(addressLineTwoValue);
				}

				String addresslinethreeCanonicalName = sourceMap.getCanonicalNameForClm(
						QSAddressStdizationINAttributes.AddressLineThree.toString());
				if(addresslinethreeCanonicalName != null){
					inAddress.setAddresslinethree(
							(String) nextInputBean.get(addresslinethreeCanonicalName.toLowerCase()));
				}

				String cityCanonicalName = sourceMap.getCanonicalNameForClm(
						QSAddressStdizationINAttributes.City.toString());
				if(cityCanonicalName != null){
					inAddress.setCity(
							(String) nextInputBean.get(cityCanonicalName.toLowerCase()));
				}

				String countryCanonicalName = sourceMap.getCanonicalNameForClm(
						QSAddressStdizationINAttributes.Country.toString());
				if(countryCanonicalName != null){
					inAddress.setCountry(
							(String) nextInputBean.get(countryCanonicalName.toLowerCase()));
				}

				String zipCodeCanonicalName = sourceMap.getCanonicalNameForClm(
						QSAddressStdizationINAttributes.PostalCode.toString());
				if(zipCodeCanonicalName != null){
					inAddress.setPostalcode(
							(String) nextInputBean.get(zipCodeCanonicalName.toLowerCase()));
				}

				String stateCanonicalName = sourceMap.getCanonicalNameForClm(
						QSAddressStdizationINAttributes.State.toString());
				if(stateCanonicalName != null){
					inAddress.setState(
							(String) nextInputBean.get(stateCanonicalName.toLowerCase()));
				}


				/*
				 * Standardize the Record.
				 */
				MDX_ServicesInAddress[] inAddresses = {inAddress};
				MDX_ServicesOutAddress[] outAddresses = MDX_ServicesProxy.MDX_ADDRESS_STAND(inAddresses);

				/*
				 * Build an AbstractModelObject out of the cleansed record.
				 */
				GenericRecord nextOutput = new GenericRecord();
				nextOutput.setId(""+outAddresses[0].getId());
				nextOutput.addField(
						targetMap.getCanonicalNameForClm(
								QSAddressStdizationOUTAttributes.StreetName.toString()), 
								buildStreetName(outAddresses[0]));


				String addressLine1 = buildAddressLine1(outAddresses[0]);
				String addressLine2 = buildAddressLine2(outAddresses[0]);

				/*
				 * If addressline1 is null, promote addressLine2 to addressLine1 and set addressLine2 to blank.
				 * If both are null, set the original value from the input.
				 */
				if(addressLine1 == null){
					if(addressLine2 != null){
						addressLine1 = addressLine2;
						addressLine2 = null;
					}
					else{
						addressLine1 = addressLineOneValue;
						addressLine2 = addressLineTwoValue;
					}
				}

				nextOutput.addField(
						targetMap.getCanonicalNameForClm(
								QSAddressStdizationOUTAttributes.AddressLineOne.toString()), 
								addressLine1);
				nextOutput.addField(
						targetMap.getCanonicalNameForClm(
								QSAddressStdizationOUTAttributes.AddressLineTwo.toString()), 
								addressLine2);

				nextOutput.addField(
						targetMap.getCanonicalNameForClm(
								QSAddressStdizationOUTAttributes.HouseNumber.toString()), 
								outAddresses[0].getHousenumber());

				nextOutput.addField(
						targetMap.getCanonicalNameForClm(
								QSAddressStdizationOUTAttributes.StreetPrefixDirectional.toString()), 
								outAddresses[0].getStreetprefixdirectional());

				nextOutput.addField(
						targetMap.getCanonicalNameForClm(
								QSAddressStdizationOUTAttributes.StreetSuffixType.toString()), 
								outAddresses[0].getStreetsuffixtype());
				nextOutput.addField(
						targetMap.getCanonicalNameForClm(
								QSAddressStdizationOUTAttributes.StreetPrefixType.toString()), 
								outAddresses[0].getStreetprefixtype());

				stdizedRecords.add(nextOutput);

				/*
				 * Update Progress
				 */
				int progressPercent = (counter+1) * 100 / (input.size()); 
				String stdizeProgressString = 
					counter   + 
					" of "  +  input.size()  +  
					" records cleansed. ("  + 
					progressPercent + "% completed.)"
					;

				progressMessageObject.setProcessStatus(stdizeProgressString);
				progressMessageObject.setProgressPercent(progressPercent);
			}

			progressMessageObject.setProcessStatus("Operation Completed");
			progressMessageObject.setProgressPercent(100);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error Standardizing Address Records: "+e.getMessage());
		}
		return stdizedRecords;
	}

	public ArrayList<AbstractModelObject> doContactsStandardization(ArrayList<AbstractModelObject> input,
			ProgressMessageObject progressMessageObject) throws Exception{

		ArrayList<AbstractModelObject> stdizedRecords = null;
		progressMessageObject.setProgressPercent(0);

		MDX_ServicesProxy MDX_ServicesProxy;
		try{
			MDX_ServicesProxy = new MDX_ServicesProxy();
			stdizedRecords = new ArrayList<AbstractModelObject>();
			for(int counter = 0; counter < input.size(); counter++){

				MDXDynaBeanWrapper nextInput = (MDXDynaBeanWrapper) input.get(counter);
				DynaBean nextInputBean = nextInput.getDynaBean();

				/*
				 * Set all the Fields
				 */
				MDX_ServicesInPhone MDX_ServicesInPhone = new MDX_ServicesInPhone();
				MDX_ServicesInPhone.setId(""+nextInputBean.get(
						sourceMap.getKeyColumnName().toLowerCase()));

				String referenceNumberCanonicalName = sourceMap.getCanonicalNameForClm(
						QSContactsStdizationINAttributes.PhoneNumber.toString());
				BigDecimal referenceNumberValue = null;
				if(referenceNumberCanonicalName != null){
					referenceNumberValue = convertPhoneNumStrToBigDecimal((String) nextInputBean.get(referenceNumberCanonicalName.toLowerCase()));
					MDX_ServicesInPhone.setReferencenumber(referenceNumberValue); 

				}

				/*
				 * Standardize the Record.
				 */
				MDX_ServicesInPhone[] inPhones = {MDX_ServicesInPhone};
				MDX_ServicesOutPhone[] outPhones = MDX_ServicesProxy.MDX_PHONE_STAND(inPhones);

				/*
				 * Build an AbstractModelObject out of the cleansed record.
				 */
				GenericRecord nextOutput = new GenericRecord();
				nextOutput.setId(""+outPhones[0].getId());
				nextOutput.addField(
						targetMap.getCanonicalNameForClm(
								QSContactsStdizationOUTAttributes.areacode_mnphone.toString()), 
								outPhones[0].getAreacode_mnphone());

				nextOutput.addField(
						targetMap.getCanonicalNameForClm(
								QSContactsStdizationOUTAttributes.exchange_mnphone.toString()), 
								outPhones[0].getExchange_mnphone());

				nextOutput.addField(
						targetMap.getCanonicalNameForClm(
								QSContactsStdizationOUTAttributes.extension_mnphone.toString()), 
								outPhones[0].getExtension_mnphone());

				nextOutput.addField(
						targetMap.getCanonicalNameForClm(
								QSContactsStdizationOUTAttributes.countrycode_mnphone.toString()), 
								outPhones[0].getCountrycode_mnphone());

				nextOutput.addField(
						targetMap.getCanonicalNameForClm(
								QSContactsStdizationOUTAttributes.phoneformatted_mnphone.toString()), 
								(outPhones[0].getPhoneformatted_mnphone() != null) ? outPhones[0].getPhoneformatted_mnphone() : referenceNumberValue);


				stdizedRecords.add(nextOutput);

				/*
				 * Update Progress
				 */
				int progressPercent = (counter+1) * 100 / (input.size()); 
				String stdizeProgressString = 
					counter   + 
					" of "  +  input.size()  +  
					" records cleansed. ("  + 
					progressPercent + "% completed.)"
					;

				progressMessageObject.setProcessStatus(stdizeProgressString);
				progressMessageObject.setProgressPercent(progressPercent);
			}

			progressMessageObject.setProcessStatus("Operation Completed");
			progressMessageObject.setProgressPercent(100);
		}
		catch(Exception e){
			e.printStackTrace();
			throw new Exception("Error Standardizing Contact Info Records: "+e.getMessage());
		}
		return stdizedRecords;
	}


	private BigDecimal convertPhoneNumStrToBigDecimal(String phoneNumberStr){
		try{
			return new BigDecimal(phoneNumberStr);
		}
		catch (Exception e) {
			return null;
		}
	}

	private String buildAddressLine1(MDX_ServicesOutAddress standardizedAddress){
		//	Address Line 1 => (housenumber|' '|streetprefixdirectional|' '|STREET NAME) / (boxtype|' '|boxvalue)
		//	Address Line 2 => floortype|' '|floorvalue|' '|unittype|' '|unitvalue|' '|buildingname / (boxtype|' '|boxvalue)

		String addressLine1 = "";
		boolean addressLine1Built = false;

		if(standardizedAddress.getHousenumber() != null){
			addressLine1 += standardizedAddress.getHousenumber() + " ";
			addressLine1Built = true;
		}
		if(standardizedAddress.getStreetprefixdirectional() != null){
			addressLine1 += standardizedAddress.getStreetprefixdirectional() + " ";
			addressLine1Built = true;
		}
		if(standardizedAddress.getStreetname() != null){
			addressLine1 += buildStreetName(standardizedAddress);
			addressLine1Built = true;
		}
		if(!addressLine1Built){
			if(standardizedAddress.getBoxtype() != null){
				addressLine1 += standardizedAddress.getBoxtype() + " ";
			}
			if(standardizedAddress.getBoxvalue() != null){
				addressLine1 += standardizedAddress.getBoxvalue();
			}
		}

		if(addressLine1.trim().length() > 0){
			return addressLine1;
		}
		else{
			return null;
		}
	}

	private String buildAddressLine2(MDX_ServicesOutAddress standardizedAddress){
		//	Address Line 1 => (housenumber|' '|streetprefixdirectional|' '|STREET NAME) / (boxtype|' '|boxvalue)
		//	Address Line 2 => floortype|' '|floorvalue|' '|unittype|' '|unitvalue|' '|buildingname / (boxtype|' '|boxvalue)

		String addressLine2 = "";

		if(standardizedAddress.getFloortype() != null){
			addressLine2 += standardizedAddress.getFloortype() + ", ";
		}
		if(standardizedAddress.getFloorvalue() != null){
			addressLine2 += standardizedAddress.getFloorvalue() + ", ";
		}
		if(standardizedAddress.getUnittype() != null){
			addressLine2 += standardizedAddress.getUnittype();
		}
		if(standardizedAddress.getUnitvalue() != null){
			addressLine2 += standardizedAddress.getUnitvalue();
		}
		if(standardizedAddress.getBuildingname() != null){
			addressLine2 += standardizedAddress.getBuildingname();
		}
		else{
			if(standardizedAddress.getBoxtype() != null){
				addressLine2 += standardizedAddress.getBoxtype() + ", ";
			}
			if(standardizedAddress.getBoxvalue() != null){
				addressLine2 += standardizedAddress.getBoxvalue();
			}
		}

		if(addressLine2.trim().length() > 0){
			return addressLine2;
		}
		else{
			return null;
		}
	}

	private String buildStreetName(MDX_ServicesOutAddress standardizedAddress){
		String streetName = "";

		if(standardizedAddress.getStreetprefixtype() != null){
			streetName += standardizedAddress.getStreetprefixtype() + " ";
		}
		if(standardizedAddress.getStreetname() != null){
			streetName += standardizedAddress.getStreetname() + " ";
		}
		if(standardizedAddress.getStreetsuffixtype() != null){
			streetName += standardizedAddress.getStreetsuffixtype();
		}

		if(streetName.trim().length() > 0){
			return streetName;
		}
		else{
			return null;
		}
	}

	@Override
	public Enum getEnumValueOfEntity(String entity) {
		return LIST_OF_STDIZABLE_ENTITIES.valueOf(entity);
	}	


}
