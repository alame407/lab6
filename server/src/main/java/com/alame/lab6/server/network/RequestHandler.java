package com.alame.lab6.server.network;

import com.alame.lab6.common.network.Frame;
import com.alame.lab6.common.network.NetworkUtils;
import com.alame.lab6.common.request.ClientServerInterface;
import com.alame.lab6.common.request.Request;
import com.alame.lab6.common.response.Response;
import com.alame.lab6.server.App;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * class for receive request handle it and send response
 */
public class RequestHandler {
    private final DatagramChannel datagramChannel;
    private final static int port = 50123;
    private final Logger logger = App.logger;
    private final ClientServerInterface server;
    private final static int MAX_SERIALIZED_FRAME_SIZE = 6000;
    private final FrameMapper frameMapper;
    public RequestHandler(DatagramChannel datagramChannel, ClientServerInterface server, FrameMapper frameMapper) throws IOException {
        this.datagramChannel = datagramChannel;
        this.frameMapper = frameMapper;
        this.datagramChannel.configureBlocking(false);
        SocketAddress address = new InetSocketAddress(port);
        datagramChannel.bind(address);
        this.server = server;
    }

    /**
     * receive request handle it and send response
     * @throws IOException if receive or send fail
     */
    public void ReceiveThenSend() throws IOException {
        Pair<SocketAddress, Boolean> pair = receiveFrame();
        SocketAddress clientAddress = pair.getLeft();
        boolean receivedRequest = pair.getRight();
        if (receivedRequest) {
            Request request = SerializationUtils.deserialize(
                    NetworkUtils.convertListFramesToByteArray(frameMapper.getFramesByUser(clientAddress)));
            frameMapper.removeUser(clientAddress);
            request.setServer(server);
            logger.info("получен новый запрос " + request);
            Response response = request.handle();
            send(SerializationUtils.serialize(response), clientAddress);
            logger.info("отправлен ответ на запрос");
        }
    }
    public void send(byte[] bytes, SocketAddress socketAddress) throws IOException{
        int step = 4000;
        int current = 0;
        int next = Math.min(current + step, bytes.length);
        while(next!= bytes.length){
            Frame frame = new Frame(Arrays.copyOfRange(bytes, current, next), false);
            datagramChannel.send(ByteBuffer.wrap(SerializationUtils.serialize(frame)),socketAddress);
            current = next;
            next = Math.min(current + step, bytes.length);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Frame frame = new Frame(Arrays.copyOfRange(bytes, current, next+1), true);
        datagramChannel.send(ByteBuffer.wrap(SerializationUtils.serialize(frame)),socketAddress);
    }
    public Pair<SocketAddress, Boolean> receiveFrame() throws IOException{
        byte[] bufferResponse = new byte[MAX_SERIALIZED_FRAME_SIZE];
        SocketAddress clientAddress = datagramChannel.receive(ByteBuffer.wrap(bufferResponse));
        if(clientAddress!=null) {
            Frame frame = SerializationUtils.deserialize(bufferResponse);
            frameMapper.addFrameToUser(clientAddress, frame);
            return new ImmutablePair<>(clientAddress, frame.isLast());
        }
        return new ImmutablePair<>(null, false);
    }
}
