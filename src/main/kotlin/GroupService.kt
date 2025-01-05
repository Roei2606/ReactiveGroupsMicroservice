package org.example

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface GroupService {
    fun create(group: GroupBoundary): Mono<GroupBoundary>
    fun getById(groupId: String): Mono<GroupBoundary>
    fun getGroups(page: Int, size: Int): Flux<GroupBoundary>
    fun updateGroup(groupId: String, updatedGroup: GroupBoundary): Mono<Void>
    fun deleteAllGroups(): Mono<Void>
}