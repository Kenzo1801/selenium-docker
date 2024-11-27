package tests.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import tests.vendors.model.VendorTestData;

public class Demo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		InputStream stream = ResourceLoader.getResource("dummy.txt");
		
		VendorTestData testData = JsonUtil.getTestData("test-data/vendor-portal/john.json", VendorTestData.class);
		System.out.println(testData.monthlyEarning());
//	 	System.out.println(IOUtils.toString(stream, StandardCharsets.UTF_8));		
	}

}
