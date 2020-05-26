package me.nurio.bungeekeeper.packets.bungee;

import lombok.*;
import me.nurio.bungeekeeper.packets.IdentityUtil;
import me.nurio.bungeekeeper.packets.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class HandshakePacket implements Packet {

    public static final byte PACKET_ID = 20;

    private long eventId = IdentityUtil.timeBasedId();

    @NonNull private InetSocketAddress address;
    @NonNull private String domain;
    @NonNull private int port;
    @NonNull private int protocol;

    @Override
    public byte getId() {
        return PACKET_ID;
    }

    @Override
    @SneakyThrows
    public void read(DataInputStream inputStream) {
        String inetAddress = inputStream.readUTF();
        int inetPort = inputStream.readInt();
        address = InetSocketAddress.createUnresolved(inetAddress, inetPort);

        domain = inputStream.readUTF();
        port = inputStream.readInt();
        protocol = inputStream.readInt();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);

        outputStream.writeUTF(address.getHostName());
        outputStream.writeInt(address.getPort());
        outputStream.writeUTF(domain);
        outputStream.writeInt(port);
        outputStream.writeInt(protocol);
    }

}