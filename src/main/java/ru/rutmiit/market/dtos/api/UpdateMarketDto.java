package ru.rutmiit.market.dtos.api;

public class UpdateMarketDto {
    private int id;
    private String name;
    private String description;

    public UpdateMarketDto(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    protected UpdateMarketDto() {}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int updatedId) {
        id = updatedId;
    }

    public void setName(String updatedName) {
        name = updatedName;
    }

    public void setDescription(String updatedDescription) {
        description = updatedDescription;
    }
}
