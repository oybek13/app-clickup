package uz.ok.service.impl;

import uz.ok.dto.request.WorkspaceDto;
import uz.ok.dto.response.ApiResponse;
import uz.ok.entity.User;

import java.util.UUID;

public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDto workspaceDto, User user);

    ApiResponse editWorkspace(WorkspaceDto workspaceDto);

    ApiResponse changeOwnerWorkspace(Long id, UUID ownerId);

    ApiResponse deleteWorkspace(Long id);
}