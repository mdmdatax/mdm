/**
 * MDX_ServicesOutOrgName.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.isd.MDXService.MDX_Services;

public class MDX_ServicesOutOrgName  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String nametype;

    private java.lang.String primaryname;

    private java.lang.String namesuffix;

    private java.lang.String matchprimaryname;

    public MDX_ServicesOutOrgName() {
    }

    public MDX_ServicesOutOrgName(
           java.lang.String id,
           java.lang.String nametype,
           java.lang.String primaryname,
           java.lang.String namesuffix,
           java.lang.String matchprimaryname) {
           this.id = id;
           this.nametype = nametype;
           this.primaryname = primaryname;
           this.namesuffix = namesuffix;
           this.matchprimaryname = matchprimaryname;
    }


    /**
     * Gets the id value for this MDX_ServicesOutOrgName.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this MDX_ServicesOutOrgName.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the nametype value for this MDX_ServicesOutOrgName.
     * 
     * @return nametype
     */
    public java.lang.String getNametype() {
        return nametype;
    }


    /**
     * Sets the nametype value for this MDX_ServicesOutOrgName.
     * 
     * @param nametype
     */
    public void setNametype(java.lang.String nametype) {
        this.nametype = nametype;
    }


    /**
     * Gets the primaryname value for this MDX_ServicesOutOrgName.
     * 
     * @return primaryname
     */
    public java.lang.String getPrimaryname() {
        return primaryname;
    }


    /**
     * Sets the primaryname value for this MDX_ServicesOutOrgName.
     * 
     * @param primaryname
     */
    public void setPrimaryname(java.lang.String primaryname) {
        this.primaryname = primaryname;
    }


    /**
     * Gets the namesuffix value for this MDX_ServicesOutOrgName.
     * 
     * @return namesuffix
     */
    public java.lang.String getNamesuffix() {
        return namesuffix;
    }


    /**
     * Sets the namesuffix value for this MDX_ServicesOutOrgName.
     * 
     * @param namesuffix
     */
    public void setNamesuffix(java.lang.String namesuffix) {
        this.namesuffix = namesuffix;
    }


    /**
     * Gets the matchprimaryname value for this MDX_ServicesOutOrgName.
     * 
     * @return matchprimaryname
     */
    public java.lang.String getMatchprimaryname() {
        return matchprimaryname;
    }


    /**
     * Sets the matchprimaryname value for this MDX_ServicesOutOrgName.
     * 
     * @param matchprimaryname
     */
    public void setMatchprimaryname(java.lang.String matchprimaryname) {
        this.matchprimaryname = matchprimaryname;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MDX_ServicesOutOrgName)) return false;
        MDX_ServicesOutOrgName other = (MDX_ServicesOutOrgName) obj;
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
            ((this.nametype==null && other.getNametype()==null) || 
             (this.nametype!=null &&
              this.nametype.equals(other.getNametype()))) &&
            ((this.primaryname==null && other.getPrimaryname()==null) || 
             (this.primaryname!=null &&
              this.primaryname.equals(other.getPrimaryname()))) &&
            ((this.namesuffix==null && other.getNamesuffix()==null) || 
             (this.namesuffix!=null &&
              this.namesuffix.equals(other.getNamesuffix()))) &&
            ((this.matchprimaryname==null && other.getMatchprimaryname()==null) || 
             (this.matchprimaryname!=null &&
              this.matchprimaryname.equals(other.getMatchprimaryname())));
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
        if (getNametype() != null) {
            _hashCode += getNametype().hashCode();
        }
        if (getPrimaryname() != null) {
            _hashCode += getPrimaryname().hashCode();
        }
        if (getNamesuffix() != null) {
            _hashCode += getNamesuffix().hashCode();
        }
        if (getMatchprimaryname() != null) {
            _hashCode += getMatchprimaryname().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MDX_ServicesOutOrgName.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://MDX_Services.MDXService.isd.ibm.com", "MDX_ServicesOutOrgName"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nametype");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nametype"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "primaryname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("namesuffix");
        elemField.setXmlName(new javax.xml.namespace.QName("", "namesuffix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchprimaryname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "matchprimaryname"));
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
