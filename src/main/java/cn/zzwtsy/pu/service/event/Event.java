package cn.zzwtsy.pu.service.event;

import net.mamoe.mirai.message.data.MessageChain;

/**
 * 事件
 *
 * @author zzwtsy
 * @since 2023/01/27
 */
public interface Event {

    /**
     * 获取消息
     *
     * @return {@link MessageChain}
     */
    MessageChain getMessage();

}
