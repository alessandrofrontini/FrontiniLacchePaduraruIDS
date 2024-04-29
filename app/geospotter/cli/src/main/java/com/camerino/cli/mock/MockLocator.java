package com.camerino.cli.mock;

public class MockLocator {
    private static MockNodi mockNodi = new MockNodi();
    private static MockComuni mockComuni = new MockComuni();
    private static MockTuristi mockTuristi = new MockTuristi();
    private static MockItinerari mockItinerari = new MockItinerari(mockNodi);
    private static MockSegnalazioni mockSegnalazioni = new MockSegnalazioni();
    private static MockRecensioni mockRecensioni = new MockRecensioni();
    private static MockImmagini mockImmagini = new MockImmagini();
    private static MockRCDNodi mockRCD = new MockRCDNodi();

    public static MockNodi getMockNodi() {
        return mockNodi;
    }

    public static MockComuni getMockComuni() {
        return mockComuni;
    }

    public static MockTuristi getMockTuristi() {
        return mockTuristi;
    }

    public static MockItinerari getMockItinerari() {
        return mockItinerari;
    }
    public static MockSegnalazioni getMockSegnalazioni(){return mockSegnalazioni;}
    public static MockRecensioni getMockRecensioni(){return mockRecensioni;}
    public static MockImmagini getMockImmagini(){return mockImmagini;}
    public static MockRCDNodi getMockRCD(){return mockRCD;}
}
