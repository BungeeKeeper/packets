package me.nurio.bungeekeeper.packets.bungee;

import lombok.*;
import me.nurio.bungeekeeper.packets.IdentityUtil;
import me.nurio.bungeekeeper.packets.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class HandshakePacket implements Packet {

    public static final byte PACKET_ID = 20;

    @Getter private long eventId = IdentityUtil.timeBasedId();

    @Getter @NonNull private InetSocketAddress address;
    @Getter @NonNull private String domain;
    @Getter @NonNull private int port;
    @Getter @NonNull private int protocol;
    @Getter @NonNull private int requestedProtocol;

    @Override
    public byte getId() {
        return PACKET_ID;
    }

    @Override
    @SneakyThrows
    public void read(DataInputStream inputStream) {
        String ipAddress = inputStream.readUTF();
        String hostName = inputStream.readUTF();
        InetAddress inetAddress = InetAddress.getByAddress(hostName, InetAddress.getByName(ipAddress).getAddress());

        int inetPort = inputStream.readInt();
        address = new InetSocketAddress(inetAddress, inetPort);

        domain = inputStream.readUTF();
        port = inputStream.readInt();
        protocol = inputStream.readInt();
        requestedProtocol = inputStream.readInt();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);

        outputStream.writeUTF(address.getAddress().getHostAddress());
        outputStream.writeUTF(address.getAddress().getHostName());
        outputStream.writeInt(address.getPort());

        outputStream.writeUTF(domain);
        outputStream.writeInt(port);
        outputStream.writeInt(protocol);
        outputStream.writeInt(requestedProtocol);
    }

}