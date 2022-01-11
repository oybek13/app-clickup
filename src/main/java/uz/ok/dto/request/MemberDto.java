package uz.ok.dto.request;

import lombok.Data;
import uz.ok.entity.enums.AddType;

import java.util.UUID;

@Data
public class MemberDto {
    private UUID id;
    private UUID roleId;
    private AddType addType;
}
