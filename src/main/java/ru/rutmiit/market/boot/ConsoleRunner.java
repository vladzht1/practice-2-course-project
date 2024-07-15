package ru.rutmiit.market.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.rutmiit.market.domain.Market;
import ru.rutmiit.market.domain.ProductCategory;
import ru.rutmiit.market.dtos.MarketDto;
import ru.rutmiit.market.dtos.ProductDto;
import ru.rutmiit.market.dtos.api.AddMarketDto;
import ru.rutmiit.market.dtos.api.AddMarketProductDto;
import ru.rutmiit.market.dtos.api.AddProductDto;
import ru.rutmiit.market.dtos.api.AddUserDto;
import ru.rutmiit.market.services.MarketProductService;
import ru.rutmiit.market.services.MarketService;
import ru.rutmiit.market.services.ProductService;
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

    @Override
    public void run(String... args) throws Exception {
        AddUserDto userDto = new AddUserDto("Vladislav", "Alexandrovich", "Zheltov", "email@gmail.com");
        userService.add(userDto);

        AddProductDto productDto1 = new AddProductDto("Beauty Product Title", "Beauty Product Description", ProductCategory.BEAUTY);
        AddProductDto productDto2 = new AddProductDto("PC Title", "PC Product Description", ProductCategory.PC);
        ProductDto product1 = productService.add(productDto1);
        ProductDto product2 = productService.add(productDto2);

        AddMarketDto marketDto1 = new AddMarketDto("DNS", "DNS description");
        MarketDto market = marketService.add(marketDto1);

        AddMarketProductDto marketProductDto1 = new AddMarketProductDto(market.getId(), product1.getId(), 20.3, 45);
        AddMarketProductDto marketProductDto2 = new AddMarketProductDto(market.getId(), product2.getId(), 4, 450);

        marketProductService.add(marketProductDto1);
        marketProductService.add(marketProductDto2);
    }
}
