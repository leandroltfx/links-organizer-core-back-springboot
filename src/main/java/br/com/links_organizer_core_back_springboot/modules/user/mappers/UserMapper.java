package br.com.links_organizer_core_back_springboot.modules.user.mappers;

import br.com.links_organizer_core_back_springboot.modules.user.entities.UserEntity;
import br.com.links_organizer_core_back_springboot.modules.user.model.dto.UserRegistrationRequestDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserEntity toEntity(UserRegistrationRequestDto userRegistrationRequestDto) {
        return UserEntity
                .builder()
                .userName(userRegistrationRequestDto.getUserName())
                .email(userRegistrationRequestDto.getEmail())
                .password(userRegistrationRequestDto.getPassword())
                .build();
    }


}
