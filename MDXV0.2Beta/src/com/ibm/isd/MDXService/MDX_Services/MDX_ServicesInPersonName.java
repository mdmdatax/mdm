/**
 * MDX_ServicesInPersonName.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.isd.MDXService.MDX_Services;

public class MDX_ServicesInPersonName  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String personnameprefix;

    private java.lang.String persongivennameone;

    private java.lang.String persongivennametwo;

    private java.lang.String persongivennamethree;

    private java.lang.String persongivennamefour;

    private java.lang.String personfamilyname;

    private java.lang.String personnamesuffix;

    public MDX_ServicesInPersonName() {
    }

    public MDX_ServicesInPersonName(
           java.lang.String id,
           java.lang.String personnameprefix,
           java.lang.String persongivennameone,
           java.lang.String persongivennametwo,
           java.lang.String persongivennamethree,
           java.lang.String persongivennamefour,
           java.lang.String personfamilyname,
           java.lang.String personnamesuffix) {
           this.id = id;
           this.personnameprefix = personnameprefix;
           this.persongivennameone = persongivennameone;
           this.persongivennametwo = persongivennametwo;
           this.persongivennamethree = persongivennamethree;
           this.persongivennamefour = persongivennamefour;
           this.personfamilyname = personfamilyname;
           this.personnamesuffix = personnamesuffix;
    }


    /**
     * Gets the id value for this MDX_ServicesInPersonName.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this MDX_ServicesInPersonName.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the personnameprefix value for this MDX_ServicesInPersonName.
     * 
     * @return personnameprefix
     */
    public java.lang.String getPersonnameprefix() {
        return personnameprefix;
    }


    /**
     * Sets the personnameprefix value for this MDX_ServicesInPersonName.
     * 
     * @param personnameprefix
     */
    public void setPersonnameprefix(java.lang.String personnameprefix) {
        this.personnameprefix = personnameprefix;
    }


    /**
     * Gets the persongivennameone value for this MDX_ServicesInPersonName.
     * 
     * @return persongivennameone
     */
    public java.lang.String getPersongivennameone() {
        return persongivennameone;
    }


    /**
     * Sets the persongivennameone value for this MDX_ServicesInPersonName.
     * 
     * @param persongivennameone
     */
    public void setPersongivennameone(java.lang.String persongivennameone) {
        this.persongivennameone = persongivennameone;
    }


    /**
     * Gets the persongivennametwo value for this MDX_ServicesInPersonName.
     * 
     * @return persongivennametwo
     */
    public java.lang.String getPersongivennametwo() {
        return persongivennametwo;
    }


    /**
     * Sets the persongivennametwo value for this MDX_ServicesInPersonName.
     * 
     * @param persongivennametwo
     */
    public void setPersongivennametwo(java.lang.String persongivennametwo) {
        this.persongivennametwo = persongivennametwo;
    }


    /**
     * Gets the persongivennamethree value for this MDX_ServicesInPersonName.
     * 
     * @return persongivennamethree
     */
    public java.lang.String getPersongivennamethree() {
        return persongivennamethree;
    }


    /**
     * Sets the persongivennamethree value for this MDX_ServicesInPersonName.
     * 
     * @param persongivennamethree
     */
    public void setPersongivennamethree(java.lang.String persongivennamethree) {
        this.persongivennamethree = persongivennamethree;
    }


    /**
     * Gets the persongivennamefour value for this MDX_ServicesInPersonName.
     * 
     * @return persongivennamefour
     */
    public java.lang.String getPersongivennamefour() {
        return persongivennamefour;
    }


    /**
     * Sets the persongivennamefour value for this MDX_ServicesInPersonName.
     * 
     * @param persongivennamefour
     */
    public void setPersongivennamefour(java.lang.String persongivennamefour) {
        this.persongivennamefour = persongivennamefour;
    }


    /**
     * Gets the personfamilyname value for this MDX_ServicesInPersonName.
     * 
     * @return personfamilyname
     */
    public java.lang.String getPersonfamilyname() {
        return personfamilyname;
    }


    /**
     * Sets the personfamilyname value for this MDX_ServicesInPersonName.
     * 
     * @param personfamilyname
     */
    public void setPersonfamilyname(java.lang.String personfamilyname) {
        this.personfamilyname = personfamilyname;
    }


    /**
     * Gets the personnamesuffix value for this MDX_ServicesInPersonName.
     * 
     * @return personnamesuffix
     */
    public java.lang.String getPersonnamesuffix() {
        return personnamesuffix;
    }


    /**
     * Sets the personnamesuffix value for this MDX_ServicesInPersonName.
     * 
     * @param personnamesuffix
     */
    public void setPersonnamesuffix(java.lang.String personnamesuffix) {
        this.personnamesuffix = personnamesuffix;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MDX_ServicesInPersonName)) return false;
        MDX_ServicesInPersonName other = (MDX_ServicesInPersonName) obj;
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
            ((this.personnameprefix==null && other.getPersonnameprefix()==null) || 
             (this.personnameprefix!=null &&
              this.personnameprefix.equals(other.getPersonnameprefix()))) &&
            ((this.persongivennameone==null && other.getPersongivennameone()==null) || 
             (this.persongivennameone!=null &&
              this.persongivennameone.equals(other.getPersongivennameone()))) &&
            ((this.persongivennametwo==null && other.getPersongivennametwo()==null) || 
             (this.persongivennametwo!=null &&
              this.persongivennametwo.equals(other.getPersongivennametwo()))) &&
            ((this.persongivennamethree==null && other.getPersongivennamethree()==null) || 
             (this.persongivennamethree!=null &&
              this.persongivennamethree.equals(other.getPersongivennamethree()))) &&
            ((this.persongivennamefour==null && other.getPersongivennamefour()==null) || 
             (this.persongivennamefour!=null &&
              this.persongivennamefour.equals(other.getPersongivennamefour()))) &&
            ((this.personfamilyname==null && other.getPersonfamilyname()==null) || 
             (this.personfamilyname!=null &&
              this.personfamilyname.equals(other.getPersonfamilyname()))) &&
            ((this.personnamesuffix==null && other.getPersonnamesuffix()==null) || 
             (this.personnamesuffix!=null &&
              this.personnamesuffix.equals(other.getPersonnamesuffix())));
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
        if (getPersonnameprefix() != null) {
            _hashCode += getPersonnameprefix().hashCode();
        }
        if (getPersongivennameone() != null) {
            _hashCode += getPersongivennameone().hashCode();
        }
        if (getPersongivennametwo() != null) {
            _hashCode += getPersongivennametwo().hashCode();
        }
        if (getPersongivennamethree() != null) {
            _hashCode += getPersongivennamethree().hashCode();
        }
        if (getPersongivennamefour() != null) {
            _hashCode += getPersongivennamefour().hashCode();
        }
        if (getPersonfamilyname() != null) {
            _hashCode += getPersonfamilyname().hashCode();
        }
        if (getPersonnamesuffix() != null) {
            _hashCode += getPersonnamesuffix().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MDX_ServicesInPersonName.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://MDX_Services.MDXService.isd.ibm.com", "MDX_ServicesInPersonName"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("personnameprefix");
        elemField.setXmlName(new javax.xml.namespace.QName("", "personnameprefix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("persongivennameone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "persongivennameone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("persongivennametwo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "persongivennametwo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("persongivennamethree");
        elemField.setXmlName(new javax.xml.namespace.QName("", "persongivennamethree"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("persongivennamefour");
        elemField.setXmlName(new javax.xml.namespace.QName("", "persongivennamefour"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("personfamilyname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "personfamilyname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("personnamesuffix");
        elemField.setXmlName(new javax.xml.namespace.QName("", "personnamesuffix"));
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
