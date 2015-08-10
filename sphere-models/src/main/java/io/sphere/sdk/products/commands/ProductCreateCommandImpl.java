package io.sphere.sdk.products.commands;

import io.sphere.sdk.commands.ReferenceExpandeableCreateCommandBuilder;
import io.sphere.sdk.commands.ReferenceExpandeableCreateCommandImpl;
import io.sphere.sdk.products.Product;
import io.sphere.sdk.products.ProductDraft;
import io.sphere.sdk.products.expansion.ProductExpansionModel;

/**
 Creates a product.

 <p>A {@link Product} must belong to a {@link io.sphere.sdk.producttypes.ProductType},
 so you need to {@link io.sphere.sdk.producttypes.commands.ProductTypeCreateCommand create a product type} first if not already done.</p>

 Example usage executing the command:
 {@include.example io.sphere.sdk.products.CreateProductExamples#createWithClient()}

 Create a {@link ProductDraft} instance:
 {@include.example io.sphere.sdk.suppliers.SimpleCottonTShirtProductDraftSupplier}
 */
final class ProductCreateCommandImpl extends ReferenceExpandeableCreateCommandImpl<Product, ProductCreateCommand, ProductDraft, ProductExpansionModel<Product>> implements ProductCreateCommand {
    ProductCreateCommandImpl(final ReferenceExpandeableCreateCommandBuilder<Product, ProductCreateCommand, ProductDraft, ProductExpansionModel<Product>> builder) {
        super(builder);
    }

    ProductCreateCommandImpl(final ProductDraft body) {
        super(body, ProductEndpoint.ENDPOINT, ProductExpansionModel.of(), ProductCreateCommandImpl::new);
    }
}
