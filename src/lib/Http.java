/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lib;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author sandeepnarwal
 */
public class Http {
	
	public void get(URL url) throws IOException {
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		InputStream in;
		int http_status;
		try {
			in = conn.getInputStream();
			http_status = conn.getResponseCode();
			if (http_status / 100 != 2) {
				//something strange happened
			}
		} catch (IOException e) {
			System.out.println(" http error" );

		} finally {
			conn.disconnect();
		}
	}
	

}
