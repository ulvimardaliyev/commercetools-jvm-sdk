package io.sphere.sdk.producttypes.commands;

import com.fasterxml.jackson.core.type.TypeReference;
import io.sphere.sdk.models.Versioned;
import io.sphere.sdk.producttypes.ProductType;
import io.sphere.sdk.commands.DeleteByIdCommandImpl;

public final class ProductTypeDeleteByIdCommand extends DeleteByIdCommandImpl<ProductType> {
    public ProductTypeDeleteByIdCommand(final Versioned<ProductType> versioned) {
        super(versioned);
    }

    @Override
    protected String baseEndpointWithoutId() {
        return "/product-types";
    }

    @Override
    protected TypeReference<ProductType> typeReference() {
        return ProductType.typeReference();
    }
}
