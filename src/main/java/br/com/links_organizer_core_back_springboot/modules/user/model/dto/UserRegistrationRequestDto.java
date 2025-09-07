package br.com.links_organizer_core_back_springboot.modules.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationRequestDto {

    private String userName;
    private String email;
    private String password;

}
