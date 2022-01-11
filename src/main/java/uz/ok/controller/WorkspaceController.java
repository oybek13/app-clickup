package uz.ok.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ok.dto.request.MemberDto;
import uz.ok.dto.request.WorkspaceDto;
import uz.ok.dto.response.ApiResponse;
import uz.ok.entity.User;
import uz.ok.entity.Workspace;
import uz.ok.security.CurrentUser;
import uz.ok.service.impl.WorkspaceService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<?> addWorkspace(@Valid @RequestBody WorkspaceDto workspaceDto, @CurrentUser User user){
        ApiResponse apiResponse = workspaceService.addWorkspace(workspaceDto, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    /**
     * NAME, COLOR, AVATAR MAY BE EDITED
      * @param id
     * @param workspaceDto
     * @return
     */
    @PutMapping("/edit/{id}")
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

    /**
     * DELETION WORKSPACE
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkspace(@PathVariable Long id){
        ApiResponse apiResponse = workspaceService.deleteWorkspace(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public List<Workspace> getAllWorkspace(){
        return workspaceService.getAllWorkspaces();
    }

    @PostMapping("/addOrEditOrRemove/{id}")
    public ResponseEntity<?> addOrEditOrRemoveWorkspace(@PathVariable Long id,
                                                        @RequestBody MemberDto memberDto){
        ApiResponse apiResponse = workspaceService.addOrEditOrRemoveWorkspace(id, memberDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/join")
    public ResponseEntity<?> joinToWorkspace(@RequestParam Long id,
                                             @CurrentUser User user){
        ApiResponse apiResponse = workspaceService.joinToWorkspace(id, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }



}
