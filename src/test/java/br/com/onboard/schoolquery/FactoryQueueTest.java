package br.com.onboard.schoolquery;

import br.com.onboard.schoolquery.config.amqp.SCHOOLChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;

@EnableBinding(SCHOOLChannel.SchoolExchangeInput.class)
public class FactoryQueueTest<T> {

    @Autowired
    private SCHOOLChannel.SchoolExchangeInput schoolExchangeInput;

    public void criaNovoRegistroFila(T event) {
        schoolExchangeInput.input().send(
                MessageBuilder.withPayload(event)
                        .setHeader("type", event.getClass().getSimpleName())
                        .build());
    }
}
