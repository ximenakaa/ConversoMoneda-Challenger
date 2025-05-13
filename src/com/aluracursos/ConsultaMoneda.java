package com.aluracursos;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public  class ConsultaMoneda {
    private static final String Api_key ="Api_key";
    private static final String Base_URL ="https://v6.exchangerate-api.com/v6/" + Api_key + "/latest/";
    private final HttpClient httpClient;

    public ConsultaMoneda() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public JsonObject getRates(String baseCurrency) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(Base_URL + baseCurrency))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP error: " + response.statusCode());
        }

        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

        // Aquí accedemos solo a la parte útil
        return json.getAsJsonObject("conversion_rates");
    }
}

