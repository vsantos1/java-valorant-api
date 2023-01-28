package com.vsantos1.resources;

import com.vsantos1.dtos.AgentDTO;
import com.vsantos1.models.Agent;
import com.vsantos1.models.Game;
import com.vsantos1.services.AgentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class AgentResource {

    private final AgentService agentService;


    public AgentResource(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping(value = "/agents")
    public ResponseEntity<Page<Agent>> getAllPaginated(@PageableDefault() Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(agentService.findAllPaginated(pageable));
    }

    @GetMapping(value = "/agents/{agent_id}")
    public ResponseEntity<Agent> getById(@PathVariable("agent_id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(agentService.findById(id));
    }

    @GetMapping(value = "/agents/{agent_name}/games")
    public ResponseEntity<Game> getByAgentName(@PathVariable("agent_name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(agentService.findGameByAgent(name));
    }

    @PostMapping(value = "/agents")
    public ResponseEntity<Agent> create(@RequestBody @Valid AgentDTO agentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agentService.execute(agentDTO));
    }

    @PutMapping(value = "/agents/{agent_id}")
    public ResponseEntity<Agent> update(@PathVariable("agent_id") Long id, @RequestBody @Valid AgentDTO agentDTO) {

        return ResponseEntity.status(HttpStatus.OK).body(agentService.update(id, agentDTO));
    }

    @DeleteMapping(value = "/agents/{agent_id}")
    public ResponseEntity<Void> delete(@PathVariable("agent_id") Long id) {
        agentService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
