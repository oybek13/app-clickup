package uz.ok.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class LoginDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
