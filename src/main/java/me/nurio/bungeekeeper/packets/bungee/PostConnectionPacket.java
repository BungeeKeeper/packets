package me.nurio.bungeekeeper.packets.bungee;

import lombok.*;
import me.nurio.bungeekeeper.packets.IdentityUtil;
import me.nurio.bungeekeeper.packets.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
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
    @Getter @NonNull private InetSocketAddress address;
    @Getter @NonNull private int protocol;

    @Override
    public byte getId() {
        return PACKET_ID;
    }

    @Override
    @SneakyThrows
    public void read(DataInputStream inputStream) {
        username = inputStream.readUTF();
        uniqueId = UUID.fromString(inputStream.readUTF());

        String ipAddress = inputStream.readUTF();
        String hostName = inputStream.readUTF();
        InetAddress inetAddress = InetAddress.getByAddress(hostName, InetAddress.getByName(ipAddress).getAddress());

        int inetPort = inputStream.readInt();
        address = new InetSocketAddress(inetAddress, inetPort);

        protocol = inputStream.readInt();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);

        outputStream.writeUTF(username);
        outputStream.writeUTF(uniqueId.toString());

        outputStream.writeUTF(address.getAddress().getHostAddress());
        outputStream.writeUTF(address.getAddress().getHostName());
        outputStream.writeInt(address.getPort());

        outputStream.writeInt(protocol);
    }

}