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
package kr.co.blackducksoftware.olam.sdk.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import kr.co.blackducksoftware.olam.sdk.ProtexConnector;

import com.blackducksoftware.sdk.fault.SdkFault;
import com.blackducksoftware.sdk.protex.project.bom.BomComponent;

public class BomLicenses {
	public static ArrayList<String> getBomLicenses(String projectname) throws Exception {
		
		ProtexConnector prtxconn = new ProtexConnector();

		ArrayList<String> bomLicenseNames = new ArrayList<String>();
		List<BomComponent> bomComponents = new ArrayList<BomComponent>();

		try {
			String project = prtxconn.getProjectApi().getProjectByName(projectname).getProjectId();			
			bomComponents.addAll(prtxconn.getBomApi().getBomComponents(project));
		} catch (SdkFault e) {
    		System.err.println(e.getMessage());
    		System.exit(-1);
		}
        
        System.out.println("Aggregating project licenses...");
        
        ArrayList<String> sortedLicenseNames = null;
        for (BomComponent bomComponent : bomComponents) {
        	bomLicenseNames.add(bomComponent.getLicenseInfo().getName());
        	
        	HashSet<String> hset = new HashSet<String>(bomLicenseNames);
        	sortedLicenseNames = new ArrayList<String>(hset);
        	Collections.sort(sortedLicenseNames);
        }
		
    	System.out.println(sortedLicenseNames);
        
		return sortedLicenseNames;
	}
}
