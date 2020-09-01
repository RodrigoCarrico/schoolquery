package br.com.onboard.schoolquery.config.amqp;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SCHOOLChannel {

    public static final String SCHOOL_INPUT = "school-input-events";

    public  interface SchoolExchangeInput {
        @Input(SCHOOLChannel.SCHOOL_INPUT)
        SubscribableChannel input();
    }
}
