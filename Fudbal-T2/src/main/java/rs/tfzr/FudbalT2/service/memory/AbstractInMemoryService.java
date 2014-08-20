package rs.tfzr.FudbalT2.service.memory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import rs.tfzr.FudbalT2.model.AbstractBaseEntity;
import rs.tfzr.FudbalT2.service.CrudService;

/**
 * Abstract implementation of an in memory based service CRUD.
 * 
 * @author d.gajic
 * 
 * @param <T>
 */
public abstract class AbstractInMemoryService<T extends AbstractBaseEntity>
		implements CrudService<T> {

	protected static final String NON_EXISTING_ENTITY = "Error: Tried to remove non-existing entity with id=%d.";

	/**
	 * Map for in-memory storage
	 */
	private final Map<Long, T> map = new LinkedHashMap<>();

	/**
	 * ID generation sequence
	 */
	protected final AtomicLong sequence = new AtomicLong(1);

	/**
	 * @see rs.code9.taster.service.CrudService#findOne(java.lang.Long)
	 */
	@Override
	public T findOne(Long id) {
		return map.get(id);
	}

	/**
	 * @see rs.code9.taster.service.CrudService#findAll()
	 */
	@Override
	public List<T> findAll() {
		return new ArrayList<>(map.values());
	}

	/**
	 * @see rs.code9.taster.service.CrudService#save(rs.code9.taster.model.AbstractBaseEntity)
	 */
	@Override
	public T save(T t) {
		if (t.getId() == null) {
			t.setId(sequence.getAndIncrement());
		}
		map.put(t.getId(), t);
		return t;
	}

	/**
	 * @see rs.code9.taster.service.CrudService#remove(java.lang.Long)
	 */
	@Override
	public void remove(Long id) throws IllegalArgumentException {
		T t = map.remove(id);
		if (t == null) {
			throw new IllegalArgumentException(String.format(
					NON_EXISTING_ENTITY, id));
		}
	}
}
