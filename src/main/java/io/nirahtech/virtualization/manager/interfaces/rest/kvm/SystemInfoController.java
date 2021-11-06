package io.nirahtech.virtualization.manager.interfaces.rest.kvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.nirahtech.virtualization.manager.domain.commands.CommandResult;
import io.nirahtech.virtualization.manager.domain.commands.kvm.SystemInfoCommand;

/**
 * Interface that represents the root context of the KVM Manager RESTfull API.
 * 
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
@RestController
@RequestMapping("/system-info")
public class SystemInfoController extends KvmManagerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemInfoController.class);

    public SystemInfoController() {
        super(new SystemInfoCommand());
        LOGGER.info("[KVM] Virtual Machines Controller is requested.");
    }

    @GetMapping
    @ResponseBody
    public CommandResult getSystemInfo() {
        LOGGER.info("[KVM] A KVM command is requested to list all VM.");
        return this.command.execute();
    }
}
