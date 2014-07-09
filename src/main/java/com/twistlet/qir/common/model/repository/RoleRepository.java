package com.twistlet.qir.common.model.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.twistlet.qir.common.model.entity.Role;

public interface RoleRepository extends ElasticsearchRepository<Role, String> {

}
