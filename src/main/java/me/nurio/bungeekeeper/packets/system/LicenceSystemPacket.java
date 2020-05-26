package me.nurio.bungeekeeper.packets.system;

import lombok.Getter;
import lombok.SneakyThrows;
import me.nurio.bungeekeeper.packets.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class LicenceSystemPacket implements Packet {

    public static final byte PACKET_ID = 3;

    @Getter private boolean accepted;
    @Getter private String message;
    @Getter private String licence;
    @Getter private long expiration;

    @Override
    public byte getId() {
        return PACKET_ID;
    }

    @Override
    @SneakyThrows
    public void read(DataInputStream inputStream) {
        accepted = inputStream.readBoolean();
        message = inputStream.readUTF();
        licence = inputStream.readUTF();
        expiration = inputStream.readLong();
    }

    @Override
    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);
        outputStream.writeBoolean(accepted);
        outputStream.writeUTF(message);
        outputStream.writeUTF(licence);
        outputStream.writeLong(expiration);
    }

}