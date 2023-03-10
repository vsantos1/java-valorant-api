package com.vsantos1.services.gateways;


import com.vsantos1.dtos.AgentDTO;
import com.vsantos1.models.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AgentGateway {

    Page<Agent> findAllPaginated(Pageable pageable);

    Agent findById(Long id);

    List<Agent> findByName(String name);

    Agent execute(AgentDTO agentDTO);

    Agent update(Long id, AgentDTO agentDTO);

    void deleteById(Long id);

}
