package am.aca.generactive.generactiveservlets.gen.service.mapper;

import am.aca.generactive.generactiveservlets.gen.model.Item;
import am.aca.generactive.generactiveservlets.gen.service.dto.itemdto.ItemDTO;
import am.aca.generactive.generactiveservlets.gen.service.dto.itemdto.ItemDetailsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class ItemDTOMapper {

    public static List<ItemDTO> mapToDTOs(Collection<? extends Item> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return new ArrayList<>();
        }

        List<ItemDTO> rv = new ArrayList<>();

        for (Item entity : entities) {
            rv.add(mapToDTO(entity).orElse(null));
        }

        return rv;
    }

    public static Optional<ItemDTO> mapToDTO(Item entity) {
        if (entity == null) {
            return Optional.empty();
        }

        ItemDTO dto=new ItemDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBasePrice(entity.getBasePrice());
        dto.setGroupName(entity.getGroup() == null ? null : entity.getGroup().getName());
        dto.setItemDetails(ItemDetailsDTO.mapToDTO(entity.getItemDetail()));
//        ModelMapper modelMapper = new ModelMapper();
//         dto = modelMapper.map(entity, ItemDTO.class);


        return Optional.of(dto);
    }

    public static Item mapToEntity(ItemDTO dto) {
        if (dto == null) {
            return null;
        }

        Item entity = new Item();
        //entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setBasePrice(dto.getBasePrice());
        entity.setItemDetail(ItemDetailsDTO.mapToEntity(dto.getItemDetails()));

        return entity;
    }
}