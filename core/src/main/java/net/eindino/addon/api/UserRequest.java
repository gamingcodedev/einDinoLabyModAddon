package net.eindino.addon.api;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class UserRequest {

  private boolean successful;
  private UserResponse userResponse;
  private String error;

  public CompletableFuture<Void> sendRequestAsync(String username) {
    Gson gson = new Gson();
    CompletableFuture<Void> future = new CompletableFuture<>();

    try {

      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI("https://api.eindino.dev/user?name=" + username))
          .header("Content-Type", "application/json")
          .build();

      HttpClient client = HttpClient.newHttpClient();
      client.sendAsync(request, BodyHandlers.ofString())
          .thenAccept(response -> {
            if (response.body().isEmpty()) {
              successful = false;
            } else {
              this.userResponse = gson.fromJson(response.body(), UserResponse.class);
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

  public UserResponse getUserResponse() {
    return userResponse;
  }

  public String getError() {
    return error;
  }

}
