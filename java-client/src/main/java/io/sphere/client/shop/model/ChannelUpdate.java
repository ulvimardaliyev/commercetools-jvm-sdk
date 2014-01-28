package io.sphere.client.shop.model;

import io.sphere.internal.command.ChannelCommands;
import io.sphere.internal.command.Update;

import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class ChannelUpdate extends Update<ChannelCommands.ChannelUpdateAction> {

    public ChannelUpdate changeKey(final String key) {
        add(new ChannelCommands.ChangeKey(key));
        return this;
    }

    public ChannelUpdate addRoles(final Set<ChannelRoles> roles) {
        add(new ChannelCommands.AddRoles(roles));
        return this;
    }

    public ChannelUpdate addRole(ChannelRoles role) {
        return addRoles(newHashSet(role));
    }
}
