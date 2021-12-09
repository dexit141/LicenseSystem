package lol.szymeko.server.manager;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lol.szymeko.server.Server;
import lol.szymeko.server.data.ConnectionData;

/**
 * @author Szymon on 08.12.2021
 * @project LicenseSystem
 */
public class PacketManager extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        String adress = ctx.channel().remoteAddress().toString();
        String received = byteBuf.toString(CharsetUtil.UTF_8);
        String[] auth = received.split(":");

        ConnectionData connectionData = Server.getInstance().getUser(auth[0]);
        if (connectionData == null) {
            System.out.println(adress + " -> Wrong user/license.");
            ctx.writeAndFlush(Unpooled.buffer().writeBoolean(false));
        } else {
            if (connectionData.getUser().equals(auth[0]) && connectionData.getLicense().equals(auth[1])) {
                System.out.println(adress + " -> License is correct.");
                ctx.writeAndFlush(Unpooled.buffer().writeBoolean(true));
            } else {
                System.out.println(adress + " -> Wrong user/license!");
                ctx.writeAndFlush(Unpooled.buffer().writeBoolean(false));
            }
        }
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String adress = ctx.channel().remoteAddress().toString();
        System.out.println(adress + " connected.");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String adress = ctx.channel().remoteAddress().toString();
        System.out.println(adress + " disconnected.");
    }
}
