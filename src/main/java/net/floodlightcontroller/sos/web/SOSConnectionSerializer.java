package net.floodlightcontroller.sos.web;

import java.io.IOException;

import net.floodlightcontroller.sos.ISOSConnection;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SOSConnectionSerializer extends JsonSerializer<ISOSConnection> {

	@Override
	public void serialize(ISOSConnection conn, JsonGenerator jGen, SerializerProvider sProv) 
			throws IOException, JsonProcessingException {
		jGen.configure(Feature.WRITE_NUMBERS_AS_STRINGS, true);

		if (conn == null) {
			jGen.writeStartObject();
			jGen.writeString("No SOS connection to report");
			jGen.writeEndObject();
			return;
		}

		jGen.writeStartObject();
		jGen.writeNumberField("buffer-size", conn.getBufferSize());
		jGen.writeNumberField("parallel-connections", conn.getNumParallelSockets());
		jGen.writeNumberField("queue-capacity", conn.getQueueCapacity());
		jGen.writeNumberField("flow-timeout", conn.getFlowTimeout());
		jGen.writeObjectField("client", conn.getClient());
		jGen.writeObjectField("client-side-agent", conn.getClientSideAgent());
		jGen.writeObjectField("server", conn.getServer());
		jGen.writeObjectField("server-side-agent", conn.getServerSideAgent());
		jGen.writeObjectField("server-side-agent-tcp-port", conn.getServerSideAgentTcpPort());
		jGen.writeObjectField("route-client-to-agent", conn.getClientSideRoute());
		jGen.writeObjectField("route-server-to-agent", conn.getServerSideRoute());
		jGen.writeObjectField("route-agent-to-agent", conn.getInterAgentRoute());
		jGen.writeStringField("route-client-to-agent", conn.getTransferID().toString());
		jGen.writeStringField("time-init", conn.getInitTime().toString());
		jGen.writeStringField("time-start", conn.getStartTime().toString());
		jGen.writeStringField("time-stop", conn.getStopTime().toString());

		jGen.writeEndObject();
	}
}