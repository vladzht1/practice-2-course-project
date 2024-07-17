package ru.rutmiit.market.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.rutmiit.market.dtos.OrderDto;
import ru.rutmiit.market.dtos.api.AddOrderDto;
import ru.rutmiit.market.dtos.api.UpdateOrderDto;
import ru.rutmiit.market.exceptions.OrderNotFoundException;
import ru.rutmiit.market.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
    @Autowired
    private OrderService orderDomainService;

    @GetMapping()
    public Iterable<OrderDto> getAllOrder() {
        return orderDomainService.findAll();
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable() int id) throws OrderNotFoundException {
        return orderDomainService.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @PostMapping()
    public List<OrderDto> create(@RequestBody AddOrderDto addOrderDto) {
        return orderDomainService.add(addOrderDto);
    }

    @PatchMapping()
    public OrderDto update(@RequestBody UpdateOrderDto updateOrderDto) throws OrderNotFoundException {
        return orderDomainService.update(updateOrderDto).orElseThrow(() -> new OrderNotFoundException(updateOrderDto.getId()));
    }
}
