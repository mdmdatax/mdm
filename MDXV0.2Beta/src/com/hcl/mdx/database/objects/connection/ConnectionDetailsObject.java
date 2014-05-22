package com.hcl.mdx.database.objects.connection;

public class ConnectionDetailsObject  {

	String serverName;
	int portNumber;
	String serviceName;
	String userName;
	String password;
	
	public ConnectionDetailsObject(
			String serverName, 
			int portNumber, 
			String serviceName, 
			String userName, 
			String password) {
		this.serverName = serverName;
		this.serviceName = serviceName;
		this.portNumber = portNumber;
		this.userName = userName;
		this.password = password;
	}
	
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName)  {
		this.serverName = serverName;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber)  {
		this.portNumber = portNumber;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName)  {
		this.serviceName = serviceName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName)  {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password)  {
		this.password = password;
	}
}
