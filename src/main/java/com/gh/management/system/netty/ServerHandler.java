package com.gh.management.system.netty;

import com.gh.management.system.service.DeviceService;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;


/**
 * @author YJL
 * @create 2022-11-20 20:39
 */

@Component
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<String> {


    private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private ChannelHandlerContext innerCtx;//内部ctx  服务器转发给认证端口
    private ChannelHandlerContext outCtx;//外部ctx    硬件组发送至服务器
    ChannelFuture connectFuture;
    /**
     * 在连接的时候创建一个netty客户端连接真正的服务器
     *
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx1) throws Exception {

        logger.info("硬件端和服务器连接开始...");
        outCtx = ctx1;
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(new StringEncoder())
                             .addLast(new ByteArrayEncoder())
                             .addLast(new SimpleChannelInboundHandler<ByteBuf>() {
                            // 内层建立的连接，从这里接收内层的应答，在这里是服务端的应答
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
                                /**
                                 * 如果接到消息，则将该消息回复给外部
                                 */
                                if (outCtx != null && outCtx.channel().isActive()) {
                                    logger.info("认证端返回认证消息给硬件端："+in.toString(Charset.defaultCharset()));
                                    outCtx.writeAndFlush(in.copy());
                                }

                            }
                            /**
                             * 内部如果建立链接,就将innerCtx置为属性
                             */
                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                logger.info("硬件组和认证端连接开始...");
                                innerCtx = ctx;
                                System.out.println("链接服务" + ctx.channel().toString());
                            }
                            /**
                             * 内部链接如果关闭,外部链接也关闭
                             */
                            @Override
                            public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                if (outCtx != null && outCtx.channel().isActive()) {
                                    outCtx.close();
                                    logger.info("认证服务器异常，服务关闭");
                                }
                            }
                        });
            }
        });
        bootstrap.group(ctx1.channel().eventLoop());// 关键在这里。把外层channel的eventLoop挂接在内层上
        connectFuture = bootstrap.connect(
                new InetSocketAddress("localhost", 8888));
    }
    /**
     * 如果接到数据,则让内部链接发送数据
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // TODO Auto-generated method stub
        if (connectFuture.isDone()) {
            // do something with the data
            // channel并不共享，共享的是线程EventLoop，所以如果想向内层转发的话
            // 需要持有内层的channel

            if (innerCtx != null && innerCtx.channel().isActive()) {
                logger.info("硬件组发送给认证端："+msg);

                innerCtx.writeAndFlush(msg);
            }
        }
    }
    /**
     * 如果断开外部链接,则内部链接也断开
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (innerCtx != null && innerCtx.channel().isActive()) {
            innerCtx.close();
            logger.info("硬件组关闭链接，服务关闭");
        }
    }

}