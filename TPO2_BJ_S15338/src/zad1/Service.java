package zad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Service {

	String kraj;
	String temperatur;
	String walutaKurs;
	String wzgledemPLN;
	String walutaKraju;

	public Service(String string) {
		this.kraj = string.toUpperCase();
	}

	public String getWeather(String string) {

		try {
			URL url = new URL("https://samples.openweathermap.org/data/2.5/weather?q=" + string
					+ "&appid=42bea8fd6d0057ad1eba1bc50ff8293d");
			JSONObject jsonObject = new JSONObject(this.mojUrl(url));
			JSONObject temp = (JSONObject) jsonObject.get("main");
			Double a = ((Double) temp.get("temp")) - 273.15;
			temperatur = String.format("%.2f", a);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public Double getRateFor(String waluta) {
		String json;
		Map<String, String> countries = new HashMap<>();
		for (String iso : Locale.getISOCountries()) {

			Locale l = new Locale("", iso);
			countries.put(l.getDisplayCountry(Locale.ENGLISH).toUpperCase(), iso);
		}
		Currency cc = Currency.getInstance(new Locale("", countries.get(kraj)));
		this.walutaKraju = cc.getCurrencyCode();
		try {
			JSONObject jsonObject = new JSONObject(this.mojUrl(new URL("https://api.exchangeratesapi.io/latest?base=" + cc.getCurrencyCode())));
			JSONObject temp = (JSONObject) jsonObject.get("rates");
			walutaKurs = Double.toString((((Double) temp.get(waluta))));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public Double getNBPRate() {
		
		try {
			JSONObject jsonObject = new JSONObject(this.mojUrl((new URL("http://api.nbp.pl/api/exchangerates/rates/A/"+this.walutaKraju+"/?format=json"))));
			System.out.println(this.mojUrl((new URL("http://api.nbp.pl/api/exchangerates/rates/A/"+this.walutaKraju+"/?format=json"))));
			JSONObject a = (JSONObject)((JSONArray) jsonObject.get("rates")).get(0);
			wzgledemPLN = a.get("mid").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		return null;
	}

	public String mojUrl(URL url) {
		HttpURLConnection con;
		try {
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}

			in.close();
			con.disconnect();
			return content.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public String getTemperatur() {
		return temperatur;
	}

	public void setTemperatur(String temperatur) {
		this.temperatur = temperatur;
	}

	public String getWalutaKurs() {
		return walutaKurs;
	}

	public void setWalutaKurs(String walutaKurs) {
		this.walutaKurs = walutaKurs;
	}

	public String getWzgledemPLN() {
		return wzgledemPLN;
	}

	public void setWzgledemPLN(String wzgledemPLN) {
		this.wzgledemPLN = wzgledemPLN;
	}

	public String getWalutaKraju() {
		return walutaKraju;
	}

	public void setWalutaKraju(String walutaKraju) {
		this.walutaKraju = walutaKraju;
	}



	

}
