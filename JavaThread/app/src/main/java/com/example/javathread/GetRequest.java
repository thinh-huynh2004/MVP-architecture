package com.example.javathread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest {
    public static void main(String[] args) {

        try {

            URL url = new URL("http://www.example.com");

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");

            urlConnection.setConnectTimeout(15000);

            urlConnection.setReadTimeout(15000);

            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");



            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String inputLine;

                StringBuilder response = new StringBuilder();



                while ((inputLine = in.readLine()) != null) {

                    response.append(inputLine);

                }

                in.close();



                System.out.println(response.toString());

            } else {

                System.out.println("GET request failed");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
