package ru.rsatu.rest;

import ru.rsatu.rest.dto.DeliveryRequest;
import ru.rsatu.rest.dto.DeliveryResponse;
import ru.rsatu.rest.service.DeliveryCalculator;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/delivery")
public class DeliveryResource {

    @Inject
    private DeliveryCalculator calculator;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DeliveryResponse calculate(DeliveryRequest request) {
        return calculator.calculate(request);
    }
}