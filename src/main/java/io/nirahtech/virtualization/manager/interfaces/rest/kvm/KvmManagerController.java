package io.nirahtech.virtualization.manager.interfaces.rest.kvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nirahtech.virtualization.manager.domain.commands.Command;

/**
 * Interface that represents the root context of the KVM Manager RESTfull API.
 * 
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
@RestController
@RequestMapping("/kvm-manager")
public abstract class KvmManagerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(KvmManagerController.class);

    protected final Command command;

    protected KvmManagerController(final Command command) {
        this.command = command;
        LOGGER.info("[KVM] KVM Manager Controller is requested.");
    }
    
}
