package me.nurio.bungeekeeper.packets.bungee;

import lombok.*;
import me.nurio.bungeekeeper.packets.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.util.UUID;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ConnectionResponsePacket implements Packet {

    public static final byte PACKET_ID = 30;

    @Getter private long eventId;

    @Getter @NonNull private String playerName;
    @Getter @NonNull private UUID uniqueId;
    @Getter @NonNull private InetAddress address;

    @Getter @NonNull private boolean allowed;
    @Getter @NonNull private int reasonId;
    @Getter @NonNull private String reason;

    @Override
    public byte getId() {
        return PACKET_ID;
    }

    @Override
    @SneakyThrows
    public void read(DataInputStream inputStream) {
        eventId = inputStream.readLong();
        playerName = inputStream.readUTF();
        uniqueId = UUID.fromString(inputStream.readUTF());

        String ipAddress = inputStream.readUTF();
        String hostName = inputStream.readUTF();
        address = InetAddress.getByAddress(hostName, InetAddress.getByName(ipAddress).getAddress());

        allowed = inputStream.readBoolean();
        reasonId = inputStream.readInt();
        reason = inputStream.readUTF();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);

        outputStream.writeLong(eventId);
        outputStream.writeUTF(playerName);
        outputStream.writeUTF(uniqueId.toString());

        outputStream.writeUTF(address.getHostAddress());
        outputStream.writeUTF(address.getHostName());

        outputStream.writeBoolean(allowed);
        outputStream.writeInt(reasonId);
        outputStream.writeUTF(reason);
    }

}