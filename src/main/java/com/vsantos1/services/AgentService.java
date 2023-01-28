package com.vsantos1.services;

import com.vsantos1.dtos.AgentDTO;
import com.vsantos1.exceptions.ResourceNotFoundException;
import com.vsantos1.mapper.Mapper;
import com.vsantos1.models.Agent;
import com.vsantos1.models.Game;
import com.vsantos1.repositories.AgentRepository;
import com.vsantos1.repositories.GameRepository;
import com.vsantos1.services.gateways.AgentGateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService implements AgentGateway {

    private final AgentRepository agentRepository;

    private final GameRepository gameRepository;

    public AgentService(AgentRepository agentRepository, GameRepository gameRepository) {
        this.agentRepository = agentRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Page<Agent> findAllPaginated(Pageable pageable) {
        return agentRepository.findAll(pageable);
    }

    @Override
    public Agent findById(Long id) {
        Optional<Agent> agentOptional = agentRepository.findById(id);

        if (agentOptional.isPresent()) {
            return agentOptional.get();
        }
        throw new ResourceNotFoundException("No records found for this ID");
    }

    public Game findGameByAgent(String name) {
        return gameRepository.findGameByAgents_Name(name);
    }

    @Override
    public List<Agent> findByName(String name) {
        return agentRepository.findAgentsByNameContainingIgnoreCase(name);
    }

    @Override
    public Agent execute(AgentDTO agentDTO) {
        Agent agent = new Agent();
        Mapper.copyProperties(agentDTO, agent);
        return agentRepository.save(agent);
    }

    @Override
    public Agent update(Long id, AgentDTO agentDTO) {
        Agent agent = this.findById(id);
        Mapper.copyProperties(agentDTO, agent);
        return agentRepository.save(agent);
    }

    @Override
    public void deleteById(Long id) {
        Agent agent = this.findById(id);

        agentRepository.deleteById(agent.getId());
    }
}
