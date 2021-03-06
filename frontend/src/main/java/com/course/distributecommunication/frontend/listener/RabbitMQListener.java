package com.course.distributecommunication.frontend.listener;

import com.course.distributecommunication.frontend.services.DashboardGrpcService;
import com.course.distributecommunication.frontend.services.DashboardRestService;
import com.course.distributecommunication.rabbit.BookAndAuthorMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.course.distributecommunication.rabbit.Constants.BOOK_AND_AUTHOR_QUEUE;

/**
 * @author Ilya Alterovych
 */
@Component
@RequiredArgsConstructor
public class RabbitMQListener {
    private static final Logger logger = LoggerFactory.getLogger(DashboardRestService.class);
    private final DashboardGrpcService grpcService;

    @RabbitListener(queues = BOOK_AND_AUTHOR_QUEUE)
    public void listen(BookAndAuthorMessage message) {
        logger.info(
                "Message received from {} : {}",
                BOOK_AND_AUTHOR_QUEUE,
                message
        );

        grpcService.addAuthorIfNotExist(message);
    }
}
