/*
 * Copyright (c) Tarek Hosni El Alaoui 2017
 */

package de.dytanic.cloudnetcore.network.packet.in;

import com.google.gson.reflect.TypeToken;
import de.dytanic.cloudnetcore.CloudNet;
import de.dytanic.cloudnet.lib.utility.document.Document;
import de.dytanic.cloudnet.lib.network.protocol.packet.PacketInHandler;
import de.dytanic.cloudnet.lib.network.protocol.packet.PacketSender;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import de.dytanic.cloudnetcore.network.components.CloudServer;
import de.dytanic.cloudnetcore.network.components.MinecraftServer;

import java.lang.reflect.Type;

/**
 * Created by Tareko on 23.07.2017.
 */
public class PacketInUpdateServerInfo extends PacketInHandler {

    private static Type type = new TypeToken<ServerInfo>(){}.getType();

    @Override
    public void handleInput(Document data, PacketSender packetSender)
    {
        if(packetSender instanceof MinecraftServer)
        {
            ((MinecraftServer)packetSender).setLastServerInfo(((MinecraftServer)packetSender).getServerInfo());
            ((MinecraftServer)packetSender).setServerInfo(data.getObject("serverInfo", type));
            CloudNet.getInstance().getNetworkManager().handleServerInfoUpdate(((MinecraftServer)packetSender), data.getObject("serverInfo", type));
        }
        if(packetSender instanceof CloudServer)
        {
            ((CloudServer)packetSender).setLastServerInfo(((CloudServer)packetSender).getServerInfo());
            ((CloudServer)packetSender).setServerInfo(data.getObject("serverInfo", type));
            CloudNet.getInstance().getNetworkManager().handleServerInfoUpdate(((CloudServer)packetSender), data.getObject("serverInfo", type));
        }
    }
}