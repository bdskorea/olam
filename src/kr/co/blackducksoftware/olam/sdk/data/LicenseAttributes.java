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

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

import kr.co.blackducksoftware.olam.sdk.ProtexConnector;
import kr.co.blackducksoftware.olam.util.CSVWriter;

import com.blackducksoftware.sdk.fault.SdkFault;
import com.blackducksoftware.sdk.protex.license.License;
import com.blackducksoftware.sdk.protex.license.LicenseExtensionLevel;
import com.blackducksoftware.sdk.protex.license.LicenseOriginType;
import com.blackducksoftware.sdk.protex.license.PermittedOrRequired;
import com.blackducksoftware.sdk.protex.license.RestrictionType;
import com.blackducksoftware.sdk.protex.license.RightToDistributeBinaryForMaximumUsage;

public class LicenseAttributes {

	public static List<String> getLicenseAttributes(String licensename) throws Exception {
		
		ProtexConnector prtxconn = new ProtexConnector();
		
		List<String> attribute = new ArrayList<String>();

		try {
			List<LicenseOriginType> typeFilter = new ArrayList<LicenseOriginType>();
			typeFilter.add(LicenseOriginType.STANDARD);
			
			License license = prtxconn.getLicenseApi().getLicenseByName(licensename);

			Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
			String copyString = "";

			attribute.clear();
			attribute.add(0, license.getName());

			// Attribute 1:
			if (license.getAttributes()
					.getRightToDistributeBinaryForMaximumUsage()
					.equals(RightToDistributeBinaryForMaximumUsage.ANY)) {
				attribute.add(1, "X");
			} else {
				attribute.add(1, "O");
			}

			// Attribute 2:
			if (license.getAttributes().isCarriesDistributionObligations()
					.equals(true)) {
				attribute.add(2, "X");
			} else {
				attribute.add(2, "O");
			}

			// Attribute 3:
			if (license.getAttributes().getSourceCodeDistribution()
					.equals(PermittedOrRequired.REQUIRED)) {
				attribute.add(3, "O");
			} else if (license.getAttributes().getSourceCodeDistribution()
					.equals(PermittedOrRequired.PERMITTED)) {
				attribute.add(3, "△");
				//attribute.add(3, "\u25b3");
			} else {
				attribute.add(3, "X");
			}

			// Attribute 4:
			if (license.getAttributes().getGrantRecipientRightToCopy()
					.equals(PermittedOrRequired.REQUIRED)) {
				attribute.add(4, "O");
			} else if (license.getAttributes().getGrantRecipientRightToCopy()
					.equals(PermittedOrRequired.PERMITTED)) {
				attribute.add(4, "△");
			} else {
				attribute.add(4, "X");
			}

			// Attribute 5:
			if (license.getAttributes().getGrantRecipientRightToModify()
					.equals(PermittedOrRequired.REQUIRED)) {
				attribute.add(5, "O");
			} else if (license.getAttributes().getGrantRecipientRightToModify()
					.equals(PermittedOrRequired.PERMITTED)) {
				attribute.add(5, "△");
			} else {
				attribute.add(5, "X");
			}

			// Attribute 6:
			if (license.getAttributes()
					.getGrantRecipientRightToReverseEngineer()
					.equals(PermittedOrRequired.REQUIRED)) {
				attribute.add(6, "O");
			} else if (license.getAttributes()
					.getGrantRecipientRightToReverseEngineer()
					.equals(PermittedOrRequired.PERMITTED)) {
				attribute.add(6, "△");
			} else {
				attribute.add(6, "X");
			}

			// Attribute 7:
			if (license
					.getAttributes()
					.getDiscriminatoryRestrictions()
					.equals(RestrictionType.HAS_NO_RESTRICTIONS_AND_CAN_NOT_ADD_ANY)) {
				attribute.add(7, "O");
			} else if (license.getAttributes().getDiscriminatoryRestrictions()
					.equals(RestrictionType.HAS_RESTRICTIONS)) {
				attribute.add(7, "△");
			} else {
				attribute.add(7, "X");
			}

			// Attribute 8:
			if (license.getAttributes().getChargingFees()
					.equals(PermittedOrRequired.NOT_PERMITTED)) {
				attribute.add(8, "O");
			} else if (license.getAttributes().getChargingFees()
					.equals(PermittedOrRequired.PERMITTED)) {
				attribute.add(8, "△");
			} else {
				attribute.add(8, "X");
			}

			// Attribute 9:
			if (license.getAttributes().isPatentRetaliation().equals(true)) {
				attribute.add(9, "O");
			} else {
				attribute.add(9, "X");
			}

			// Attribute 10:
			if (license.getAttributes().isExpressPatentLicense().equals(true)) {
				attribute.add(10, "O");
			} else {
				attribute.add(10, "△");
			}

			// Attribute 11:
			if (license.getAttributes().isAntiDrmProvision().equals(true)) {
				attribute.add(11, "O");
			} else {
				attribute.add(11, "△");
			}

			// Attribute 12:
			if (license.getAttributes().isNotice().equals(true)) {
				attribute.add(12, "O");
			} else {
				attribute.add(12, "X");
			}

			// Attribute 13:
			if (license.getAttributes().isChangeNoticeRequired().equals(true)) {
				attribute.add(13, "O");
			} else {
				attribute.add(13, "X");
			}

			// Attribute 14:
			if (license.getAttributes().isLicenseBackRequired().equals(true)) {
				attribute.add(14, "O");
			} else {
				attribute.add(14, "X");
			}

			// Attribute 15:
			if (license.getAttributes().isWarrantyDisclaimerRequired()
					.equals(true)) {
				attribute.add(15, "O");
			} else {
				attribute.add(15, "X");
			}

			// Attribute 16:
			if (license.getAttributes().isLimitationOfLiabilityRequired()
					.equals(true)) {
				attribute.add(16, "O");
			} else {
				attribute.add(16, "X");
			}

			// Attribute 17:
			if (license.getAttributes().isIndemnificationRequired()
					.equals(true)) {
				attribute.add(17, "O");
			} else {
				attribute.add(17, "X");
			}

			// Attribute 18:
			if (license.getAttributes().isIncludeLicense().equals(true)) {
				attribute.add(18, "O");
			} else {
				attribute.add(18, "X");
			}

			// Attribute 19:
			if (license.getAttributes().isPromotionRestriction().equals(true)) {
				attribute.add(19, "O");
			} else {
				attribute.add(19, "X");
			}

			// Attribute 20:
			if (license.getAttributes().isShareAlikeReciprocity().equals(true)) {
				attribute.add(20, "O");
			} else {
				attribute.add(20, "X");
			}

			// Attribute 21:
			if (license.getAttributes()
					.getIntegrationLevelForLicenseApplication()
					.equals(LicenseExtensionLevel.FILE_PER_MPL)) {
				attribute.add(21, "파일\n(per MPL)");
			} else if (license.getAttributes()
					.getIntegrationLevelForLicenseApplication()
					.equals(LicenseExtensionLevel.MODULE_PER_EPL_CPL)) {
				attribute.add(21, "모듈\n(per EPL/CPL)");
			} else if (license.getAttributes()
					.getIntegrationLevelForLicenseApplication()
					.equals(LicenseExtensionLevel.DYNAMIC_LIBRARY_PER_LGPL)) {
				attribute.add(21, "동적 라이브러리\n(per LGPL)");
			} else if (license.getAttributes()
					.getIntegrationLevelForLicenseApplication()
					.equals(LicenseExtensionLevel.WORK_BASED_ON_PER_GPL)) {
				attribute.add(21, "코드 기반의 산출물\n(per GPL)");
			} else if (license
					.getAttributes()
					.getIntegrationLevelForLicenseApplication()
					.equals(LicenseExtensionLevel.ACCOMPANYING_SOFTWARE_USING_PER_SLEEPY_CAT)) {
				attribute.add(21, "\"수반 소프트웨어\n(per SleepyCat)\"");
			} else {
				attribute.add(21, "X");
			}
			
			for (String attributes : attribute) {
				System.out.println(attributes);
				copyString += attributes + "\n";
			}
			
			CSVWriter csv = new CSVWriter();
			csv.appendColumn(attribute);
			
			if (copyString != null) {
				StringSelection contents = new StringSelection(copyString);
				clip.setContents(contents, null);
			}

		} catch (SdkFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		

		return attribute;
	}
}
