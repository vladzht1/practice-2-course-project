package ru.rutmiit.market.boot;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.rutmiit.market.domain.MarketProduct;
import ru.rutmiit.market.domain.ProductCategory;
import ru.rutmiit.market.dtos.MarketDto;
import ru.rutmiit.market.dtos.MarketProductDto;
import ru.rutmiit.market.dtos.OrderDto;
import ru.rutmiit.market.dtos.ProductDto;
import ru.rutmiit.market.dtos.UserDto;
import ru.rutmiit.market.dtos.api.AddMarketDto;
import ru.rutmiit.market.dtos.api.AddMarketProductDto;
import ru.rutmiit.market.dtos.api.AddOrderDto;
import ru.rutmiit.market.dtos.api.AddOrderPositionDto;
import ru.rutmiit.market.dtos.api.AddProductDto;
import ru.rutmiit.market.dtos.api.AddUserDto;
import ru.rutmiit.market.services.DiscountRecommendationsService;
import ru.rutmiit.market.services.MarketProductService;
import ru.rutmiit.market.services.MarketService;
import ru.rutmiit.market.services.OrderService;
import ru.rutmiit.market.services.ProductService;
import ru.rutmiit.market.services.UserRecommendationsService;
import ru.rutmiit.market.services.UserService;

@Component
public class ConsoleRunner implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MarketService marketService;

    @Autowired
    private MarketProductService marketProductService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRecommendationsService userRecommendationsService;

    @Autowired
    private DiscountRecommendationsService discountRecommendationsService;

    @Override
    public void run(String... args) throws Exception {
        AddUserDto userDto = new AddUserDto("Vladislav", "Alexandrovich", "Zheltov", "email@gmail.com");
        UserDto user = userService.add(userDto);

        AddProductDto productDto1 = new AddProductDto("Beauty Product Title", "Beauty Product Description", ProductCategory.BEAUTY);
        AddProductDto productDto3 = new AddProductDto("Another Beauty Product Title", "Another Beauty Product Description", ProductCategory.BEAUTY);
        AddProductDto productDto2 = new AddProductDto("PC Title", "PC Product Description", ProductCategory.PC);
        ProductDto product1 = productService.add(productDto1);
        ProductDto product2 = productService.add(productDto2);
        ProductDto product3 = productService.add(productDto3);

        AddMarketDto marketDto1 = new AddMarketDto("DNS", "DNS description");
        MarketDto market = marketService.add(marketDto1);

        AddMarketDto marketDto2 = new AddMarketDto("Эльдорадо", "Описание Эльдорадо");
        MarketDto market2 = marketService.add(marketDto2);

        AddMarketProductDto marketProductDto1 = new AddMarketProductDto(market.getId(), product1.getId(), 20.3, 45);
        AddMarketProductDto marketProductDto2 = new AddMarketProductDto(market.getId(), product2.getId(), 4, 450);
        AddMarketProductDto marketProductDto3 = new AddMarketProductDto(market2.getId(), product3.getId(), 44, 1200);

        MarketProductDto marketProduct1 = marketProductService.add(marketProductDto1);
        MarketProductDto marketProduct2 = marketProductService.add(marketProductDto2);
        MarketProductDto marketProduct3 = marketProductService.add(marketProductDto3);

        List<AddOrderPositionDto> orderPositions = new ArrayList<>();
        orderPositions.add(new AddOrderPositionDto(marketProduct1.getId(), 4));
        orderPositions.add(new AddOrderPositionDto(marketProduct2.getId(), 5));

        AddOrderDto addOrderDto1 = new AddOrderDto(user.getId(), orderPositions);
        List<OrderDto> orders = orderService.add(addOrderDto1);

        List<MarketProductDto> result = userRecommendationsService.findRecommendedProductsForUser(user.getId());

        for (var entry : result) {
            System.out.println(entry.getProductTitle() + " " + entry.getPrice() + " " + entry.getMarketId());
        }

        var result2 = productService.getBestSoldProductsByMarketId(market.getId());

        for (var entry : result2) {
            System.out.println(entry.getProductTitle() + " " + entry.getIncome() + " " + entry.getAmountSold());
        }

        var result3 = discountRecommendationsService.getRecommendationsForDiscountsAndSalesByMarketId(market.getId());

        for (var entry : result3) {
            System.out.println(entry.getProductTitle() + " " + entry.getCategory() + " " + entry.getRecommendedDiscount());
        }
    }
}
