package uz.ok.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.ok.dao.IAttachmentRepo;
import uz.ok.dao.IWorkspaceRepo;
import uz.ok.dto.request.WorkspaceDto;
import uz.ok.dto.response.ApiResponse;
import uz.ok.entity.User;
import uz.ok.entity.Workspace;
import uz.ok.service.impl.WorkspaceService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final IWorkspaceRepo iWorkspaceRepo;
    private final IAttachmentRepo iAttachmentRepo;

    @Override
    public ApiResponse addWorkspace(WorkspaceDto workspaceDto, User user) {
        if (iWorkspaceRepo.existsByOwnerIdAndName(user.getId(), workspaceDto.getName()))
        return new ApiResponse("You got a workspace like that name!", false);
        Workspace workspace = new Workspace(
                workspaceDto.getName(),
                workspaceDto.getColor(),
                user,
                workspaceDto.getName().substring(0, 1),
                workspaceDto.getAvatarId() == null ? null :
                        iAttachmentRepo.findById(workspaceDto.getAvatarId())
                                .orElseThrow(()-> new ResourceNotFoundException("attachment"))
        );
        return null;
    }

    @Override
    public ApiResponse editWorkspace(WorkspaceDto workspaceDto) {
        return null;
    }

    @Override
    public ApiResponse changeOwnerWorkspace(Long id, UUID ownerId) {
        return null;
    }

    @Override
    public ApiResponse deleteWorkspace(Long id) {
        return null;
    }
}
