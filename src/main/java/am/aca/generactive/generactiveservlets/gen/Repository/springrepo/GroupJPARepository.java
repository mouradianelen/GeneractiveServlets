package am.aca.generactive.generactiveservlets.gen.Repository.springrepo;

import am.aca.generactive.generactiveservlets.gen.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupJPARepository extends JpaRepository<Group,Long> {
}
