package io.redscorp.ip.addr.counter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * The ipv4 address counter.
 *
 * @author <a href="mailto:gennady.abakyan@gmail.com">Gennady Abakyan</a>
 */
public class Ipv4AddrCounter {
    private final String filename;

    public Ipv4AddrCounter(String filename) {
        this.filename = filename;
    }

    public long count() throws IOException {
        try (Stream<String> linesStream = Files.lines(Path.of(filename))) {
            return linesStream.parallel().mapToInt(ipAddrString -> {
                String[] ipAddrParts = ipAddrString.split("[.]");

                if (ipAddrParts.length != 4) {
                    throw new IllegalArgumentException("The illegal ipv4 address: "
                            + Arrays.toString(ipAddrParts)
                            + ".length != 4");
                }

                return parseIpAddrPart(ipAddrParts[0]) << 24
                        | parseIpAddrPart(ipAddrParts[1]) << 16
                        | parseIpAddrPart(ipAddrParts[2]) << 8
                        | parseIpAddrPart(ipAddrParts[3]);
            }).distinct().count();
        }
    }

    private static int parseIpAddrPart(String ipAddrPart) {
        if (ipAddrPart.length() > 3) {
            throw new IllegalArgumentException("The illegal ipv4 address part: \"" + ipAddrPart
                    + "\".length() > 3");
        }

        if (ipAddrPart.charAt(0) == '0' && ipAddrPart.length() > 1) {
            throw new IllegalArgumentException("The illegal ipv4 address part: \"" + ipAddrPart
                    + "\".charAt(0) == '0'");
        }

        int result = 0;
        for (int i = 0; i < ipAddrPart.length(); i++) {
            char ch = ipAddrPart.charAt(i);
            if (ch < 48 || ch > 57) {
                throw new IllegalArgumentException("The illegal ipv4 address part: \"" + ipAddrPart
                        + "\".charAt(" + i + ") < 48 || \"" + ipAddrPart + "\".charAt(" + i + ") > 57");
            }

            result = result * 10 + (ipAddrPart.charAt(i) - 48);
        }

        return result;
    }
}
