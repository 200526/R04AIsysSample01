package jp.jc21.t.yoshizawa.WEB01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Sentiment {

	public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
		Language1 message = getLanguage("Stepover Toehold With Facelock");
		if (message != null) {
			System.out.println("Positive:"+message.documents[0].confidenceScores.positive);
			System.out.println("Neutral:"+message.documents[0].confidenceScores.neutral);
			System.out.println("Negative:"+message.documents[0].confidenceScores.negative);
			
		}
	}

	static Language1 getLanguage(String s) throws IOException, URISyntaxException, InterruptedException {
		Gson gson = new Gson();

		String url = "https://r04jk3b05-text.cognitiveservices.azure.com/" + "text/analytics/v3.0/sentiment";
		Map<String, String> map = new HashMap<>();
		map.put("Ocp-Apim-Subscription-Key", "de9e671926a14dd69600d14a626e390b");

		Docs1 doc = new Docs1();
		doc.id = "1";
		doc.text = s;

		Source1 src = new Source1();
		src.documents = new Docs1[1];
		src.documents[0] = doc;

		String jsonData = new Gson().toJson(src);

		InetSocketAddress proxy = new InetSocketAddress("172.17.0.2", 80);

		JsonReader reader = WebApiConnector.postJsonReader(url, proxy, map, jsonData);
		Language1 message = null;
		if (reader != null) {
			message = gson.fromJson(reader, Language1.class);
			reader.close();
		}
		return message;
	}

}

class Language1 {
	Documents1[] documents;
	String[] errors;
	String modelVersion;
}

class Documents1 {
	ConfidenceScores confidenceScores;
	
}

class ConfidenceScores {
	String negative;
	String neutral;
	String positive;
	
}

class Source1 {
	Docs1[] documents;
}

class Docs1 {
	String id;
	String text;
}
