package lol.szymeko.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lol.szymeko.client.channel.ChannelInitializer;
import lombok.Getter;

/**
 * @author Szymon on 09.12.2021
 * @project LicenseSystem
 */
@Getter
public class Client {

    private Channel channel;

    public void start(String adress, Integer port) {
        try {
            Bootstrap bootstrap = new Bootstrap();
            NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
            bootstrap.group(nioEventLoopGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer());
            ChannelFuture channelFuture = bootstrap.connect(adress, port).syncUninterruptibly();
            channel = channelFuture.channel();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.exit(0);
        }
    }

}
