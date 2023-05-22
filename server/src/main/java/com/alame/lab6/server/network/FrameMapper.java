package com.alame.lab6.server.network;

import com.alame.lab6.common.network.Frame;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FrameMapper {
    private final HashMap<SocketAddress, List<Frame>> userFramesMap;
    public FrameMapper(HashMap<SocketAddress, List<Frame>> userFramesMap){
        this.userFramesMap = userFramesMap;
    }
    public void addFrameToUser(SocketAddress socketAddress, Frame frame){
        if(userFramesMap.containsKey(socketAddress)){
            userFramesMap.get(socketAddress).add(frame);
        }
        else{
            userFramesMap.put(socketAddress, new ArrayList<>(){{
                add(frame);
            }});
        }
    }
    public List<Frame> getFramesByUser(SocketAddress socketAddress){
        return userFramesMap.get(socketAddress);
    }
    public void removeUser(SocketAddress socketAddress){
        userFramesMap.remove(socketAddress);
    }
}
