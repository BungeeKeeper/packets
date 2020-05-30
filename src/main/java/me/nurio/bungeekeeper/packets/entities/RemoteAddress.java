package me.nurio.bungeekeeper.packets.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.net.InetAddress;
import java.net.InetSocketAddress;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class RemoteAddress {

    @NonNull private String ipAddress;
    @NonNull private String hostName;
    private int port;

    public static RemoteAddress fromInetSocket(InetSocketAddress address) {
        return new RemoteAddress(
            address.getAddress().getHostAddress(),
            address.getAddress().getHostName(),
            address.getPort()
        );
    }

    public static RemoteAddress fromInetAddress(InetAddress address) {
        return new RemoteAddress(
            address.getHostAddress(),
            address.getHostName()
        );
    }

}