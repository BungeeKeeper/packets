package me.nurio.bungeekeeper.packets.system;

import lombok.*;
import me.nurio.bungeekeeper.packets.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GoodbyeSystemPacket implements Packet {

    public static final byte PACKET_ID = 2;

    @Getter private byte reason;

    public byte getId() {
        return PACKET_ID;
    }

    @SneakyThrows
    public void read(DataInputStream inputStream) {
        reason = inputStream.readByte();
    }

    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);
        outputStream.writeByte(reason);
    }

}