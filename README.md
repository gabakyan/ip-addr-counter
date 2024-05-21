# ip-addr-counter

Some highlights:

- The command to run the build:

  ``gradlew clean build``

  *The executable jar file can be found in:* ``build/libs``

- The command to run the test suite:

  ``gradlew test``

- The command to run executable jar after build:

  ``java -Xms6G -Xmx6G -XX:+UseCompressedOops -jar ip-addr-counter-<version>.jar <filename>``

  *Example:*

  ``java -Xms6G -Xmx6G -XX:+UseCompressedOops -jar ip-addr-counter-0.0.1-SNAPSHOT.jar ip_addresses``
