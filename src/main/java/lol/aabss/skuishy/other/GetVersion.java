package lol.aabss.skuishy.other;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetVersion {
    public static String version(){
        return Bukkit.getBukkitVersion().split("-")[0];
    }

    public static int datapackVersion(){
        String v = version();
        return switch (v){
            case "1.13", "1.13.1", "1.13.2", "1.13.3", "1.14", "1.14.1", "1.14.2", "1.14.3", "1.14.4" -> 4;
            case "1.15", "1.15.1", "1.15.2", "1.16", "1.16.1" -> 5;
            case "1.16.2", "1.16.3", "1.16.4", "1.16.5" -> 6;
            case "1.17", "1.17.1" -> 7;
            case "1.18", "1.18.1" -> 8;
            case "1.18.2" -> 9;
            case "1.19", "1.19.1", "1.19.2", "1.19.3" -> 10;
            case "1.19.4" -> 12;
            case "1.20", "1.20.1" -> 15;
            case "1.20.2" -> 18;
            case "1.20.3", "1.20.4" -> 26;
            default -> 0;
        };
    }

    public static String latestVersion() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.modrinth.com/v2/project/skuishy/version"))
                .build();
        try {
            String body = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).get().body();
            JsonArray releasesArray = JsonParser.parseString(body).getAsJsonArray();
            JsonObject latestRelease = releasesArray.get(0).getAsJsonObject();
            return latestRelease.get("version_number").getAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String latestSkriptVersion() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/repos/SkriptLang/Skript/releases/latest"))
                .build();
        try {
            String body = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString()).get().body();
            return JsonParser.parseString(body).getAsJsonObject().get("tag_name").getAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
