/**
 * Copyright(C) 2014-2016 Junsu Lee
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
package kr.co.blackducksoftware.olam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import kr.co.blackducksoftware.olam.sdk.data.BomLicenses;
import kr.co.blackducksoftware.olam.sdk.data.LicenseAttributes;
import kr.co.blackducksoftware.olam.util.FileUtility;
import kr.co.blackducksoftware.olam.util.Version;

public class Main {
	
	BufferedReader br;
	int selectedMenu;
	
	public Main() throws IOException {
		showMenu();		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			int selectedMenu;
			selectedMenu = Integer.parseInt(br.readLine());
			switch(selectedMenu) {
				case 1:
					System.out.print("License name: ");
					String lname = br.readLine();
					getLicenseAttributes(lname);
					break;
				case 2:
					System.out.print("Project name: ");
					String pname = br.readLine();
					getProjectBomLicensesAttributes(pname);
					break;
					
				case 3:
					terminateApplication();
					break;
					
				default:
					terminateApplication();
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void getLicenseAttributes(String licensename) {
		try {
			LicenseAttributes.getLicenseAttributes(licensename);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void getProjectBomLicensesAttributes(String projectname) {
		try {
			FileUtility.deleteFile("res/input.csv");
			FileUtility.copyFile("res/template.csv", "res/input.csv");
			ArrayList<String> licenses = new ArrayList<String>();
			licenses = BomLicenses.getBomLicenses(projectname);
			
			for (String license : licenses) {
				getLicenseAttributes(license);
				FileUtility.deleteFile("res/input.csv");
				FileUtility.copyFile("output.csv", "res/input.csv");
			}
			
			System.out.println("output.csv successfully generated.");
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
		
	}
	
	public void showMenu() {
		System.out.println("========================================");
		System.out.println("OLAM v" + Version.getApplicationVersion());
		System.out.println("jslee@opensource.or.kr");
		System.out.println("----------------------------------------");
		System.out.println("Get attributes of...");
		System.out.println("1. a single license");
		System.out.println("2. whole licenses of a project");
		System.out.println("3. Exit");
		System.out.println("========================================");
		System.out.print("Select menu:");
	}
	
	public void terminateApplication() {
		System.exit(1);
	}
	
	public static void main(String args[]) throws IOException {
		new Main();		
	}
}
