package lol.szymeko.client.manager;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author Szymon on 09.12.2021
 * @project LicenseSystem
 */
public class PacketManager extends SimpleChannelInboundHandler {

    private final String username;
    private final String license;

    public PacketManager(String username, String license) {
        this.username = username;
        this.license = license;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        if (byteBuf.readBoolean()) {
            System.out.println("licencja dziala");
        } else {
            System.out.println("huju stary zbity licencja ci nie dziala");
            System.exit(0);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Connected");
        ctx.writeAndFlush(Unpooled.copiedBuffer(username + ":" + license, CharsetUtil.UTF_8));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Disconnected");
    }

}
