package io.sphere.sdk.channels;

import io.sphere.sdk.channels.commands.ChannelCreateCommand;
import io.sphere.sdk.channels.commands.ChannelDeleteByIdCommand;
import io.sphere.sdk.channels.queries.ChannelFetchByKey;
import io.sphere.sdk.channels.queries.ChannelQuery;
import io.sphere.sdk.client.TestClient;
import io.sphere.sdk.client.ClientRequest;
import io.sphere.sdk.models.LocalizedStrings;
import io.sphere.sdk.queries.PagedQueryResult;
import io.sphere.sdk.queries.QueryIntegrationTest;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static io.sphere.sdk.test.OptionalAssert.assertThat;
import static java.util.Locale.ENGLISH;

public class ChannelIntegrationTest extends QueryIntegrationTest<Channel> {
    @Override
    protected ClientRequest<Channel> deleteCommand(final Channel item) {
        return ChannelDeleteByIdCommand.of(item);
    }

    @Override
    protected ClientRequest<Channel> newCreateCommandForName(final String name) {
        return ChannelCreateCommand.of(ChannelDraft.of(name));
    }

    @Override
    protected String extractName(final Channel instance) {
        return instance.getKey();
    }

    @Override
    protected ClientRequest<PagedQueryResult<Channel>> queryRequestForQueryAll() {
        return ChannelQuery.of();
    }

    @Override
    protected ClientRequest<PagedQueryResult<Channel>> queryObjectForName(final String name) {
        return ChannelQuery.of().byKey(name);
    }

    @Override
    protected ClientRequest<PagedQueryResult<Channel>> queryObjectForNames(final List<String> names) {
        return ChannelQuery.of().withPredicate(ChannelQuery.model().key().isOneOf(names));
    }

    @Test
    public void FetchChannelByKey() throws Exception {
        withChannel(client(), ChannelDraftBuilder.of("foo"), channel -> {
                    final Optional<Channel> channelOption = execute(ChannelFetchByKey.of(channel.getKey()));
                    assertThat(channelOption).isPresentAs(channel);
                }
        );
    }

    private void withChannel(final TestClient client, final Supplier<ChannelDraft> creator, final Consumer<Channel> user) {
        final ChannelDraft channelDraft = creator.get();
        cleanUpByName(channelDraft.getKey());
        final Channel channel = client.execute(ChannelCreateCommand.of(channelDraft));
        user.accept(channel);
        cleanUpByName(channelDraft.getKey());
    }

    @Test
    public void deleteChannelById() throws Exception {
        final Channel channel = createChannel();
        final Channel deletedChannel = execute(ChannelDeleteByIdCommand.of(channel));
    }

    private Channel createChannel() {
        final ChannelDraft channelDraft = ChannelDraft.of("my-store")
                .withDescription(LocalizedStrings.of(ENGLISH, "description"));
        return execute(ChannelCreateCommand.of(channelDraft));
    }
}
