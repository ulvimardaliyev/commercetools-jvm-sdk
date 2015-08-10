package io.sphere.sdk.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import io.sphere.sdk.client.HttpRequestIntent;
import io.sphere.sdk.client.JsonEndpoint;
import io.sphere.sdk.expansion.ExpansionDslUtil;
import io.sphere.sdk.expansion.ExpansionPath;
import io.sphere.sdk.expansion.MetaModelExpansionDslExpansionModelRead;
import io.sphere.sdk.http.HttpMethod;
import io.sphere.sdk.http.UrlQueryBuilder;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static io.sphere.sdk.json.SphereJsonUtils.toJsonString;
import static io.sphere.sdk.utils.ListUtils.listOf;
import static java.util.Objects.requireNonNull;

/**
 * Internal base class to implement commands which create an entity in SPHERE.IO.
 *
 * @param <T> the type of the result of the command, most likely the updated entity without expanded references
 * @param <C> class which will serialized as JSON command body, most likely a template
 * @param <E> type of the expansion model
 */
public abstract class ReferenceExpandeableCreateCommandImpl<T, C, D, E> extends CommandImpl<T> implements CreateCommand<T>, MetaModelExpansionDslExpansionModelRead<T, C, E> {

    final D body;
    final E expansionModel;
    final JsonEndpoint<T> endpoint;
    final List<ExpansionPath<T>> expansionPaths;
    final Function<ReferenceExpandeableCreateCommandBuilder<T, C, D, E>, C> creationFunction;

    protected ReferenceExpandeableCreateCommandImpl(final D draft, final JsonEndpoint<T> endpoint, final List<ExpansionPath<T>> expansionPaths, final E expansionModel, final Function<ReferenceExpandeableCreateCommandBuilder<T, C, D, E>, C> creationFunction) {
        this.creationFunction = requireNonNull(creationFunction);
        this.expansionPaths = requireNonNull(expansionPaths);
        this.expansionModel = requireNonNull(expansionModel);
        this.body = requireNonNull(draft);
        this.endpoint = requireNonNull(endpoint);
    }

    protected ReferenceExpandeableCreateCommandImpl(final ReferenceExpandeableCreateCommandBuilder<T, C, D, E> builder) {
        this(builder.body, builder.endpoint, builder.expansionPaths, builder.expansionModel, builder.creationFunction);
    }

    protected ReferenceExpandeableCreateCommandImpl(final D draft, final JsonEndpoint<T> endpoint, final E expansionModel, final Function<ReferenceExpandeableCreateCommandBuilder<T, C, D, E>, C> creationFunction) {
        this(draft, endpoint, Collections.emptyList(), expansionModel, creationFunction);
    }

    @Override
    public HttpRequestIntent httpRequestIntent() {
        final String additions = queryParametersToString(true);
        return HttpRequestIntent.of(httpMethod(), endpoint.endpoint() + (additions.length() > 1 ? additions : ""), httpBody());
    }

    private String queryParametersToString(final boolean urlEncoded) {
        final UrlQueryBuilder builder = UrlQueryBuilder.of();
        expansionPaths().forEach(path -> builder.add("expand", path.toSphereExpand(), urlEncoded));
        return builder.toStringWithOptionalQuestionMark();
    }

    protected HttpMethod httpMethod() {
        return HttpMethod.POST;
    }

    protected String httpBody() {
        return toJsonString(body);
    }

    @Override
    protected TypeReference<T> typeReference() {
        return endpoint.typeReference();
    }

    @Override
    public List<ExpansionPath<T>> expansionPaths() {
        return expansionPaths;
    }

    @Override
    public final C withExpansionPaths(final List<ExpansionPath<T>> expansionPaths) {
        return copyBuilder().expansionPaths(expansionPaths).build();
    }

    @Override
    public C withExpansionPaths(final ExpansionPath<T> expansionPath) {
        return ExpansionDslUtil.withExpansionPaths(this, expansionPath);
    }

    @Override
    public C withExpansionPaths(final Function<E, ExpansionPath<T>> m) {
        return ExpansionDslUtil.withExpansionPaths(this, m);
    }

    @Override
    public C plusExpansionPaths(final List<ExpansionPath<T>> expansionPaths) {
        return withExpansionPaths(listOf(expansionPaths(), expansionPaths));
    }

    @Override
    public C plusExpansionPaths(final ExpansionPath<T> expansionPath) {
        return ExpansionDslUtil.plusExpansionPaths(this, expansionPath);
    }

    @Override
    public C plusExpansionPaths(final Function<E, ExpansionPath<T>> m) {
        return ExpansionDslUtil.plusExpansionPaths(this, m);
    }

    @Override
    public E expansionModel() {
        return expansionModel;
    }

    protected ReferenceExpandeableCreateCommandBuilder<T, C, D, E> copyBuilder() {
        return new ReferenceExpandeableCreateCommandBuilder<>(this);
    }
}
