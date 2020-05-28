package me.nurio.bungeekeeper.packets.bungee;

import lombok.*;
import me.nurio.bungeekeeper.packets.IdentityUtil;
import me.nurio.bungeekeeper.packets.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ConnectionPacket implements Packet {

    public static final byte PACKET_ID = 21;

    private long eventId = IdentityUtil.timeBasedId();

    @NonNull private String username;
    @NonNull private InetSocketAddress address;
    @NonNull private int protocol;

    @Override
    public byte getId() {
        return PACKET_ID;
    }

    @Override
    @SneakyThrows
    public void read(DataInputStream inputStream) {
        username = inputStream.readUTF();

        String inetAddress = inputStream.readUTF();
        int port = inputStream.readInt();
        address = InetSocketAddress.createUnresolved(inetAddress, port);

        protocol = inputStream.readInt();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);
        outputStream.writeUTF(username);
        outputStream.writeUTF(address.getHostName());
        outputStream.writeInt(address.getPort());
        outputStream.writeInt(protocol);
    }

}