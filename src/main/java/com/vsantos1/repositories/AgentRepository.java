package com.vsantos1.repositories;

import com.vsantos1.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    List<Agent> findAgentsByNameContainingIgnoreCase(String name);
}
