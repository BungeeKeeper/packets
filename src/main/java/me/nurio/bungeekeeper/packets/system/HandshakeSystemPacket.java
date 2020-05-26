package me.nurio.bungeekeeper.packets.system;

import lombok.*;
import me.nurio.bungeekeeper.packets.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HandshakeSystemPacket implements Packet {

    public static final byte PACKET_ID = 1;

    @Getter private String message;
    @Getter private boolean accepted;

    public byte getId() {
        return PACKET_ID;
    }

    @SneakyThrows
    public void read(DataInputStream inputStream) {
        message = inputStream.readUTF();
        accepted = inputStream.readBoolean();
    }

    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);
        outputStream.writeUTF(message);
        outputStream.writeBoolean(accepted);
    }

}