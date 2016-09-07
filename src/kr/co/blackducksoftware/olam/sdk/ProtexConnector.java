/**
 * Copyright(C) 2014-2016 Junsu Lee. All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *   
 * @author junsulee (jslee@opensource.or.kr)
 */
package kr.co.blackducksoftware.olam.sdk;

import java.io.FileInputStream;
import java.util.Properties;

import com.blackducksoftware.sdk.fault.SdkFault;
import com.blackducksoftware.sdk.protex.client.util.ProtexServerProxy;
import com.blackducksoftware.sdk.protex.license.LicenseApi;
import com.blackducksoftware.sdk.protex.project.ProjectApi;
import com.blackducksoftware.sdk.protex.project.bom.BomApi;


public class ProtexConnector {
	private static BomApi bomApi = null;
	private static ProjectApi projectApi = null;
	private static LicenseApi licenseApi = null;
	private static Long connectionTimeout = 30 * 60 * 1000L;	// 30 min

	private String serveruri = null;
	private String username = null;
	private String password = null;
	private ProtexServerProxy myProtexServer = null;
	
	public ProtexConnector() throws Exception{		
		Properties props = new Properties();
		FileInputStream fis = new FileInputStream("config.properties");
		props.load(fis);
		
		serveruri = props.getProperty("protex.server");
		username = props.getProperty("protex.username");
		password = props.getProperty("protex.password");
		
		myProtexServer = new ProtexServerProxy(serveruri, username, password, connectionTimeout);
	}
	
	public BomApi getBomApi() {
		if(myProtexServer == null) return null;
		if(bomApi == null) bomApi = myProtexServer.getBomApi();
		return bomApi;
	}
	
	public ProjectApi getProjectApi() {
		if(myProtexServer == null) return null;
		if(projectApi == null) projectApi = myProtexServer.getProjectApi();
		return projectApi;
	}
	
	public LicenseApi getLicenseApi() {
		if(myProtexServer == null) return null;
		if(licenseApi == null) licenseApi = myProtexServer.getLicenseApi();
		return licenseApi;
	}
	
	public boolean checkSDKConnection(){
    	myProtexServer =  new ProtexServerProxy(serveruri, username, password, connectionTimeout);
 	    //connection test
 	    try {
 	    	myProtexServer.getUserApi().getCurrentUserHasServerFileAccess();
		} catch (SdkFault e) {
			return false;
		}
    	return true;    	
    }

	public void discardAllUserData() {
		myProtexServer = null;
	    bomApi = null;
	    projectApi = null;
	    licenseApi = null;
	}
	
	public ProtexServerProxy getProtexSDKConnection() {
		return myProtexServer;
	}
}
