package com.fy.tutorial01.server;

public enum ServerHandshakeState {

    SEND_INIT_REQ{
        @Override
        public byte[] getResponsePayload() {
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
        public byte[] getResponsePayload() {
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
        public byte[] getResponsePayload() {
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

    public boolean receivedPayloadMatchesExpected(byte[] bytes) {
        return true;
    }

    public byte[] getResponsePayload() {
        return new byte[0];
    }

    public Integer getNextStateOrdinal() {
        return 1;
    }
}
