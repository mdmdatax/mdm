/**
 * MDX_Services_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.isd.MDXService.MDX_Services.soapoverhttp;

public class MDX_Services_ServiceLocator extends org.apache.axis.client.Service implements com.ibm.isd.MDXService.MDX_Services.soapoverhttp.MDX_Services_Service {

    public MDX_Services_ServiceLocator() {
    }


    public MDX_Services_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MDX_Services_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for MDX_ServicesSOAP
    private java.lang.String MDX_ServicesSOAP_address = "http://ibminfosphere:9080/wisd/MDXService/MDX_Services";

    public java.lang.String getMDX_ServicesSOAPAddress() {
        return MDX_ServicesSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MDX_ServicesSOAPWSDDServiceName = "MDX_ServicesSOAP";

    public java.lang.String getMDX_ServicesSOAPWSDDServiceName() {
        return MDX_ServicesSOAPWSDDServiceName;
    }

    public void setMDX_ServicesSOAPWSDDServiceName(java.lang.String name) {
        MDX_ServicesSOAPWSDDServiceName = name;
    }

    public com.ibm.isd.MDXService.MDX_Services.soapoverhttp.MDX_Services_PortType getMDX_ServicesSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(MDX_ServicesSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMDX_ServicesSOAP(endpoint);
    }

    public com.ibm.isd.MDXService.MDX_Services.soapoverhttp.MDX_Services_PortType getMDX_ServicesSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ibm.isd.MDXService.MDX_Services.soapoverhttp.MDX_ServicesSOAPSoapBindingStub _stub = new com.ibm.isd.MDXService.MDX_Services.soapoverhttp.MDX_ServicesSOAPSoapBindingStub(portAddress, this);
            _stub.setPortName(getMDX_ServicesSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMDX_ServicesSOAPEndpointAddress(java.lang.String address) {
        MDX_ServicesSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ibm.isd.MDXService.MDX_Services.soapoverhttp.MDX_Services_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ibm.isd.MDXService.MDX_Services.soapoverhttp.MDX_ServicesSOAPSoapBindingStub _stub = new com.ibm.isd.MDXService.MDX_Services.soapoverhttp.MDX_ServicesSOAPSoapBindingStub(new java.net.URL(MDX_ServicesSOAP_address), this);
                _stub.setPortName(getMDX_ServicesSOAPWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("MDX_ServicesSOAP".equals(inputPortName)) {
            return getMDX_ServicesSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://MDX_Services.MDXService.isd.ibm.com/soapoverhttp/", "MDX_Services");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://MDX_Services.MDXService.isd.ibm.com/soapoverhttp/", "MDX_ServicesSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("MDX_ServicesSOAP".equals(portName)) {
            setMDX_ServicesSOAPEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
