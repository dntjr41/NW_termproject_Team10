package myPackage2;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject; 
import org.json.simple.parser.JSONParser;
import  org.json.simple.parser.ParseException;

public class socialDate {
		String tem;
		String hum;
		
		public socialDate() {
			
			String serviceKey = "MKxy5Lr%2BzXhe%2FObhNr%2B0h5RGweJdg6FJa9IxO3ZPAE%2FWh0hdeiWjOLQzAUjw8hqttCssWh7qUgHzGDLExWz98g%3D%3D";
			String dataType = "JSON"; 
			String nx="63";
			String ny="124"; 
			Date date=new Date();
            SimpleDateFormat f1 = new SimpleDateFormat("yyyyMMdd");
            String baseDate = f1.format(date);
            System.out.println(baseDate);
            String baseTime="0000";
 	       	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtNcst"); /*URL*/
 	       String result = null;
 	       	try {
	 	       	urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
	 	       	urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
	 	       	urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
	 	       	urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode(dataType, "UTF-8")); 
	 	       	urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); 
	 	       	urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); 
	 	       	urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); 
	 	       	urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); 
	 	       	URL url = new URL(urlBuilder.toString());
	 	       	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	 	       	conn.setRequestMethod("GET");
	 	       	conn.setRequestProperty("Content-type", "application/json");
	 	       	System.out.println("Response code: " + conn.getResponseCode());
	 	       	BufferedReader rd;
	 	       	if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300){
	 	       		rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	 	       	}
	 	       	else{
	 	       		rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	 	       	}
	 	       	StringBuilder sb = new StringBuilder();
	 	       	String line;
	 	       	while ((line = rd.readLine()) != null){
	 	       		sb.append(line);
	 	       	}
	 	       	rd.close();
	 	       	conn.disconnect();
	 	       	result=sb.toString();
	 	       	System.out.println(result);
 	       	}catch(Exception e) {
 	       		e.printStackTrace();
 	       	}
 	       	
 	       	try {

 	        JSONParser parser = new JSONParser(); 
 	        JSONObject obj = (JSONObject) parser.parse(result);
 	        JSONObject parse_response = (JSONObject) obj.get("response");
 	        JSONObject parse_body = (JSONObject) parse_response.get("body");
 	        JSONObject parse_items = (JSONObject) parse_body.get("items");
 	        JSONArray parse_item = (JSONArray) parse_items.get("item"); 
 	           
 	        JSONObject weather; 
 	        JSONObject weather2;

 	        String day="";
            String time="";
            weather = (JSONObject)parse_item.get(3); 
            weather2=(JSONObject) parse_item.get(1);
            if(!day.equals(weather.get("baseDate").toString())) {
            	day=weather.get("baseDate").toString();
            }
            if(!time.equals(weather.get("baseTime").toString())){
            	time=weather.get("baseTime").toString();
            }
                     	 //System.out.println("temperature: "+weather.get("obsrValue"));
                     	 //System.out.println("humidity:"+weather2.get("obsrValue").toString());
                     	 String weather3[] = new String[2];
                     	 this.tem =  weather.get("obsrValue").toString() + " "+ weather.get("category").toString();
                     	 this.hum = weather2.get("obsrValue").toString() + " "+ weather2.get("category").toString();
 	       	}catch(Exception e) {
 	       		e.printStackTrace();
 	       	}
                     	
        }
			
		/*
	    public static void main(String[] args) throws IOException, ParseException{	      
	    	       socialDate s = new socialDate();
	    	       System.out.println(s.tem);
	    	       System.out.println(s.hum);
	    	       
	    }
	    
	    */
}
	    

	             
