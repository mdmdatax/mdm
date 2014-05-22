/**
 * MDX_Services_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.isd.MDXService.MDX_Services.soapoverhttp;

public interface MDX_Services_PortType extends java.rmi.Remote {
    public com.ibm.isd.MDXService.MDX_Services.MDX_ServicesOutAddress[] MDX_ADDRESS_STAND(com.ibm.isd.MDXService.MDX_Services.MDX_ServicesInAddress[] arg1) throws java.rmi.RemoteException;
    public com.ibm.isd.MDXService.MDX_Services.MDX_ServicesOutPhone[] MDX_PHONE_STAND(com.ibm.isd.MDXService.MDX_Services.MDX_ServicesInPhone[] arg1) throws java.rmi.RemoteException;
    public com.ibm.isd.MDXService.MDX_Services.MDX_ServicesOutOrgName[] MDX_ORGNAME(com.ibm.isd.MDXService.MDX_Services.MDX_ServicesInOrgName[] arg1) throws java.rmi.RemoteException;
    public com.ibm.isd.MDXService.MDX_Services.MDX_ServicesOutPersonName[] MDX_PERSONNAME(com.ibm.isd.MDXService.MDX_Services.MDX_ServicesInPersonName[] arg1) throws java.rmi.RemoteException;
}
