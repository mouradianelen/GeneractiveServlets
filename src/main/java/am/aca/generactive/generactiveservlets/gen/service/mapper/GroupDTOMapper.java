package am.aca.generactive.generactiveservlets.gen.service.mapper;

import am.aca.generactive.generactiveservlets.gen.model.Group;
import am.aca.generactive.generactiveservlets.gen.service.dto.groupdto.GroupDTO;
import org.modelmapper.ModelMapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class GroupDTOMapper {
    public static List<GroupDTO> mapToDTOs(Collection<? extends Group> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return new ArrayList<>();
        }

        List<GroupDTO> rv = new ArrayList<>();

        for (Group entity : entities) {
            rv.add(mapToDTO(entity).orElse(null));
        }

        return rv;
    }

    public static Optional<GroupDTO> mapToDTO(Group entity) {
        if (entity == null) {
            return Optional.empty();
        }

        GroupDTO dto;

        ModelMapper modelMapper = new ModelMapper();
        dto = modelMapper.map(entity, GroupDTO.class);


        return Optional.of(dto);
    }

    public static Group mapToEntity(GroupDTO dto) {
        if (dto == null) {
            return null;
        }

        Group entity = new Group();
        //entity.setId(dto.getId());
        entity.setName(dto.getName());
        ;
        entity.setItems(entity.getItems());

        return entity;
    }
}
