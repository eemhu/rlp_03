package com.teragrep.rlp_03.context.frame.access;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccessTest {

    @Test
    public void testAccess() {
        Access access = new Access();

        Assertions.assertFalse(access.isTerminated());

        Lease leaseOut;
        try (Lease lease = access.get()) {
            leaseOut = lease;
            Assertions.assertFalse(lease.isTerminated());
            // try-with-resources AutoCloses
        }
        Assertions.assertTrue(leaseOut.isTerminated());

        access.terminate();

        Assertions.assertTrue(access.isTerminated());

        Assertions.assertThrows(IllegalStateException.class, access::get);

        Assertions.assertThrows(IllegalStateException.class, () -> access.release(leaseOut));
    }
}