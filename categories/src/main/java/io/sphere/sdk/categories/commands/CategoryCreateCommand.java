package io.sphere.sdk.categories.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import io.sphere.sdk.categories.Category;
import io.sphere.sdk.categories.NewCategory;
import io.sphere.sdk.commands.Command;
import io.sphere.sdk.commands.CreateCommandImpl;
import net.jcip.annotations.Immutable;

@Immutable
public final class CategoryCreateCommand extends CreateCommandImpl<Category, NewCategory> implements Command<Category> {

    public CategoryCreateCommand(final NewCategory newCategory) {
        super(newCategory);
    }

    @Override
    protected String httpEndpoint() {
        return "/categories";
    }

    @Override
    public TypeReference<Category> typeReference() {
        return Category.typeReference();
    }
}
