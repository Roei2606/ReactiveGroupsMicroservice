package org.example

import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface GroupCrud : ReactiveMongoRepository<GroupEntity, String>{
    fun findAllByIdNotNull(pageable: PageRequest): Flux<GroupEntity>
}
