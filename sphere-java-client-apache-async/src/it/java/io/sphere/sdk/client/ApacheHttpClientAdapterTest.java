package io.sphere.sdk.client;

import io.sphere.sdk.http.ApacheHttpClientAdapter;
import io.sphere.sdk.http.HttpClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public class ApacheHttpClientAdapterTest extends HttpClientAdapterTest {
    @Override
    protected HttpClient createClient() {
        return ApacheHttpClientAdapter.of(HttpAsyncClients.createDefault());
    }
}
