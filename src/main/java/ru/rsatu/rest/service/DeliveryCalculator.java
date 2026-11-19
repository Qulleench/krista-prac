package ru.rsatu.rest.service;

import ru.rsatu.rest.dto.DeliveryRequest;
import ru.rsatu.rest.dto.DeliveryResponse;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeliveryCalculator {

    public DeliveryResponse calculate(DeliveryRequest req) {
        // Объёмный вес
        double volumeWeight = (req.getLength() * req.getWidth() * req.getHeight()) / 5000.0;

        // Расчётный вес
        double chargeableWeight = Math.max(req.getWeight(), volumeWeight);

        // Базовая стоимость
        double cost = chargeableWeight * 150.0;

        // Коэффициент скорости
        boolean express = req.getIsExpress();
        if (express) {
            cost *= 1.5;
        }

        // Страховка
        double insurance = req.getInsuranceValue() * 0.01;
        double finalCost = cost + insurance;

        // Срок доставки
        int deliveryDays = express ? 2 : 5;

        return new DeliveryResponse(volumeWeight, chargeableWeight, deliveryDays, finalCost);
    }
}