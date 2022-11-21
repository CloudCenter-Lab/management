package com.gh.management.system.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;


/**
 * Netty服务   服务启动监听器
 * @author zl
 * @date 2019-12-11
 *
 */
@Component
public class NettyServer {

    private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    @Value("${netty.port}")
    private int port;

    public static ServerSocketChannel serverSocketChannel;

    @Autowired
    private ServerHandler serverHandler;


    public void start() throws Exception {
        // 连接处理group
        EventLoopGroup boss = new NioEventLoopGroup();
        // 事件处理group
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();//1.创建ServerBootStrap实例
        // 绑定处理group
        bootstrap.group(boss, worker)//2.设置并绑定Reactor线程池：EventLoopGroup，EventLoop就是处理所有注册到本线程的Selector上面的Channel
        		.channel(NioServerSocketChannel.class)//3.设置并绑定服务端的channel
                // 保持连接数
                .option(ChannelOption.SO_BACKLOG, 1024)
                // 有数据立即发送
                .option(ChannelOption.TCP_NODELAY, true)
                // 保持连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                // 处理新连接
                .childHandler(new ChannelInitializer<SocketChannel>() {//设置了客户端连接socket属性。
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        // 增加任务处理
                        ChannelPipeline p = sc.pipeline();
                        p.addLast(
                                //默认的编码器
                                new StringEncoder(StandardCharsets.UTF_8),
                                new StringDecoder(StandardCharsets.UTF_8),
                                // 自定义的处理器
                                serverHandler);
                    }
                });

        // 绑定端口，同步等待成功
        ChannelFuture future;
        try {
        	logger.info("netty服务器在[{}]端口启动监听",port);
            future = bootstrap.bind(port).sync();//真正让netty跑起来的重点
            if (future.isSuccess()) {
                serverSocketChannel = (ServerSocketChannel) future.channel();
                logger.info("netty服务开启成功");
            } else {
                logger.info("netty服务开启失败");
            }
            // 等待服务监听端口关闭,就是由于这里会将线程阻塞，导致无法发送信息，所以我这里开了线程
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅地退出，释放线程池资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}