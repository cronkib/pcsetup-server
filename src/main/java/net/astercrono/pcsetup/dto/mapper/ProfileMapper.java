package net.astercrono.pcsetup.dto.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.astercrono.pcsetup.domain.Profile;
import net.astercrono.pcsetup.dto.ProfileDto;

@Component
public class ProfileMapper implements EntityDtoMapper<Profile, ProfileDto> {
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ProfileDto mapDtoFromEntity(Profile profile) {
		return modelMapper.map(profile, ProfileDto.class);
	}
	
	@Override
	public Profile mapEntityFromDto(ProfileDto dto) {
		return modelMapper.map(dto, Profile.class);
	}
}
