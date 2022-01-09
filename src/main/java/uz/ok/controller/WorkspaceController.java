package uz.ok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ok.dto.request.WorkspaceDto;
import uz.ok.dto.response.ApiResponse;
import uz.ok.entity.User;
import uz.ok.security.CurrentUser;
import uz.ok.service.impl.WorkspaceService;

import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<?> addWorkspace(@RequestBody WorkspaceDto workspaceDto, @CurrentUser User user){
        ApiResponse apiResponse = workspaceService.addWorkspace(workspaceDto, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editWorkspace(@PathVariable Long id, @RequestBody WorkspaceDto workspaceDto){
        ApiResponse apiResponse = workspaceService.editWorkspace(workspaceDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeOwnerWorkspace(@PathVariable Long id,
                                                  @RequestParam UUID ownerId){
       ApiResponse apiResponse = workspaceService.changeOwnerWorkspace(id, ownerId);
       return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkspace(@PathVariable Long id){
        ApiResponse apiResponse = workspaceService.deleteWorkspace(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
