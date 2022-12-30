package cn.zzwtsy.pu.tools;

import com.fasterxml.jackson.databind.JsonNode;

import static cn.zzwtsy.pu.utils.DateUtil.formatUnixTimestamp;

/**
 * json解析
 *
 * @author zzwtsy
 * @since 2022/12/30
 */
public class JsonParse {
    /**
     * 事件列表解析
     *
     * @param contentNode 内容节点
     * @return {@link String}
     */
    public String eventListParse(JsonNode contentNode) {
        String event;
        JsonNode tempNode;
        //获取当前时间戳（转换为10位）
        long nowTimestamp = System.currentTimeMillis() / 1000;
        StringBuilder eventList = new StringBuilder();
        int contentLength = contentNode.size();
        for (int i = 0; i < contentLength; i++) {
            tempNode = contentNode.get(i);
            //活动状态
            String eventStatus = tempNode.get("eventStatus").asText();
            //报名结束时间
            long eventRegistrationCloseTime = tempNode.get("deadline").asLong();
            //判断活动是否仍在进行和当前时间小于报名结束时间
            if ("4".equals(eventStatus) && nowTimestamp < eventRegistrationCloseTime) {
                //活动标题
                String title = tempNode.get("title").asText();
                //活动开始时间
                String sTime = formatUnixTimestamp(tempNode.get("sTime").asLong());
                //活动结束时间
                String eTime = formatUnixTimestamp(tempNode.get("eTime").asLong());
                //报名开始时间
                String startline = formatUnixTimestamp(tempNode.get("startline").asLong());
                //报名结束时间
                String deadline = formatUnixTimestamp(eventRegistrationCloseTime);
                //剩余可参加人数
                String limitCount = tempNode.get("limitCount").asText();
                //活动地址
                String address = tempNode.get("address").asText();
                event = "活动名称：\n\t\t\t《" + title + "》\n"
                        + "活动地址：\n\t\t\t『" + address + "』\n"
                        + "活动开始时间：\n\t\t\t" + sTime + "\n"
                        + "活动结束时间：\n\t\t\t" + eTime + "\n"
                        + "报名开始时间：\n\t\t\t" + startline + "\n"
                        + "报名结束时间：\n\t\t\t" + deadline + "\n"
                        + "剩余可参加人数：" + limitCount + "\n"
                        + "============" + "\n";
                eventList.append(event);
            }
        }
        return eventList.toString();
    }
}
