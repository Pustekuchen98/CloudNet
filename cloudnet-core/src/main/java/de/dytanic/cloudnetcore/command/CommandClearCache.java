/*
 * Copyright (c) Tarek Hosni El Alaoui 2017
 */

package de.dytanic.cloudnetcore.command;

import de.dytanic.cloudnet.command.Command;
import de.dytanic.cloudnet.command.CommandSender;
import de.dytanic.cloudnetcore.CloudNet;
import de.dytanic.cloudnetcore.network.components.Wrapper;

import java.util.function.Consumer;

/**
 * Created by Tareko on 23.08.2017.
 */
public class CommandClearCache extends Command {

    public CommandClearCache()
    {
        super("clearcache", "cloudnet.command.clearcache", "cc");
    }

    @Override
    public void onExecuteCommand(CommandSender sender, String[] args)
    {
        CloudNet.getInstance().getWrappers().values().forEach(new Consumer<Wrapper>() {
            @Override
            public void accept(Wrapper wrapper)
            {
                if(wrapper.getChannel() != null)
                {
                    wrapper.sendCommand("clearcache");
                }
            }
        });
        sender.sendMessage("The cache of all wrappers are cleared");
    }
}