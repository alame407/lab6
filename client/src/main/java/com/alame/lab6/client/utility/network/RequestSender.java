package com.alame.lab6.client.utility.network;

import com.alame.lab6.common.network.Frame;
import com.alame.lab6.common.network.NetworkUtils;
import com.alame.lab6.common.request.Request;
import com.alame.lab6.common.response.Response;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.SerializationUtils;

/**
 * class for sending requests and receive response
 */
public class RequestSender {
    private final DatagramSocket datagramSocket;
    private final static int port = 50123;
    private final static int MAX_SERIALIZED_FRAME_SIZE = 6000;
    public RequestSender(DatagramSocket datagramSocket) {
        this.datagramSocket = datagramSocket;
    }

    /**
     * send given request to server then receive response from server
     * @param request - request to send
     * @return response from server
     * @throws IOException if sending or receiving failed
     */
    public <T> Response<T> sendThenReceive(Request request) throws IOException {
        InetAddress host = InetAddress.getLocalHost();
        SocketAddress socketAddress = new InetSocketAddress(host, port);
        byte[] bufferRequest = SerializationUtils.serialize(request);
        send(bufferRequest, socketAddress);
        return receive();
    }
    public <T> Response<T> receive() throws IOException{
        boolean received = false;
        List<Frame> frames = new ArrayList<>();
        byte[] bufferResponse = new byte[MAX_SERIALIZED_FRAME_SIZE];
        while(!received) {
            DatagramPacket datagramPacketReceive = new DatagramPacket(bufferResponse, bufferResponse.length);
            datagramSocket.receive(datagramPacketReceive);
            Frame frame = SerializationUtils.deserialize(bufferResponse);
            frames.add(frame);
            if (frame.isLast()) {
                received = true;
            }
        }
        return SerializationUtils.deserialize(NetworkUtils.convertListFramesToByteArray(frames));
    }
    public void send(byte[] bytes, SocketAddress socketAddress) throws IOException{
        int step = 4000;
        int current = 0;
        int next = Math.min(current + step, bytes.length);
        while(next!= bytes.length){
            Frame frame = new Frame(Arrays.copyOfRange(bytes, current, next), false);
            byte[] bufferFrame = SerializationUtils.serialize(frame);
            datagramSocket.send(new DatagramPacket(bufferFrame, bufferFrame.length, socketAddress));
            current = next;
            next = Math.min(current + step, bytes.length);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Frame frame = new Frame(Arrays.copyOfRange(bytes, current, next+1), true);
        byte[] bufferFrame = SerializationUtils.serialize(frame);
        datagramSocket.send(new DatagramPacket(bufferFrame, bufferFrame.length, socketAddress));
    }
}
