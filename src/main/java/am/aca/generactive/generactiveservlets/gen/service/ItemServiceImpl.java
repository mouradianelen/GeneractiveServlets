package am.aca.generactive.generactiveservlets.gen.service;

import am.aca.generactive.generactiveservlets.gen.Repository.springrepo.ItemJPARepository;
import am.aca.generactive.generactiveservlets.gen.model.Item;
import am.aca.generactive.generactiveservlets.gen.service.dto.itemdto.ItemDTO;
import am.aca.generactive.generactiveservlets.gen.service.mapper.ItemDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemJPARepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemJPARepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public ItemDTO create(ItemDTO item) {
        Item entity = ItemDTOMapper.mapToEntity(item);

        itemRepository.save(entity);

        return ItemDTOMapper.mapToDTO(entity).orElse(null);
    }

    @Override
    public Item update(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public boolean delete(Long id) {
        if (!itemRepository.existsById(id)) {
            return false;
        }

        itemRepository.deleteById(id);

        return true;
    }

    @Override
    public Optional<ItemDTO> getItem(Long id) {
        Optional<Item> item = itemRepository.findById(id);

        return ItemDTOMapper.mapToDTO(item.orElse(null));
    }

    @Override
    public List<? extends ItemDTO> getAll() {
        return ItemDTOMapper.mapToDTOs(itemRepository.findAll());
    }

    @Override
    public List<? extends ItemDTO> find(String name) {
        Specification<Item> specification = Specification.where(null);

        return ItemDTOMapper
                .mapToDTOs(itemRepository
                        .find(name, 300));
    }
}
