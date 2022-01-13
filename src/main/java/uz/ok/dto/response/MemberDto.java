package uz.ok.dto.response;

import lombok.Data;
import uz.ok.entity.enums.AddType;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class MemberDto {
    private UUID id;
    private String fullName;
    private String email;
    private String roleName;
    private Timestamp lastActive;
    private UUID roleId;
    private AddType addType;
}
