package me.nurio.bungeekeeper.packets.bungee;

import lombok.*;
import me.nurio.bungeekeeper.packets.IdentityUtil;
import me.nurio.bungeekeeper.packets.Packet;
import me.nurio.bungeekeeper.packets.entities.RemoteAddress;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.UUID;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class PostConnectionPacket implements Packet {

    public static final byte PACKET_ID = 22;

    @Getter private long eventId = IdentityUtil.timeBasedId();

    @Getter @NonNull private String username;
    @Getter @NonNull private UUID uniqueId;
    @Getter @NonNull private RemoteAddress address;
    @Getter @NonNull private int protocol;

    @Override
    public byte getId() {
        return PACKET_ID;
    }

    @Override
    @SneakyThrows
    public void read(DataInputStream inputStream) {
        eventId = inputStream.readLong();
        username = inputStream.readUTF();
        uniqueId = UUID.fromString(inputStream.readUTF());

        String ipAddress = inputStream.readUTF();
        String hostName = inputStream.readUTF();
        int inetPort = inputStream.readInt();
        address = new RemoteAddress(ipAddress, hostName, inetPort);

        protocol = inputStream.readInt();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);

        outputStream.writeLong(eventId);
        outputStream.writeUTF(username);
        outputStream.writeUTF(uniqueId.toString());

        outputStream.writeUTF(address.getIpAddress());
        outputStream.writeUTF(address.getHostName());
        outputStream.writeInt(address.getPort());

        outputStream.writeInt(protocol);
    }

}