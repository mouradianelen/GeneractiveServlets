package am.aca.generactive.generactiveservlets.gen.service;

import am.aca.generactive.generactiveservlets.gen.Repository.springrepo.GroupJPARepository;
import am.aca.generactive.generactiveservlets.gen.model.Group;

import am.aca.generactive.generactiveservlets.gen.service.dto.groupdto.GroupDTO;

import am.aca.generactive.generactiveservlets.gen.service.mapper.GroupDTOMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupJPARepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupJPARepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public GroupDTO create(GroupDTO group) {
        Group entity = GroupDTOMapper.mapToEntity(group);

        groupRepository.save(entity);

        return GroupDTOMapper.mapToDTO(entity).orElse(null);
    }

    @Override
    public Group update(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public boolean delete(Long id) {
        if (!groupRepository.existsById(id)) {
            return false;
        }

        groupRepository.deleteById(id);

        return true;
    }

    @Override
    public Optional<GroupDTO> getGroup(Long id) {
        Optional<Group> group = groupRepository.findById(id);

        return GroupDTOMapper.mapToDTO(group.orElse(null));
    }
    public List<? extends GroupDTO> getAll() {
        return GroupDTOMapper.mapToDTOs(groupRepository.findAll());
    }

}
