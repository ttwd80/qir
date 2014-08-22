package com.twistlet.qir.common.model.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.twistlet.qir.common.model.entity.Role;

@Repository
public interface RoleRepository extends ElasticsearchRepository<Role, String> {

}
