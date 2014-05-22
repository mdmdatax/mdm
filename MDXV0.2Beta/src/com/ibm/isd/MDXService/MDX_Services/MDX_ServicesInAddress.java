/**
 * MDX_ServicesInAddress.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.isd.MDXService.MDX_Services;

public class MDX_ServicesInAddress  implements java.io.Serializable {
    private java.lang.Integer id;

    private java.lang.String addresslineone;

    private java.lang.String addresslinetwo;

    private java.lang.String addresslinethree;

    private java.lang.String city;

    private java.lang.String state;

    private java.lang.String postalcode;

    private java.lang.String country;

    public MDX_ServicesInAddress() {
    }

    public MDX_ServicesInAddress(
           java.lang.Integer id,
           java.lang.String addresslineone,
           java.lang.String addresslinetwo,
           java.lang.String addresslinethree,
           java.lang.String city,
           java.lang.String state,
           java.lang.String postalcode,
           java.lang.String country) {
           this.id = id;
           this.addresslineone = addresslineone;
           this.addresslinetwo = addresslinetwo;
           this.addresslinethree = addresslinethree;
           this.city = city;
           this.state = state;
           this.postalcode = postalcode;
           this.country = country;
    }


    /**
     * Gets the id value for this MDX_ServicesInAddress.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this MDX_ServicesInAddress.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the addresslineone value for this MDX_ServicesInAddress.
     * 
     * @return addresslineone
     */
    public java.lang.String getAddresslineone() {
        return addresslineone;
    }


    /**
     * Sets the addresslineone value for this MDX_ServicesInAddress.
     * 
     * @param addresslineone
     */
    public void setAddresslineone(java.lang.String addresslineone) {
        this.addresslineone = addresslineone;
    }


    /**
     * Gets the addresslinetwo value for this MDX_ServicesInAddress.
     * 
     * @return addresslinetwo
     */
    public java.lang.String getAddresslinetwo() {
        return addresslinetwo;
    }


    /**
     * Sets the addresslinetwo value for this MDX_ServicesInAddress.
     * 
     * @param addresslinetwo
     */
    public void setAddresslinetwo(java.lang.String addresslinetwo) {
        this.addresslinetwo = addresslinetwo;
    }


    /**
     * Gets the addresslinethree value for this MDX_ServicesInAddress.
     * 
     * @return addresslinethree
     */
    public java.lang.String getAddresslinethree() {
        return addresslinethree;
    }


    /**
     * Sets the addresslinethree value for this MDX_ServicesInAddress.
     * 
     * @param addresslinethree
     */
    public void setAddresslinethree(java.lang.String addresslinethree) {
        this.addresslinethree = addresslinethree;
    }


    /**
     * Gets the city value for this MDX_ServicesInAddress.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this MDX_ServicesInAddress.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the state value for this MDX_ServicesInAddress.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this MDX_ServicesInAddress.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the postalcode value for this MDX_ServicesInAddress.
     * 
     * @return postalcode
     */
    public java.lang.String getPostalcode() {
        return postalcode;
    }


    /**
     * Sets the postalcode value for this MDX_ServicesInAddress.
     * 
     * @param postalcode
     */
    public void setPostalcode(java.lang.String postalcode) {
        this.postalcode = postalcode;
    }


    /**
     * Gets the country value for this MDX_ServicesInAddress.
     * 
     * @return country
     */
    public java.lang.String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this MDX_ServicesInAddress.
     * 
     * @param country
     */
    public void setCountry(java.lang.String country) {
        this.country = country;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MDX_ServicesInAddress)) return false;
        MDX_ServicesInAddress other = (MDX_ServicesInAddress) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.addresslineone==null && other.getAddresslineone()==null) || 
             (this.addresslineone!=null &&
              this.addresslineone.equals(other.getAddresslineone()))) &&
            ((this.addresslinetwo==null && other.getAddresslinetwo()==null) || 
             (this.addresslinetwo!=null &&
              this.addresslinetwo.equals(other.getAddresslinetwo()))) &&
            ((this.addresslinethree==null && other.getAddresslinethree()==null) || 
             (this.addresslinethree!=null &&
              this.addresslinethree.equals(other.getAddresslinethree()))) &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.postalcode==null && other.getPostalcode()==null) || 
             (this.postalcode!=null &&
              this.postalcode.equals(other.getPostalcode()))) &&
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getAddresslineone() != null) {
            _hashCode += getAddresslineone().hashCode();
        }
        if (getAddresslinetwo() != null) {
            _hashCode += getAddresslinetwo().hashCode();
        }
        if (getAddresslinethree() != null) {
            _hashCode += getAddresslinethree().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getPostalcode() != null) {
            _hashCode += getPostalcode().hashCode();
        }
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MDX_ServicesInAddress.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://MDX_Services.MDXService.isd.ibm.com", "MDX_ServicesInAddress"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addresslineone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addresslineone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addresslinetwo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addresslinetwo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addresslinethree");
        elemField.setXmlName(new javax.xml.namespace.QName("", "addresslinethree"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("", "city"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postalcode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "postalcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
