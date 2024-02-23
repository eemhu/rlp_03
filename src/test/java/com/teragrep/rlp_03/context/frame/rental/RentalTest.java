package com.teragrep.rlp_03.context.frame.rental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RentalTest {

    @Test
    public void testRental() {
        Rental rental = new Rental();

        Assertions.assertFalse(rental.closed());

        Lease leaseOut;
        try (Lease lease = rental.get()) {
            leaseOut = lease;
            Assertions.assertTrue(lease.isOpen());
            // try-with-resources AutoCloses
        }
        Assertions.assertFalse(leaseOut.isOpen());

        //TODO: Should this throw anymore?
        //Assertions.assertThrows(IllegalStateException.class, () -> rental.accept(leaseOut));

        rental.close();

        Assertions.assertTrue(rental.closed());

        Assertions.assertThrows(IllegalStateException.class, rental::get);

        Assertions.assertThrows(IllegalStateException.class, () -> rental.accept(leaseOut));
    }
}
