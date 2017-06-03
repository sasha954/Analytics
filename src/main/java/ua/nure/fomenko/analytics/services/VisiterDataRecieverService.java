package ua.nure.fomenko.analytics.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import ua.nure.fomenko.analytics.constants.Path;
import ua.nure.fomenko.analytics.db.entity.Visiters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by fomenko on 30.05.2017.
 */
public class VisiterDataRecieverService {



    private Visiters jsonToJavaObjectConverter(String json) throws IOException{
        Visiters visiter = new ObjectMapper().readValue(json, Visiters.class);
        return visiter;
    }

    public Visiters getVisiterDataFromAPI(String ip) throws IOException{

        HttpClient client = new DefaultHttpClient();
        String url = Path.TWO_IP_URL + ip;
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String jsonObj = reader.readLine();
        Visiters visiter = jsonToJavaObjectConverter(jsonObj);

        return visiter;
    }



}
