package com.fy.tutorial01.client;

public enum ClientHandshakeState {
    SEND_INIT_REQ{
        @Override
        public byte[] getPayload() {
            return new byte[]{0};
        }

        @Override
        public boolean receivedPayloadMatchesExpected(byte[] bytes) {
            return bytes.length == 1 && bytes[0] == 0;
        }

        @Override
        public Integer getNextStateOrdinal() {
            return 1;
        }
    },
    SEND_1_REQ{
        @Override
        public byte[] getPayload() {
            return new byte[]{1};
        }

        @Override
        public boolean receivedPayloadMatchesExpected(byte[] bytes) {
            return bytes.length == 1 && bytes[0] == 1;
        }

        @Override
        public Integer getNextStateOrdinal() {
            return 2;
        }
    },
    SEND_2_REQ{
        @Override
        public byte[] getPayload() {
            return new byte[]{2};
        }

        @Override
        public boolean receivedPayloadMatchesExpected(byte[] bytes) {
            return bytes.length == 1 && bytes[0] == 2;
        }

        @Override
        public Integer getNextStateOrdinal() {
            return null;
        }
    };

    public byte[] getPayload() {
        return new byte[0];
    }

    public boolean receivedPayloadMatchesExpected(byte[] bytes) {
        return true;
    }

    public Integer getNextStateOrdinal() {
        return null;
    }
}
