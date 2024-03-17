package net.eindino.addon.api.staff;

import com.google.gson.Gson;
import net.eindino.addon.user.StaffStatsCache;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class StaffStatsRequest {

  private boolean successful;
  private String error;

  public CompletableFuture<Void> sendRequestAsync(int player_id) {
    Gson gson = new Gson();
    CompletableFuture<Void> future = new CompletableFuture<>();

    try {

      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI("https://api.eindino.dev/staffStats?id=" + player_id))
          .header("Content-Type", "application/json")
          .build();

      HttpClient client = HttpClient.newHttpClient();
      client.sendAsync(request, BodyHandlers.ofString())
          .thenAccept(response -> {
            if (response.body().isEmpty()) {
              successful = false;
            } else {

              StatType[] statType = gson.fromJson(response.body(), StatType[].class);

              for (StatType type : statType) {
                StaffStatsCache.apply(type.getStats_key(), type.getValue());
              }

              successful = true;
            }

            future.complete(null);
          }).exceptionally((e) -> {
            future.completeExceptionally(e);
            error = e.getMessage();
            return null;
          });
    } catch (Exception e) {
      e.printStackTrace();
      future.completeExceptionally(e);
      error = e.getMessage();
    }

    return future;
  }

  public boolean isSuccessful() {
    return successful;
  }

  public String getError() {
    return error;
  }

}
