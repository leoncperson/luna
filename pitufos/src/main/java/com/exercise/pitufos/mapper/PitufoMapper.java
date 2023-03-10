package com.exercise.pitufos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.exercise.pitufos.model.PitufoDTO;
import com.exercise.pitufos.persistence.entity.PitufoEntity;

@Mapper
public interface PitufoMapper {
	PitufoMapper MAPPER = Mappers.getMapper( PitufoMapper.class );

	@Mapping(target = "nombre", source = "nombre")
	@Mapping(target = "descripcion", source = "descripcion")
	PitufoEntity toEntity(PitufoDTO dto);
	
	@InheritInverseConfiguration
	PitufoDTO toDTO(PitufoEntity entity);
	
	default List<PitufoDTO> toDTOList(List<PitufoEntity> entityList){
		return entityList.stream().map(pitufoE -> PitufoMapper.MAPPER.toDTO(pitufoE))
				.collect(Collectors.toList());
	}
}
