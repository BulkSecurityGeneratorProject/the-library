package org.jhipster.thibaut.thelibrary.service.impl;

import org.jhipster.thibaut.thelibrary.service.RoleService;
import org.jhipster.thibaut.thelibrary.domain.Role;
import org.jhipster.thibaut.thelibrary.repository.RoleRepository;
import org.jhipster.thibaut.thelibrary.repository.search.RoleSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Role.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    private final RoleSearchRepository roleSearchRepository;

    public RoleServiceImpl(RoleRepository roleRepository, RoleSearchRepository roleSearchRepository) {
        this.roleRepository = roleRepository;
        this.roleSearchRepository = roleSearchRepository;
    }

    /**
     * Save a role.
     *
     * @param role the entity to save
     * @return the persisted entity
     */
    @Override
    public Role save(Role role) {
        log.debug("Request to save Role : {}", role);
        Role result = roleRepository.save(role);
        roleSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the roles.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        log.debug("Request to get all Roles");
        return roleRepository.findAll();
    }


    /**
     * Get one role by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findOne(Long id) {
        log.debug("Request to get Role : {}", id);
        return roleRepository.findById(id);
    }

    /**
     * Delete the role by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Role : {}", id);
        roleRepository.deleteById(id);
        roleSearchRepository.deleteById(id);
    }

    /**
     * Search for the role corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Role> search(String query) {
        log.debug("Request to search Roles for query {}", query);
        return StreamSupport
            .stream(roleSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
