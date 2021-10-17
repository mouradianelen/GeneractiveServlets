package am.aca.generactive.generactiveservlets.gen.service;

import am.aca.generactive.generactiveservlets.gen.model.Group;
import am.aca.generactive.generactiveservlets.gen.model.Item;
import am.aca.generactive.generactiveservlets.gen.service.dto.groupdto.GroupDTO;
import am.aca.generactive.generactiveservlets.gen.service.dto.itemdto.ItemDTO;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    GroupDTO create(GroupDTO group);
    Group update(Group group);
    boolean delete(Long id);
    List<? extends GroupDTO> getAll();
    Optional<GroupDTO> getGroup(Long id);
}
