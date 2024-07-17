package ru.rutmiit.market.services.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rutmiit.market.domain.Market;
import ru.rutmiit.market.domain.MarketProduct;
import ru.rutmiit.market.domain.Order;
import ru.rutmiit.market.domain.OrderPosition;
import ru.rutmiit.market.domain.Product;
import ru.rutmiit.market.domain.User;
import ru.rutmiit.market.dtos.OrderDto;
import ru.rutmiit.market.dtos.api.AddOrderDto;
import ru.rutmiit.market.dtos.api.UpdateOrderDto;
import ru.rutmiit.market.dtos.internal.MarketProductOrderDto;
import ru.rutmiit.market.exceptions.InvalidQuantityException;
import ru.rutmiit.market.exceptions.MarketNotFoundException;
import ru.rutmiit.market.exceptions.MarketProductNotFoundException;
import ru.rutmiit.market.exceptions.OperationFailedException;
import ru.rutmiit.market.exceptions.OrderNotFoundException;
import ru.rutmiit.market.exceptions.ProductNotFoundException;
import ru.rutmiit.market.exceptions.UserNotFoundException;
import ru.rutmiit.market.mappers.OrderMapper;
import ru.rutmiit.market.repositories.MarketProductRepository;
import ru.rutmiit.market.repositories.MarketRepository;
import ru.rutmiit.market.repositories.OrderRepository;
import ru.rutmiit.market.repositories.ProductRepository;
import ru.rutmiit.market.repositories.UserRepository;
import ru.rutmiit.market.services.OrderService;

@Service
public class OrderDomainServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private MarketProductRepository marketProductRepository;

    @Autowired
    private ProductRepository productRepository;

    private OrderMapper orderMapper;

    public OrderDomainServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDto> findAll() {
        return orderMapper.mapOrdersToDto(orderRepository.findAll());
    }

    @Override
    public Optional<OrderDto> findById(int id) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(orderMapper.mapOrderToDto(order.get()));
    }

    @Override
    public List<OrderDto> add(AddOrderDto orderDto) {
        User user = userRepository.findById(orderDto.getUserId()).orElseThrow(() -> new UserNotFoundException(orderDto.getUserId()));

        if (!user.getIsActive()) {
            throw new UserNotFoundException("User with id " + user.getId() + " was deleted");
        }

        Map<Integer, List<MarketProductOrderDto>> products = new HashMap<>();

        for (var productDto : orderDto.getProducts()) {
            MarketProduct marketProduct = marketProductRepository.findById(productDto.getMarketProductId()).orElseThrow(
                () -> new MarketProductNotFoundException(productDto.getMarketProductId())
            );

            MarketProductOrderDto marketProductOrderDto = new MarketProductOrderDto(marketProduct, productDto.getQuantity());

            if (!products.containsKey(marketProduct.getMarket().getId())) {
                products.put(marketProduct.getMarket().getId(), new ArrayList<>());
            }

            products.get(marketProduct.getMarket().getId()).add(marketProductOrderDto);
        }

        List<Order> orders = new ArrayList<>();

        for (var entries : products.entrySet()) {
            int marketId = entries.getKey();
            List<MarketProductOrderDto> marketProductOrderPositions = entries.getValue();

            if (marketProductOrderPositions.size() == 0) {
                continue;
            }

            Market market = marketRepository.findById(marketId).orElseThrow(() -> new MarketNotFoundException(marketId));

            Order order = new Order(user, market);
            order = orderRepository.save(order);

            for (MarketProductOrderDto marketOrderPositionDto : marketProductOrderPositions) {
                Product product = productRepository.findById(marketOrderPositionDto.getMarketProduct().getProduct().getId())
                    .orElseThrow(() -> new ProductNotFoundException());

                int requiredQuantity = marketOrderPositionDto.getQuantity();
                int availableQuantity = marketOrderPositionDto.getMarketProduct().getQuantity();

                if (requiredQuantity > availableQuantity) {
                    throw new InvalidQuantityException("Required quantity " + requiredQuantity + " is greater than " + availableQuantity + " for product " + product.getTitle());
                }

                OrderPosition position = new OrderPosition(
                    order,
                    product,
                    marketOrderPositionDto.getMarketProduct().getPrice(),
                    marketOrderPositionDto.getQuantity()
                );

                marketOrderPositionDto.getMarketProduct().setQuantity(availableQuantity - requiredQuantity);
                MarketProduct marketProduct = marketProductRepository.update(marketOrderPositionDto.getMarketProduct()).orElseThrow(() -> new OperationFailedException("Failed to update market product"));

                if (marketProduct.getQuantity() <= 0) {
                    marketProductRepository.deleteById(marketProduct.getId());
                }

                position = orderRepository.saveOrderPosition(position);
                order.addOrderPosition(position);
            }

            orders.add(orderRepository.update(order).orElseThrow(() -> new OperationFailedException("Error while creating a new order occurred")));
        }

        return orderMapper.mapOrdersToDto(orders);
    }

    @Override
    public Optional<OrderDto> update(UpdateOrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getId()).orElseThrow(() -> new OrderNotFoundException(orderDto.getId()));

        if (orderDto.getStatus() == null) {
            return Optional.empty();
        }

        order.setStatus(orderDto.getStatus());
        return Optional.of(orderMapper.mapOrderToDto(orderRepository.update(order).orElseThrow(() -> new OperationFailedException("Could not update order"))));
    }
}
