package com.vinsguru.webfluxpatterns.sec04.service;

import com.vinsguru.webfluxpatterns.sec04.client.ProductClient;
import com.vinsguru.webfluxpatterns.sec04.dto.OrchestrationRequestContext;
import com.vinsguru.webfluxpatterns.sec04.dto.Product;
import com.vinsguru.webfluxpatterns.sec04.dto.Status;
import com.vinsguru.webfluxpatterns.sec04.util.OrchestrationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderFulfillmentService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private PaymentOrchestrator paymentOrchestrator;

    @Autowired
    private InventoryOrchestrator inventoryOrchestrator;

    @Autowired
    private ShippingOrchestrator shippingOrchestrator;

    public Mono<OrchestrationRequestContext> placeOrder(OrchestrationRequestContext ctx){
        return this.getProduct(ctx)
                .doOnNext(OrchestrationUtil::buildPaymentRequest)
                .flatMap(this.paymentOrchestrator::create)
                .doOnNext(OrchestrationUtil::buildInventoryRequest)
                .flatMap(this.inventoryOrchestrator::create)
                .doOnNext(OrchestrationUtil::buildShippingRequest)
                .flatMap(this.shippingOrchestrator::create)
                .doOnNext(c -> c.setStatus(Status.SUCCESS))
                .doOnError(ex -> ctx.setStatus(Status.FAILED))
                .onErrorReturn(ctx);
    }

    private Mono<OrchestrationRequestContext> getProduct(OrchestrationRequestContext ctx){
        return this.productClient.getProduct(ctx.getOrderRequest().getProductId())
                                 .map(Product::getPrice)
                                 .doOnNext(ctx::setProductPrice)
                                 .map(i -> ctx);
    }

}
