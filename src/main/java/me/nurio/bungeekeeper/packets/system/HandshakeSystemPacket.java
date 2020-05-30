package me.nurio.bungeekeeper.packets.system;

import lombok.*;
import me.nurio.bungeekeeper.packets.Packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HandshakeSystemPacket implements Packet {

    public static final byte PACKET_ID = 1;

    @Getter private int serverType;
    @Getter private String license;
    @Getter private String owner;
    @Getter private String serverIp;
    @Getter private int serverPort;

    public byte getId() {
        return PACKET_ID;
    }

    @SneakyThrows
    public void read(DataInputStream inputStream) {
        serverType = inputStream.readInt();
        license = inputStream.readUTF();
        owner = inputStream.readUTF();
        serverIp = inputStream.readUTF();
        serverPort = inputStream.readInt();
    }

    @SneakyThrows
    public void write(DataOutputStream outputStream) {
        outputStream.writeByte(PACKET_ID);
        outputStream.writeInt(serverType);
        outputStream.writeUTF(license);
        outputStream.writeUTF(owner);
        outputStream.writeUTF(serverIp);
        outputStream.writeInt(serverPort);
        outputStream.flush();
    }

}