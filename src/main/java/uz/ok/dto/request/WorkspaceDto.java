package uz.ok.dto.request;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class WorkspaceDto {

    @NotNull
    private String name;

    @NotNull
    private String color;

    private UUID avatarId;
}
