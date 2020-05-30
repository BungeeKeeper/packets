package me.nurio.bungeekeeper.packets.bungee;

import lombok.*;
import me.nurio.bungeekeeper.packets.IdentityUtil;
import me.nurio.bungeekeeper.packets.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.UUID;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class DisconnectPacket implements Packet {

    public static final byte PACKET_ID = 24;

    @Getter
    private long eventId = IdentityUtil.timeBasedId();

    @Getter @NonNull private String playerName;
    @Getter @NonNull private UUID uniqueId;
    @Getter @NonNull private String address;

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
        address = inputStream.readUTF();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);

        outputStream.writeLong(eventId);
        outputStream.writeUTF(playerName);
        outputStream.writeUTF(uniqueId.toString());
        outputStream.writeUTF(address);
        outputStream.flush();
    }

}