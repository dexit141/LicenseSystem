package lol.szymeko.server.channel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import lol.szymeko.server.manager.PacketManager;

/**
 * @author Szymon on 08.12.2021
 * @project LicenseSystem
 */
public class ChannelInitializer extends io.netty.channel.ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        PacketManager packetManager = new PacketManager();
        pipeline.addLast(packetManager);

    }

}