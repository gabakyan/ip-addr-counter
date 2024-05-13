package io.redscorp.ip.addr.counter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * The test suite of {@link Ipv4AddrCounter}.
 *
 * @author <a href="mailto:gennady.abakyan@gmail.com">Gennady Abakyan</a>
 */
class Ipv4AddrCounterTests {
    @Test
    void testOk() throws IOException {
        Ipv4AddrCounter ipv4AddrCounter = new Ipv4AddrCounter("src/test/resources/ip-addresses.txt");
        Assertions.assertEquals(4, ipv4AddrCounter.count());
    }

    @Test
    void testIllegalIpv4Address() {
        Ipv4AddrCounter ipv4AddrCounter = new Ipv4AddrCounter("src/test/resources/ip-addresses-1.txt");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class
                , ipv4AddrCounter::count);
        Assertions.assertEquals("java.lang.IllegalArgumentException: "
                        + "The illegal ipv4 address: [67, 23, 4].length != 4"
                , exception.getMessage());
    }

    @Test
    void testIllegalIpv4AddressPart1() {
        Ipv4AddrCounter ipv4AddrCounter = new Ipv4AddrCounter("src/test/resources/ip-addresses-2.txt");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class
                , ipv4AddrCounter::count);
        Assertions.assertEquals("java.lang.IllegalArgumentException: "
                        + "The illegal ipv4 address part: \"12345\".length() > 3"
                , exception.getMessage());
    }

    @Test
    void testIllegalIpv4AddressPart2() {
        Ipv4AddrCounter ipv4AddrCounter = new Ipv4AddrCounter("src/test/resources/ip-addresses-3.txt");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class
                , ipv4AddrCounter::count);
        Assertions.assertEquals("java.lang.IllegalArgumentException: "
                        + "The illegal ipv4 address part: \"08\".charAt(0) == '0'"
                , exception.getMessage());
    }

    @Test
    void testIllegalIpv4AddressPart3() {
        Ipv4AddrCounter ipv4AddrCounter = new Ipv4AddrCounter("src/test/resources/ip-addresses-4.txt");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class
                , ipv4AddrCounter::count);
        Assertions.assertEquals("java.lang.IllegalArgumentException: "
                        + "The illegal ipv4 address part: \"3)\".charAt(1) < 48 || \"3)\".charAt(1) > 57"
                , exception.getMessage());
    }
}
