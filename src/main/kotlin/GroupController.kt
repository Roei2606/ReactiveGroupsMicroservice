package org.example

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/groups")
class GroupController(
    val groupService: GroupService
) {
    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun create(@RequestBody group: GroupBoundary): Mono<GroupBoundary> {
        return this.groupService.create(group)
    }

    @GetMapping(
        path = ["/{groupId}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getById(@PathVariable groupId: String): Mono<GroupBoundary> {
        return this.groupService.getById(groupId)
    }

    @GetMapping(
        produces = [MediaType.TEXT_EVENT_STREAM_VALUE]
    )
    fun getGroups(
        @RequestParam(name = "page", required = false, defaultValue = "0") page: Int,
        @RequestParam(name = "size", required = false, defaultValue = "10") size: Int
    ): Flux<GroupBoundary> {
        return this.groupService.getGroups(page, size)
    }

    @PutMapping(
        path = ["/{groupId}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateGroup(
        @PathVariable groupId: String,
        @RequestBody updatedGroup: GroupBoundary
    ): Mono<Void> {
        return this.groupService.updateGroup(groupId, updatedGroup)
    }

    @DeleteMapping
    fun deleteAllGroups(): Mono<Void> {
        return this.groupService.deleteAllGroups()
    }
}
