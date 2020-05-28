package me.nurio.bungeekeeper.packets.bungee;

import lombok.*;
import me.nurio.bungeekeeper.packets.IdentityUtil;
import me.nurio.bungeekeeper.packets.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.util.UUID;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ServerChangePacket implements Packet {

    public static final byte PACKET_ID = 23;

    private long eventId = IdentityUtil.timeBasedId();

    @NonNull private String playerName;
    @NonNull private UUID uniqueId;
    @NonNull private InetSocketAddress address;
    @NonNull private String serverName;

    @Override
    public byte getId() {
        return PACKET_ID;
    }

    @Override
    @SneakyThrows
    public void read(DataInputStream inputStream) {
        playerName = inputStream.readUTF();
        uniqueId = UUID.fromString(inputStream.readUTF());

        String inetAddress = inputStream.readUTF();
        int inetPort = inputStream.readInt();
        address = InetSocketAddress.createUnresolved(inetAddress, inetPort);

        serverName = inputStream.readUTF();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);
        outputStream.writeUTF(playerName);
        outputStream.writeUTF(uniqueId.toString());
        outputStream.writeUTF(address.getHostName());
        outputStream.writeInt(address.getPort());
        outputStream.writeUTF(serverName);
    }

}