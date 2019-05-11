package cn.huihongcloud.server;

import cn.huihongcloud.component.BDComponent;
import cn.huihongcloud.entity.bd.BDInfo;
import cn.huihongcloud.entity.device.Device;
import cn.huihongcloud.mapper.DeviceMapper;
import cn.huihongcloud.service.DeviceService;
import cn.huihongcloud.util.SpringContextUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by 钟晖宏 on 2018/5/30
 */

public class ServerHandler extends SimpleChannelInboundHandler<String> {


    private StringBuffer stringBuffer;
    private DataParser dataParser;
    private BDComponent bdComponent;
    private DeviceMapper deviceMapper;

    ServerHandler() {
        stringBuffer = new StringBuffer();
        dataParser = SpringContextUtil.getContext().getBean(DataParser.class);
        bdComponent = SpringContextUtil.getContext().getBean(BDComponent.class);
        deviceMapper = SpringContextUtil.getContext().getBean(DeviceMapper.class);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) {
        stringBuffer.append(s);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        Device device = dataParser.parse(stringBuffer.toString());
        BDInfo bdInfo = bdComponent.parseLocation(device.getLatitude(), device.getLongitude());
        device.setAdcode(bdInfo.getResult().getAddressComponent().getAdcode());
        device.setProvince(bdInfo.getResult().getAddressComponent().getProvince());
        device.setCity(bdInfo.getResult().getAddressComponent().getCity());
        device.setArea(bdInfo.getResult().getAddressComponent().getArea());
        device.setTown(bdInfo.getResult().getAddressComponent().getTown());
        if (deviceMapper.isExist(device.getId())) {
            deviceMapper.updateDevice(device);
        } else {
            deviceMapper.addDevice(device);
        }

    }
}
