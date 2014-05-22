package com.ibm.isd.MDXService.MDX_Services.soapoverhttp;

public class MDX_ServicesProxy implements com.ibm.isd.MDXService.MDX_Services.soapoverhttp.MDX_Services_PortType {
  private String _endpoint = null;
  private com.ibm.isd.MDXService.MDX_Services.soapoverhttp.MDX_Services_PortType mDX_Services_PortType = null;
  
  public MDX_ServicesProxy() {
    _initMDX_ServicesProxy();
  }
  
  public MDX_ServicesProxy(String endpoint) {
    _endpoint = endpoint;
    _initMDX_ServicesProxy();
  }
  
  private void _initMDX_ServicesProxy() {
    try {
      mDX_Services_PortType = (new com.ibm.isd.MDXService.MDX_Services.soapoverhttp.MDX_Services_ServiceLocator()).getMDX_ServicesSOAP();
      if (mDX_Services_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)mDX_Services_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)mDX_Services_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (mDX_Services_PortType != null)
      ((javax.xml.rpc.Stub)mDX_Services_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.ibm.isd.MDXService.MDX_Services.soapoverhttp.MDX_Services_PortType getMDX_Services_PortType() {
    if (mDX_Services_PortType == null)
      _initMDX_ServicesProxy();
    return mDX_Services_PortType;
  }
  
  public com.ibm.isd.MDXService.MDX_Services.MDX_ServicesOutAddress[] MDX_ADDRESS_STAND(com.ibm.isd.MDXService.MDX_Services.MDX_ServicesInAddress[] arg1) throws java.rmi.RemoteException{
    if (mDX_Services_PortType == null)
      _initMDX_ServicesProxy();
    return mDX_Services_PortType.MDX_ADDRESS_STAND(arg1);
  }
  
  public com.ibm.isd.MDXService.MDX_Services.MDX_ServicesOutPhone[] MDX_PHONE_STAND(com.ibm.isd.MDXService.MDX_Services.MDX_ServicesInPhone[] arg1) throws java.rmi.RemoteException{
    if (mDX_Services_PortType == null)
      _initMDX_ServicesProxy();
    return mDX_Services_PortType.MDX_PHONE_STAND(arg1);
  }
  
  public com.ibm.isd.MDXService.MDX_Services.MDX_ServicesOutOrgName[] MDX_ORGNAME(com.ibm.isd.MDXService.MDX_Services.MDX_ServicesInOrgName[] arg1) throws java.rmi.RemoteException{
    if (mDX_Services_PortType == null)
      _initMDX_ServicesProxy();
    return mDX_Services_PortType.MDX_ORGNAME(arg1);
  }
  
  public com.ibm.isd.MDXService.MDX_Services.MDX_ServicesOutPersonName[] MDX_PERSONNAME(com.ibm.isd.MDXService.MDX_Services.MDX_ServicesInPersonName[] arg1) throws java.rmi.RemoteException{
    if (mDX_Services_PortType == null)
      _initMDX_ServicesProxy();
    return mDX_Services_PortType.MDX_PERSONNAME(arg1);
  }
  
  
}