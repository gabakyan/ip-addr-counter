package io.redscorp.ip.addr.counter;

import java.time.LocalDateTime;

/**
 * The main class.
 *
 * @author <a href="mailto:gennady.abakyan@gmail.com">Gennady Abakyan</a>
 */
public class Main {
	public static void main(String[] args) throws Exception {
		if (args == null || args.length != 1) {
			System.out.println("Usage: java -jar ip-addr-counter-<version>.jar <filename>");

			return;
		}

		System.out.println("Started: " + LocalDateTime.now());
		System.out.println(new Ipv4AddrCounter(args[0]).count());
		System.out.println("Ended: " + LocalDateTime.now());
	}
}
