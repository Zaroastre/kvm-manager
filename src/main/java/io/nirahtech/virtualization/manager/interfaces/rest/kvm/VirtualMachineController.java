package io.nirahtech.virtualization.manager.interfaces.rest.kvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.nirahtech.virtualization.manager.domain.commands.CommandResult;
import io.nirahtech.virtualization.manager.domain.commands.kvm.ListVirtualMachinesCommand;
import io.nirahtech.virtualization.manager.domain.entities.KvmVirtualMachine;
import io.nirahtech.virtualization.manager.interfaces.api.Crud;

/**
 * Interface that represents the root context of the KVM Manager RESTfull API.
 * 
 * @author METIVIER Nicolas
 * @since 0.0.1
 */
@RestController
@RequestMapping("/virtual-machines")
public class VirtualMachineController extends KvmManagerController implements Crud<KvmVirtualMachine> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(KvmManagerController.class);

    public VirtualMachineController() {
        super(new ListVirtualMachinesCommand());
        LOGGER.info("[KVM] Virtual Machines Controller is requested.");
    }

    @GetMapping
    @ResponseBody
    public CommandResult list() {
        LOGGER.info("[KVM] A KVM command is requested to list all VM.");
        return this.command.execute();
    }

    @Override
    public KvmVirtualMachine create(KvmVirtualMachine entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public KvmVirtualMachine read(String identifier) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public KvmVirtualMachine update(String identifier, KvmVirtualMachine entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(String identifier) {
        // TODO Auto-generated method stub
        
    }
}
