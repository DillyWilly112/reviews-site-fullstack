package org.wecancodeit;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TypeRepository extends CrudRepository<Type, Long> {

	Collection<Type> findByStrainsContains(Strain strain);

	Collection<Type> findByStrainsId(Long id);
}
