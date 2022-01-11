package uz.ok.service.impl;

import uz.ok.dto.request.MemberDto;
import uz.ok.dto.request.WorkspaceDto;
import uz.ok.dto.response.ApiResponse;
import uz.ok.entity.User;
import uz.ok.entity.Workspace;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {

    ApiResponse addWorkspace(WorkspaceDto workspaceDto, User user);

    ApiResponse editWorkspace(WorkspaceDto workspaceDto);

    ApiResponse changeOwnerWorkspace(Long id, UUID ownerId);

    ApiResponse deleteWorkspace(Long id);

    List<Workspace> getAllWorkspaces();

    ApiResponse addOrEditOrRemoveWorkspace(Long id, MemberDto memberDto);
}