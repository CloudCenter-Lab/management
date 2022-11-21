package com.gh.management.system.netty;


import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * 自定义服务端处理器
 */


@Component
@ChannelHandler.Sharable
public class ServerHandler1 extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);


    /**
     * 在与客户端的连接已经建立之后将被调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("netty客户端与服务端连接开始...");
    }

    /**
     * 当从客户端接收到一个消息时被调用
     * msg 就是硬件传送过来的数据信息
     */
    @Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info(msg.toString());
    	//this.getGisLocation(msg.toString());   //这是下面自己写的业务逻辑处理的方法
	}



    /**
     * 客户端与服务端断开连接时调用
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("netty客户端与服务端连接关闭...");
    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        logger.info("信息接收完毕...");
    }


    /**
     * 在处理过程中引发异常时被调用
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
        System.out.println("异常信息：rn " + cause.getMessage());
    }


}