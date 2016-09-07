import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.soap.SOAPFaultException;

import com.blackducksoftware.sdk.fault.SdkFault;
import com.blackducksoftware.sdk.protex.client.util.ProtexServerProxy;
import com.blackducksoftware.sdk.protex.license.LicenseApi;
import com.blackducksoftware.sdk.protex.license.LicenseInfo;
import com.blackducksoftware.sdk.protex.license.LicenseInfoColumn;
import com.blackducksoftware.sdk.protex.license.LicenseInfoPageFilter;
import com.blackducksoftware.sdk.protex.license.LicenseOriginType;
import com.blackducksoftware.sdk.protex.project.ProjectApi;
import com.blackducksoftware.sdk.protex.report.ReportApi;
import com.blackducksoftware.sdk.protex.util.PageFilterFactory;


public class ListLicense {
	public static void main(String[] args) throws Exception {
		String serveruri = args[0];
		String username = args[1];
		String password = args[2];
		Long connectionTime = 4800 * 1000L;

		ProjectApi projectApi = null;
		ReportApi reportApi = null;
		
		LicenseApi licenseApi = null;

		try {
			ProtexServerProxy myProtexServer = new ProtexServerProxy(serveruri,
					username, password, connectionTime);
			licenseApi = myProtexServer.getLicenseApi();
			
			
			try {
				
				LicenseInfoPageFilter pageFilter = PageFilterFactory.getAllRows(LicenseInfoColumn.LICENSE_NAME);
				List<LicenseOriginType> typeFilter = new ArrayList<LicenseOriginType>();
		        typeFilter.add(LicenseOriginType.STANDARD);
				
				List<LicenseInfo> licenses = licenseApi.getLicenses(typeFilter, pageFilter);
				
				int i = 0;
				for (LicenseInfo license : licenses) {
					System.out.println(license.getName() + " / " + license.getLicenseId() + "- " + i);
					i++;
				}
			} catch (SdkFault e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SOAPFaultException e) {
			System.out.println(e.getMessage());
		}
	}
}
