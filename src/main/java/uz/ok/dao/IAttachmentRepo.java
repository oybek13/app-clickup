package uz.ok.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ok.entity.Attachment;

import java.util.UUID;

@Repository
public interface IAttachmentRepo extends JpaRepository<Attachment, UUID> {
}
