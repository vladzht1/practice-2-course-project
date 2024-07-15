package ru.rutmiit.market.dtos.api;

public class AddMarketDto {
    private String name;
    private String description;

    public AddMarketDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    protected AddMarketDto() {}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String updatedName) {
        name = updatedName;
    }

    public void setDescription(String updatedDescription) {
        description = updatedDescription;
    }
}
