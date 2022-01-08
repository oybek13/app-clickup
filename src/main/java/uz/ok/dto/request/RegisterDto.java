package uz.ok.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class RegisterDto {

    @NotNull
    private String fullName;

    @NotNull
    private String email;

    @NotNull
    private String password;
}
