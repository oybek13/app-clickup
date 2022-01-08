package uz.ok.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.ok.entity.template.AbsUUIDEntity;

import javax.persistence.Entity;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment extends AbsUUIDEntity {

    private String name;

    private String original_name;

    private Long size;

    private String content_type;

}
