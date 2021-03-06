package lol.szymeko.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ResourceLeakDetector;
import lol.szymeko.server.channel.ChannelInitializer;
import lol.szymeko.server.data.ConnectionData;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Szymon on 08.12.2021
 * @project LicenseSystem
 */
@Getter
public class Server {

    private final Map<String, ConnectionData> authData = new HashMap<>();

    private static Server instance;
    public static Server getInstance() {
        return instance;
    }

    public Server() {
        instance = this;
    }

    public ConnectionData getUser(String user) {
        for (ConnectionData u : authData.values()) {
            if (u.getUser().equals(user)) return u;
        }
        return null;
    }

    public void bind() {
        ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.DISABLED);

        Class<? extends ServerChannel> channelClass;

        EventLoopGroup bossGroup;
        EventLoopGroup workerGroup;
        if (Epoll.isAvailable()) {
            bossGroup = new EpollEventLoopGroup();
            workerGroup = new EpollEventLoopGroup();
            channelClass = EpollServerSocketChannel.class;
        } else {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup();
            channelClass = NioServerSocketChannel.class;
        }

        new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(channelClass)
                .childHandler(new ChannelInitializer())
                .childOption(ChannelOption.TCP_NODELAY, true)
                .localAddress(2137)
                .bind();

        System.out.println("Server started.");
    }

}
