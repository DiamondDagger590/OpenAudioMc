package com.craftmend.openaudiomc.bungee.modules.commands.subcommand;

import com.craftmend.openaudiomc.bungee.OpenAudioMcBungee;
import com.craftmend.openaudiomc.generic.commands.interfaces.GenericExecutor;
import com.craftmend.openaudiomc.generic.commands.interfaces.SubCommand;
import com.craftmend.openaudiomc.generic.commands.objects.Argument;
import com.craftmend.openaudiomc.generic.node.enums.CommandProxy;
import com.craftmend.openaudiomc.generic.node.packets.CommandProxyPacket;
import com.craftmend.openaudiomc.spigot.modules.proxy.objects.CommandProxyPayload;
import com.craftmend.openaudiomc.velocity.messages.PacketPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeShowCommand extends SubCommand {

    public BungeeShowCommand() {
        super("show");
        registerArguments(

                new Argument("create <show name>",
                        "Create a new show"),

                new Argument("gui <show name>",
                        "Open the show editor"),

                new Argument("start <show name>",
                        "Start a show"),

                new Argument("loop <show name>",
                        "Start to loop a show until the server stops or the show is cancelled"),

                new Argument("cancel <show name>",
                        "Cancel a running show"),

                new Argument("add <show name> <time in MS> <type> <data...>",
                        "Add a task/cue to a show"),

                new Argument("info <show name>",
                        "Display info about a show")
        );
    }

    @Override
    public void onExecute(GenericExecutor sender, String[] args) {
        // pass on to the spigot server
        if (sender.getOriginal() instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender.getOriginal();

            CommandProxyPayload payload = new CommandProxyPayload();
            payload.setExecutor(player.getUniqueId());
            payload.setArgs(args);
            payload.setCommandProxy(CommandProxy.SHOW);

            OpenAudioMcBungee.getInstance().getNodeManager().getPacketManager().sendPacket(new PacketPlayer(player), new CommandProxyPacket(payload));
        }
    }
}
