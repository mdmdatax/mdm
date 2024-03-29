/**
 * MDX_ServicesInPhone.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.isd.MDXService.MDX_Services;

public class MDX_ServicesInPhone  implements java.io.Serializable {
    private java.lang.String id;

    private java.math.BigDecimal referencenumber;

    public MDX_ServicesInPhone() {
    }

    public MDX_ServicesInPhone(
           java.lang.String id,
           java.math.BigDecimal referencenumber) {
           this.id = id;
           this.referencenumber = referencenumber;
    }


    /**
     * Gets the id value for this MDX_ServicesInPhone.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this MDX_ServicesInPhone.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the referencenumber value for this MDX_ServicesInPhone.
     * 
     * @return referencenumber
     */
    public java.math.BigDecimal getReferencenumber() {
        return referencenumber;
    }


    /**
     * Sets the referencenumber value for this MDX_ServicesInPhone.
     * 
     * @param referencenumber
     */
    public void setReferencenumber(java.math.BigDecimal referencenumber) {
        this.referencenumber = referencenumber;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MDX_ServicesInPhone)) return false;
        MDX_ServicesInPhone other = (MDX_ServicesInPhone) obj;
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
            ((this.referencenumber==null && other.getReferencenumber()==null) || 
             (this.referencenumber!=null &&
              this.referencenumber.equals(other.getReferencenumber())));
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
        if (getReferencenumber() != null) {
            _hashCode += getReferencenumber().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MDX_ServicesInPhone.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://MDX_Services.MDXService.isd.ibm.com", "MDX_ServicesInPhone"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referencenumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "referencenumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
