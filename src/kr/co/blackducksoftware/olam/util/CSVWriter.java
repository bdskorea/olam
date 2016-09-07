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
package kr.co.blackducksoftware.olam.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

public class CSVWriter {
	public CSVWriter () {
		
	}
	
	public void appendColumn(List<String> attributes) throws IOException {
		CsvListReader reader = new CsvListReader(new FileReader("res/input.csv"), CsvPreference.STANDARD_PREFERENCE);
		CsvListWriter writer = new CsvListWriter(new FileWriter("output.csv"), CsvPreference.STANDARD_PREFERENCE);
				
		List<String> columns = null;
		for (int i = 0; i < 22; i++){
			columns = reader.read();
			columns.add(attributes.get(i));
			writer.write(columns);
		}
		reader.close();
		writer.close();
	}	
}
