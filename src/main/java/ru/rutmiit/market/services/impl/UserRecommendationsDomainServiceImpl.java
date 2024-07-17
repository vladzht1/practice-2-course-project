package ru.rutmiit.market.services.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.rutmiit.market.domain.Order;
import ru.rutmiit.market.domain.OrderPosition;
import ru.rutmiit.market.domain.Product;
import ru.rutmiit.market.domain.ProductCategory;
import ru.rutmiit.market.dtos.MarketProductDto;
import ru.rutmiit.market.repositories.MarketProductRepository;
import ru.rutmiit.market.repositories.OrderRepository;
import ru.rutmiit.market.repositories.ProductRepository;
import ru.rutmiit.market.services.UserRecommendationsService;

@Service
public class UserRecommendationsDomainServiceImpl implements UserRecommendationsService {
    private static final int MINIMAL_CATEGORIES_TO_SELECT = 5;
    private static final int MAXIMAL_RECOMMENDATIONS_AMOUNT = 15;

    @Autowired
    private MarketProductRepository marketProductRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MarketProductDto> findRecommendedProductsForUser(int userId) {
        List<Order> userOrders = orderRepository.findAllOrdersByUserId(userId);

        List<ProductCategory> mostPopularProductCategories = filterMostPopularUserCategories(
            userOrders
                .stream()
                .map(order -> order.getOrderPositions())
                .reduce(new ArrayList<>(), (List<OrderPosition> acc, List<OrderPosition> value) -> {
                    value.forEach(position -> {
                        acc.add(position);
                    });
                    return acc;
                })
        );

        List<Product> products = productRepository.findAmountByCategories(
            mostPopularProductCategories.subList(0, Math.min(MINIMAL_CATEGORIES_TO_SELECT, mostPopularProductCategories.size()))
        );

        var result = marketProductRepository.findByProducts(products);

        return result.subList(0, Math.min(MAXIMAL_RECOMMENDATIONS_AMOUNT, result.size()))
            .stream()
            .map(entry -> modelMapper.map(entry, MarketProductDto.class))
            .toList();
    }

    private List<ProductCategory> filterMostPopularUserCategories(List<OrderPosition> orderPositions) {
        Map<ProductCategory, Integer> categories = new HashMap<>();

        for (OrderPosition position : orderPositions) {
            ProductCategory category = position.getProduct().getProductCategory();

            if (!categories.containsKey(category)) {
                categories.put(category, 0);
            }

            // The larger the quantity is, the more likely it is that the user likes this category
            categories.put(category, categories.get(category) + position.getQuantity());
        }

        return categories.entrySet()
            .stream()
            .sorted((prev, next) -> prev.getValue() - next.getValue())
            .map(entry -> entry.getKey())
            .toList();
    }
}
