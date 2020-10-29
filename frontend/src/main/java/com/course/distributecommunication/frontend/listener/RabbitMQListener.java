package com.course.distributecommunication.frontend.listener;

import com.course.distributecommunication.frontend.services.DashboardRestService;
import com.course.distributecommunication.rabbit.BookAndAuthorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.course.distributecommunication.rabbit.Constants.BOOK_AND_AUTHOR_QUEUE;

/**
 * @author Ilya Alterovych
 */
@Component
public class RabbitMQListener {
    private static final Logger logger = LoggerFactory.getLogger(DashboardRestService.class);

    @RabbitListener(queues = BOOK_AND_AUTHOR_QUEUE)
    public void listen(BookAndAuthorMessage bookAndAuthorMessage) {
        logger.info(
                "Message read from {} : {}",
                BOOK_AND_AUTHOR_QUEUE,
                bookAndAuthorMessage
        );
    }
}
