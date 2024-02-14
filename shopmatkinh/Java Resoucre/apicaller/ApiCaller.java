package apicaller;

	import java.io.BufferedReader;
	import java.io.InputStreamReader;
	import java.net.HttpURLConnection;
	import java.net.URL;

	public class ApiCaller {
	    private static final String API_URL = "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json";

	    public static String getJsonFromApi() throws Exception {
	        URL url = new URL(API_URL);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");

	        int responseCode = connection.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String inputLine;
	            StringBuilder response = new StringBuilder();

	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            in.close();

	            return response.toString();
	        } else {
	            throw new Exception("Error getting JSON from API. Response code: " + responseCode);
	        }
	    }
	}

