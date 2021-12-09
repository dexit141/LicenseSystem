package lol.szymeko.client.channel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import lol.szymeko.client.Main;
import lol.szymeko.client.manager.PacketManager;
/**
 * @author Szymon on 09.12.2021
 * @project LicenseSystem
 */
public class ChannelInitializer extends io.netty.channel.ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        PacketManager packetManager = new PacketManager(Main.username, Main.license);
        pipeline.addLast(packetManager);

    }

}