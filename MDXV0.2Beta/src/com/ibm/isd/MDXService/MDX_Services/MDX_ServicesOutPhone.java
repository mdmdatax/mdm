/**
 * MDX_ServicesOutPhone.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.isd.MDXService.MDX_Services;

public class MDX_ServicesOutPhone  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String countrycode_mnphone;

    private java.lang.String areacode_mnphone;

    private java.lang.String exchange_mnphone;

    private java.lang.String number_mnphone;

    private java.lang.String extension_mnphone;

    private java.lang.String phoneformatted_mnphone;

    public MDX_ServicesOutPhone() {
    }

    public MDX_ServicesOutPhone(
           java.lang.String id,
           java.lang.String countrycode_mnphone,
           java.lang.String areacode_mnphone,
           java.lang.String exchange_mnphone,
           java.lang.String number_mnphone,
           java.lang.String extension_mnphone,
           java.lang.String phoneformatted_mnphone) {
           this.id = id;
           this.countrycode_mnphone = countrycode_mnphone;
           this.areacode_mnphone = areacode_mnphone;
           this.exchange_mnphone = exchange_mnphone;
           this.number_mnphone = number_mnphone;
           this.extension_mnphone = extension_mnphone;
           this.phoneformatted_mnphone = phoneformatted_mnphone;
    }


    /**
     * Gets the id value for this MDX_ServicesOutPhone.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this MDX_ServicesOutPhone.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the countrycode_mnphone value for this MDX_ServicesOutPhone.
     * 
     * @return countrycode_mnphone
     */
    public java.lang.String getCountrycode_mnphone() {
        return countrycode_mnphone;
    }


    /**
     * Sets the countrycode_mnphone value for this MDX_ServicesOutPhone.
     * 
     * @param countrycode_mnphone
     */
    public void setCountrycode_mnphone(java.lang.String countrycode_mnphone) {
        this.countrycode_mnphone = countrycode_mnphone;
    }


    /**
     * Gets the areacode_mnphone value for this MDX_ServicesOutPhone.
     * 
     * @return areacode_mnphone
     */
    public java.lang.String getAreacode_mnphone() {
        return areacode_mnphone;
    }


    /**
     * Sets the areacode_mnphone value for this MDX_ServicesOutPhone.
     * 
     * @param areacode_mnphone
     */
    public void setAreacode_mnphone(java.lang.String areacode_mnphone) {
        this.areacode_mnphone = areacode_mnphone;
    }


    /**
     * Gets the exchange_mnphone value for this MDX_ServicesOutPhone.
     * 
     * @return exchange_mnphone
     */
    public java.lang.String getExchange_mnphone() {
        return exchange_mnphone;
    }


    /**
     * Sets the exchange_mnphone value for this MDX_ServicesOutPhone.
     * 
     * @param exchange_mnphone
     */
    public void setExchange_mnphone(java.lang.String exchange_mnphone) {
        this.exchange_mnphone = exchange_mnphone;
    }


    /**
     * Gets the number_mnphone value for this MDX_ServicesOutPhone.
     * 
     * @return number_mnphone
     */
    public java.lang.String getNumber_mnphone() {
        return number_mnphone;
    }


    /**
     * Sets the number_mnphone value for this MDX_ServicesOutPhone.
     * 
     * @param number_mnphone
     */
    public void setNumber_mnphone(java.lang.String number_mnphone) {
        this.number_mnphone = number_mnphone;
    }


    /**
     * Gets the extension_mnphone value for this MDX_ServicesOutPhone.
     * 
     * @return extension_mnphone
     */
    public java.lang.String getExtension_mnphone() {
        return extension_mnphone;
    }


    /**
     * Sets the extension_mnphone value for this MDX_ServicesOutPhone.
     * 
     * @param extension_mnphone
     */
    public void setExtension_mnphone(java.lang.String extension_mnphone) {
        this.extension_mnphone = extension_mnphone;
    }


    /**
     * Gets the phoneformatted_mnphone value for this MDX_ServicesOutPhone.
     * 
     * @return phoneformatted_mnphone
     */
    public java.lang.String getPhoneformatted_mnphone() {
        return phoneformatted_mnphone;
    }


    /**
     * Sets the phoneformatted_mnphone value for this MDX_ServicesOutPhone.
     * 
     * @param phoneformatted_mnphone
     */
    public void setPhoneformatted_mnphone(java.lang.String phoneformatted_mnphone) {
        this.phoneformatted_mnphone = phoneformatted_mnphone;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MDX_ServicesOutPhone)) return false;
        MDX_ServicesOutPhone other = (MDX_ServicesOutPhone) obj;
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
            ((this.countrycode_mnphone==null && other.getCountrycode_mnphone()==null) || 
             (this.countrycode_mnphone!=null &&
              this.countrycode_mnphone.equals(other.getCountrycode_mnphone()))) &&
            ((this.areacode_mnphone==null && other.getAreacode_mnphone()==null) || 
             (this.areacode_mnphone!=null &&
              this.areacode_mnphone.equals(other.getAreacode_mnphone()))) &&
            ((this.exchange_mnphone==null && other.getExchange_mnphone()==null) || 
             (this.exchange_mnphone!=null &&
              this.exchange_mnphone.equals(other.getExchange_mnphone()))) &&
            ((this.number_mnphone==null && other.getNumber_mnphone()==null) || 
             (this.number_mnphone!=null &&
              this.number_mnphone.equals(other.getNumber_mnphone()))) &&
            ((this.extension_mnphone==null && other.getExtension_mnphone()==null) || 
             (this.extension_mnphone!=null &&
              this.extension_mnphone.equals(other.getExtension_mnphone()))) &&
            ((this.phoneformatted_mnphone==null && other.getPhoneformatted_mnphone()==null) || 
             (this.phoneformatted_mnphone!=null &&
              this.phoneformatted_mnphone.equals(other.getPhoneformatted_mnphone())));
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
        if (getCountrycode_mnphone() != null) {
            _hashCode += getCountrycode_mnphone().hashCode();
        }
        if (getAreacode_mnphone() != null) {
            _hashCode += getAreacode_mnphone().hashCode();
        }
        if (getExchange_mnphone() != null) {
            _hashCode += getExchange_mnphone().hashCode();
        }
        if (getNumber_mnphone() != null) {
            _hashCode += getNumber_mnphone().hashCode();
        }
        if (getExtension_mnphone() != null) {
            _hashCode += getExtension_mnphone().hashCode();
        }
        if (getPhoneformatted_mnphone() != null) {
            _hashCode += getPhoneformatted_mnphone().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MDX_ServicesOutPhone.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://MDX_Services.MDXService.isd.ibm.com", "MDX_ServicesOutPhone"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countrycode_mnphone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "countrycode_mnphone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areacode_mnphone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "areacode_mnphone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exchange_mnphone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "exchange_mnphone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number_mnphone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "number_mnphone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extension_mnphone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "extension_mnphone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phoneformatted_mnphone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phoneformatted_mnphone"));
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
