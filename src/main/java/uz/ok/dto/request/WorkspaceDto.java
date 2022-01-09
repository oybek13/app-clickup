package uz.ok.dto.request;

import lombok.Data;

import java.util.UUID;

@Data
public class WorkspaceDto {
    private String name;
    private String color;
    private UUID avatarId;
}
