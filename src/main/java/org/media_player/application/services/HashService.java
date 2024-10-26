package org.media_player.application.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashService {
    public String hash(String text) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashByte = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hashByte);
    }

}
