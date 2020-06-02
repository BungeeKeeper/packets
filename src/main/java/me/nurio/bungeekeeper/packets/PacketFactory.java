package me.nurio.bungeekeeper.packets;

import lombok.RequiredArgsConstructor;
import me.nurio.bungeekeeper.packets.bungee.*;
import me.nurio.bungeekeeper.packets.system.GoodbyeSystemPacket;
import me.nurio.bungeekeeper.packets.system.HandshakeSystemPacket;
import me.nurio.bungeekeeper.packets.system.LicenceSystemPacket;

@RequiredArgsConstructor
public class PacketFactory {

    public static Packet createPacketById(byte packetId) {
        if (packetId == 1) return new HandshakeSystemPacket();
        if (packetId == 2) return new GoodbyeSystemPacket();
        if (packetId == 3) return new LicenceSystemPacket();

        if (packetId == 20) return new HandshakePacket();
        if (packetId == 21) return new PreConnectionPacket();
        if (packetId == 25) return new ConnectionPacket();
        if (packetId == 22) return new PostConnectionPacket();
        if (packetId == 23) return new ServerChangePacket();
        if (packetId == 24) return new DisconnectPacket();

        if (packetId == 30) return new ConnectionResponsePacket();
        return null;
    }

}