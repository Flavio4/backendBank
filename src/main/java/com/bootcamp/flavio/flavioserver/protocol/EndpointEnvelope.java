
package com.bootcamp.flavio.flavioserver.protocol;

public class EndpointEnvelope<M> {
    
    private String id;
    private M message;

    public EndpointEnvelope() {
    }

    public EndpointEnvelope(String id, M message) {
        this.id = id;
        this.message = message;
    }

    public EndpointEnvelope(M message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public M getMessage() {
        return message;
    }

    public void setMessage(M message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EndpointEnvelope{" + "id=" + id + ", message=" + message + '}';
    }
    
    
    
}
