package net.astercrono.pcsetup.dto.mapper;

public interface EntityDtoMapper<T, J> {
	J mapDtoFromEntity(T entity);

	T mapEntityFromDto(J dto);
}
