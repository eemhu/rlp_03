package com.teragrep.rlp_03.context.frame.access;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccessTest {

    @Test
    public void testAccess() {
        Access access = new Access();

        Assertions.assertFalse(access.closed());

        Lease leaseOut;
        try (Lease lease = access.get()) {
            leaseOut = lease;
            Assertions.assertTrue(lease.isOpen());
            // try-with-resources AutoCloses
        }
        Assertions.assertFalse(leaseOut.isOpen());

        //Assertions.assertThrows(IllegalStateException.class, () -> access.accept(leaseOut));

        access.close();

        Assertions.assertTrue(access.closed());

        Assertions.assertThrows(IllegalStateException.class, access::get);

        Assertions.assertThrows(IllegalStateException.class, () -> access.accept(leaseOut));
    }
}
