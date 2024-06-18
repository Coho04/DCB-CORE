package io.github.coho04.dcbcore.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommunicationStatusTest {

    @Test
    public void shouldReturnCorrectEnumValues() {
        assertSame(CommunicationStatus.ADD, CommunicationStatus.valueOf("ADD"));
        assertSame(CommunicationStatus.REMOVE, CommunicationStatus.valueOf("REMOVE"));
        assertSame(CommunicationStatus.START, CommunicationStatus.valueOf("START"));
    }

    @Test
    public void shouldThrowExceptionForInvalidEnumValue() {
        assertThrows(IllegalArgumentException.class, () -> CommunicationStatus.valueOf("INVALID"));
    }
}