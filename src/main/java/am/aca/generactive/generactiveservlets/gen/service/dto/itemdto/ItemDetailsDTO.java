package am.aca.generactive.generactiveservlets.gen.service.dto.itemdto;
import am.aca.generactive.generactiveservlets.gen.model.ItemDetails;
import org.modelmapper.ModelMapper;

import java.util.Objects;

public class ItemDetailsDTO {

    private Long id;
    private String description;

    public static ItemDetailsDTO mapToDTO(ItemDetails entity) {
        if (entity == null) {
            return null;
        }

        ModelMapper modelMapper = new ModelMapper();
        ItemDetailsDTO itemDetailsDTO = modelMapper.map(entity, ItemDetailsDTO.class);
        return itemDetailsDTO;
    }

    public static ItemDetails mapToEntity(ItemDetailsDTO itemDetails) {
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDetailsDTO that = (ItemDetailsDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ItemDetailsDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}