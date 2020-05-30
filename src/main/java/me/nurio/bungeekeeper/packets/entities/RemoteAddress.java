package me.nurio.bungeekeeper.packets.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class RemoteAddress {

    @NonNull private String ipAddress;
    @NonNull private String hostName;
    private int port;

}